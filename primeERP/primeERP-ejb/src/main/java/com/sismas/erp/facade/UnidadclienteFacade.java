package com.sismas.erp.facade;

import com.sismas.erp.dominio.Cliente;
import com.sismas.erp.dominio.Unidad;
import com.sismas.erp.dominio.Unidadcliente;
import com.sismas.erp.dominio.UnidadclientePK;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Ismael Juchazara
 */
@Stateless
public class UnidadclienteFacade extends AbstractFacade<Unidadcliente> {

    public static final String HABILITADO = "H";
    public static final String INHABILITADO = "I";

    @PersistenceContext(unitName = "springERP")
    private EntityManager em;

    /**
     * @noparam constructor
     */
    public UnidadclienteFacade() {
        super(Unidadcliente.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected void create(Unidadcliente entity) {
        entity.setUnidadclientePK(obtenerID(entity));
        super.create(entity);
    }

    /**
     * @param entity Unidadcliente a persistir
     */
    public void save(Unidadcliente entity) {
        if (entity.getUnidadclientePK() == null) {
            create(entity);
        } else {
            edit(entity);
        }
    }

    private UnidadclientePK obtenerID(Unidadcliente entity) {
        return new UnidadclientePK(entity.getCliente().getIdCliente(), entity.getUnidad().getUnidadPK());
    }

    /**
     * @param cliente Cliente del que se desea buscar las unidades asociados
     * @return Lista de unidades habilitadas que estan asociadas al cliente
     * recibido como parámetro
     */
    public List<Unidad> findUnidadByCliente(Cliente cliente) {
        return em.createNamedQuery("Unidadcliente.findUnidadByCliente").setParameter("cliente", cliente).setParameter("estado", UnidadclienteFacade.HABILITADO).getResultList();
    }

    /**
     * @param cliente Cliente del que se desea deshabilitar todas las unidades
     * asociadas
     */
    public void disableAllUnidad(Cliente cliente) {
        Query queryDisableMenu = em.createQuery("UPDATE Unidadcliente u SET u.estado = 'I' WHERE u.cliente = :cliente AND u.estado = :estado");
        queryDisableMenu.setParameter("cliente", cliente).setParameter("estado", UnidadclienteFacade.HABILITADO).executeUpdate();
    }

}
