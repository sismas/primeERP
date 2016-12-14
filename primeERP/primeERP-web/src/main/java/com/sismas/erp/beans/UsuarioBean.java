package com.sismas.erp.beans;

import com.sismas.erp.dominio.Usuario;
import com.sismas.erp.facade.UsuarioFacade;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("usuario")
@ViewScoped
public class UsuarioBean implements Serializable {

    @EJB
    transient UsuarioFacade usuarioService;

    private List<Usuario> lista;
    private Usuario seleccionado;

    public UsuarioBean() {
        seleccionado = new Usuario();
    }

    public void insertar() {
        try {
            usuarioService.save(seleccionado);
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle msg = context.getApplication().getResourceBundle(context, "msg");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("successful_update"), ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
    }

    public void eliminar() {
        try {
            usuarioService.remove(seleccionado);
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle msg = context.getApplication().getResourceBundle(context, "msg");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("successful_delete"), ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
    }

    public List<Usuario> getLista() {
        lista = usuarioService.findAll("nombre");
        return lista;
    }

    public Usuario getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Usuario seleccionado) {
        this.seleccionado = seleccionado;
    }

    public void limpiar(ActionEvent actionEvent) {
        this.seleccionado = new Usuario();
    }

}
