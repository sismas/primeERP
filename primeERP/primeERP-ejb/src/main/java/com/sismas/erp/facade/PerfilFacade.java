package com.sismas.erp.facade;

import com.sismas.erp.dominio.Menu;
import com.sismas.erp.dominio.Perfil;
import com.sismas.erp.dominio.Perfilmenu;
import com.sismas.erp.dominio.PerfilmenuPK;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ismael Juchazara
 */
@Stateless
public class PerfilFacade extends AbstractFacade<Perfil> {

    @PersistenceContext(unitName = "springERP")
    private EntityManager em;

    @EJB
    PerfilmenuFacade perfilMenuService;

    /**
     * @noparam constructor
     */
    public PerfilFacade() {
        super(Perfil.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected void create(Perfil entity) {
        entity.setIdPerfil(obtenerID());
        super.create(entity);
    }

    /**
     * @param entity Perfil a persistir
     * @param listaMenu Lista de objetos menu asociados al perfil
     */
    public void save(Perfil entity, List<Menu> listaMenu) {
        if (entity.getIdPerfil() == null) {
            create(entity);
        } else {
            edit(entity);
        }
        perfilMenuService.disableAllMenu(entity);
        for (Menu menu : listaMenu) {
            Perfilmenu perfilmenu = perfilMenuService.find(new PerfilmenuPK(entity.getIdPerfil(), menu.getMenuPK()));
            if (perfilmenu != null) {
                if (perfilmenu.getEstado().equals(PerfilmenuFacade.INHABILITADO)) {
                    perfilmenu.setEstado(PerfilmenuFacade.HABILITADO);
                    perfilMenuService.edit(perfilmenu);
                }
            } else {
                perfilmenu = new Perfilmenu();
                perfilmenu.setPerfil(entity);
                perfilmenu.setMenu(menu);
                perfilmenu.setEstado(PerfilmenuFacade.HABILITADO);
                perfilMenuService.create(perfilmenu);
            }
        }
    }

    private Short obtenerID() {
        Short codigo = (Short) em.createQuery("select max(p.idPerfil) from Perfil p").getSingleResult();
        if (codigo == null) {
            codigo = (short) 1;
        } else {
            codigo++;
        }
        return codigo;
    }

}
