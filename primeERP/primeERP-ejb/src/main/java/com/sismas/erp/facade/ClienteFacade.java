package com.sismas.erp.facade;

import com.sismas.erp.dominio.Cliente;
import com.sismas.erp.dominio.Unidad;
import com.sismas.erp.dominio.Unidadcliente;
import com.sismas.erp.dominio.UnidadclientePK;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ismael Juchazara
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "springERP")
    private EntityManager em;

    @EJB
    UnidadclienteFacade unidadClienteService;

    /**
     * @noparam constructor
     */
    public ClienteFacade() {
        super(Cliente.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected void create(Cliente entity) {
        entity.setIdCliente(obtenerID());
        super.create(entity);
    }

    /**
     * @param entity Cliente a persistir
     * @param listaUnidad Lista de unidades asociadas al cliente
     */
    public void save(Cliente entity, List<Unidad> listaUnidad) {
        if (entity.getIdCliente() == null) {
            create(entity);
        } else {
            edit(entity);
        }
        unidadClienteService.disableAllUnidad(entity);
        for (Unidad unidad : listaUnidad) {
            Unidadcliente unidadcliente = unidadClienteService.find(new UnidadclientePK(entity.getIdCliente(), unidad.getUnidadPK()));
            if (unidadcliente != null) {
                if (unidadcliente.getEstado().equals(UnidadclienteFacade.INHABILITADO)) {
                    unidadcliente.setEstado(UnidadclienteFacade.HABILITADO);
                    unidadClienteService.edit(unidadcliente);
                }
            } else {
                unidadcliente = new Unidadcliente();
                unidadcliente.setCliente(entity);
                unidadcliente.setUnidad(unidad);
                unidadcliente.setEstado(UnidadclienteFacade.HABILITADO);
                unidadClienteService.create(unidadcliente);
            }
        }
    }

    private Integer obtenerID() {
        Integer codigo = (Integer) em.createQuery("select max(c.idCliente) from Cliente c").getSingleResult();
        if (codigo == null) {
            codigo = 1;
        } else {
            codigo++;
        }
        return codigo;
    }

}
