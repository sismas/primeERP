package com.sismas.erp.beans;

import com.sismas.erp.dominio.Menu;
import com.sismas.erp.dominio.Perfil;
import com.sismas.erp.facade.MenuFacade;
import com.sismas.erp.facade.PerfilFacade;
import com.sismas.erp.facade.PerfilmenuFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.DualListModel;

@Named("perfil")
@ViewScoped
public class PerfilBean implements Serializable {

    @EJB
    transient PerfilFacade perfilService;
    @EJB
    transient PerfilmenuFacade perfilmenuService;
    @EJB
    transient MenuFacade menuService;

    private List<Perfil> lista;
    private Perfil seleccionado;
    private DualListModel<Menu> listaMenu;

    public PerfilBean() {
    }

    @PostConstruct
    public void init() {
        limpiar();
    }

    public void insertar() {
        try {
            perfilService.save(seleccionado, listaMenu.getTarget());
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle msg = context.getApplication().getResourceBundle(context, "msg");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("successful_update"), ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
    }

    public void eliminar() {
        try {
            perfilService.remove(seleccionado);
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle msg = context.getApplication().getResourceBundle(context, "msg");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("successful_delete"), ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
    }

    public List<Perfil> getLista() {
        lista = perfilService.findAll("nombre");
        return lista;
    }

    public Perfil getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Perfil seleccionado) {
        this.seleccionado = seleccionado;
    }

    public void limpiar() {
        this.seleccionado = new Perfil();
        this.listaMenu = new DualListModel<>(menuService.findAll(), new ArrayList<Menu>());
    }

    public void refreshListaMenu(Perfil perfil) {
        List<Menu> actuales = perfilmenuService.findMenuByPerfil(perfil);
        List<Menu> disponibles = menuService.findAll();
        disponibles.removeAll(actuales);
        this.listaMenu = new DualListModel<>(disponibles, actuales);
    }

    public DualListModel<Menu> getListaMenu() {
        return listaMenu;
    }

    public void setListaMenu(DualListModel<Menu> listaMenu) {
        this.listaMenu = listaMenu;
    }

}
