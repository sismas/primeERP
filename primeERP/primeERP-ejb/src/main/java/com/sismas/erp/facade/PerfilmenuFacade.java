package com.sismas.erp.facade;

import com.sismas.erp.dominio.Menu;
import com.sismas.erp.dominio.Perfil;
import com.sismas.erp.dominio.Perfilmenu;
import com.sismas.erp.dominio.PerfilmenuPK;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Ismael Juchazara
 */
@Stateless
public class PerfilmenuFacade extends AbstractFacade<Perfilmenu> {

    public static final String HABILITADO = "H";
    public static final String INHABILITADO = "I";

    @PersistenceContext(unitName = "springERP")
    private EntityManager em;

    /**
     * @noparam constructor
     */
    public PerfilmenuFacade() {
        super(Perfilmenu.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected void create(Perfilmenu entity) {
        entity.setPerfilmenuPK(obtenerID(entity));
        super.create(entity);
    }

    /**
     * @param entity Perfilmenu a persistir
     */
    public void save(Perfilmenu entity) {
        if (entity.getPerfilmenuPK() == null) {
            create(entity);
        } else {
            edit(entity);
        }
    }

    private PerfilmenuPK obtenerID(Perfilmenu entity) {
        return new PerfilmenuPK(entity.getPerfil().getIdPerfil(), entity.getMenu().getMenuPK());
    }

    /**
     * @param perfil perfil del que se desea buscar los Perfilmenu asociados
     * @return Lista de Perfilmenu asociados al perfil recibido como parámetro
     */
    public List<Perfilmenu> findByPerfil(Perfil perfil) {
        return findByField("perfil", perfil);
    }

    /**
     * @param perfil perfil que se desea buscar
     * @param menu menu que se desea buscar
     * @return Objeto Perfilmenu asociado al perfil y menu recibidos como
     * parámetro
     */
    public Perfilmenu findByPerfilAndMenu(Perfil perfil, Menu menu) {
        return find(new PerfilmenuPK(perfil.getIdPerfil(), menu.getMenuPK()));
    }

    /**
     * @param perfil perfil del que se desea buscar los menus asociados
     * @return Lista de Menu asociados al perfil recibido como parámetro
     * parámetro
     */
    public List<Menu> findMenuByPerfil(Perfil perfil) {
        return em.createNamedQuery("Perfilmenu.findMenuByPerfil").setParameter("perfil", perfil).setParameter("estado", PerfilmenuFacade.HABILITADO).getResultList();
    }

    /**
     * @param perfil perfil del que se desea deshabilitar todos los menus
     * asociados
     */
    public void disableAllMenu(Perfil perfil) {
        Query queryDisableMenu = em.createQuery("UPDATE Perfilmenu p SET p.estado = 'I' WHERE p.perfil = :perfil AND p.estado = :estado");
        queryDisableMenu.setParameter("perfil", perfil).setParameter("estado", PerfilmenuFacade.HABILITADO).executeUpdate();
    }

}
