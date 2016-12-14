package com.sismas.erp.facade;

import com.sismas.erp.dominio.Menu;
import com.sismas.erp.dominio.MenuPK;
import com.sismas.erp.dominio.Modulo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ismael Juchazara
 */
@Stateless
public class MenuFacade extends AbstractFacade<Menu> {

    @PersistenceContext(unitName = "springERP")
    private EntityManager em;

    /**
     * @noparam constructor
     */
    public MenuFacade() {
        super(Menu.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected void create(Menu entity) {
        entity.setMenuPK(obtenerID(entity));
        super.create(entity);
    }

    /**
     * @param entity Menu a persistir
     */
    public void save(Menu entity) {
        if (entity.getMenuPK() == null) {
            create(entity);
        } else {
            edit(entity);
        }
    }

    private MenuPK obtenerID(Menu entity) {
        Short codigo = (Short) em.createQuery("SELECT max(m.menuPK.idMenu) from Menu m WHERE m.modulo=:modulo")
                .setParameter("modulo", entity.getModulo()).getSingleResult();
        if (codigo == null) {
            codigo = (short) 1;
        } else {
            codigo++;
        }
        return new MenuPK(codigo, entity.getModulo().getIdModulo());
    }

    /**
     * @param modulo Modulo del que se desea buscar los menus asociados
     * @return Lista de menus asociados al módulo recibido como parámetro
     */
    public List<Menu> findByModulo(Modulo modulo) {
        return findByField("modulo", modulo);
    }

}
