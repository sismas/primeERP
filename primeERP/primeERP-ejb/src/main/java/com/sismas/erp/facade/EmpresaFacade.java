package com.sismas.erp.facade;

import com.sismas.erp.dominio.Empresa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ismael Juchazara
 */
@Stateless
public class EmpresaFacade extends AbstractFacade<Empresa> {

    @PersistenceContext(unitName = "springERP")
    private EntityManager em;

    /**
     * @noparam constructor
     */
    public EmpresaFacade() {
        super(Empresa.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected void create(Empresa entity) {
        entity.setIdEmpresa(obtenerID());
        super.create(entity);
    }

    /**
     * @param entity Empresa a persistir
     */
    public void save(Empresa entity) {
        if (entity.getIdEmpresa() == null) {
            create(entity);
        } else {
            edit(entity);
        }
    }

    private Short obtenerID() {
        Short codigo = (Short) em.createQuery("select max(e.idEmpresa) from Empresa e").getSingleResult();
        if (codigo == null) {
            codigo = (short) 1;
        } else {
            codigo++;
        }
        return codigo;
    }

}
