package com.sismas.erp.facade;

import com.sismas.erp.dominio.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ismael Juchazara
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "springERP")
    private EntityManager em;

    /**
     * @noparam constructor
     */
    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected void create(Usuario entity) {
        entity.setIdUsuario(obtenerID());
        super.create(entity);
    }

    /**
     * @param entity
     */
    public void save(Usuario entity) {
        if (entity.getIdUsuario() == null) {
            create(entity);
        } else {
            edit(entity);
        }
    }

    private Integer obtenerID() {
        Integer codigo = (Integer) em.createQuery("select max(u.idUsuario) from Usuario u").getSingleResult();
        if (codigo == null) {
            codigo = 1;
        } else {
            codigo++;
        }
        return codigo;
    }

}
