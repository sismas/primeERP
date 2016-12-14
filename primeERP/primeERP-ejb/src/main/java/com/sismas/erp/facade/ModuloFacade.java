package com.sismas.erp.facade;

import com.sismas.erp.dominio.Modulo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ismael Juchazara
 */
@Stateless
public class ModuloFacade extends AbstractFacade<Modulo> {

    @PersistenceContext(unitName = "springERP")
    private EntityManager em;

    /**
     * @noparam constructor
     */
    public ModuloFacade() {
        super(Modulo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected void create(Modulo entity) {
        entity.setIdModulo(obtenerID());
        super.create(entity);
    }

    /**
     * @param entity Modulo a persistir
     */
    public void save(Modulo entity) {
        if (entity.getIdModulo() == null) {
            create(entity);
        } else {
            edit(entity);
        }
    }

    private Short obtenerID() {
        Short codigo = (Short) em.createQuery("select max(m.idModulo) from Modulo m").getSingleResult();
        if (codigo == null) {
            codigo = (short) 1;
        } else {
            codigo++;
        }
        return codigo;
    }

}
