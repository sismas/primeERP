package com.sismas.erp.facade;

import com.sismas.erp.dominio.Unidad;
import com.sismas.erp.dominio.UnidadPK;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ismael Juchazara
 */
@Stateless
public class UnidadFacade extends AbstractFacade<Unidad> {

    @PersistenceContext(unitName = "springERP")
    private EntityManager em;

    /**
     * @noparam constructor
     */
    public UnidadFacade() {
        super(Unidad.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected void create(Unidad entity) {
        entity.setUnidadPK(obtenerID(entity));
        super.create(entity);
    }

    /**
     * @param entity Unidad a persistir
     */
    public void save(Unidad entity) {
        if (entity.getUnidadPK() == null) {
            create(entity);
        } else {
            edit(entity);
        }
    }

    private UnidadPK obtenerID(Unidad entity) {
        Integer codigo = (Integer) em.createQuery("SELECT MAX(u.unidadPK.idUnidad) FROM Unidad u WHERE u.unidadPK.idEmpresa=:empresa")
                .setParameter("empresa", entity.getEmpresa().getIdEmpresa()).getSingleResult();
        if (codigo == null) {
            codigo = 1;
        } else {
            codigo++;
        }
        return new UnidadPK(codigo, entity.getEmpresa().getIdEmpresa());
    }

}
