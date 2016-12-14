package com.sismas.erp.facade;

import com.sismas.erp.dominio.Icono;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ismael Juchazara
 */
@Stateless
public class IconoFacade extends AbstractFacade<Icono> {

    @PersistenceContext(unitName = "springERP")
    private EntityManager em;

    /**
     * @noparam constructor
     */
    public IconoFacade() {
        super(Icono.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected void create(Icono entity) {
        entity.setIdIcono(obtenerID());
        super.create(entity);
    }

    /**
     * @param entity Icono a persistir
     */
    public void save(Icono entity) {
        if (entity.getIdIcono() == null) {
            create(entity);
        } else {
            edit(entity);
        }
    }

    private Integer obtenerID() {
        Integer codigo = (Integer) em.createQuery("select max(i.idIcono) from Icono i").getSingleResult();
        if (codigo == null) {
            codigo = 1;
        } else {
            codigo++;
        }
        return codigo;
    }

}
