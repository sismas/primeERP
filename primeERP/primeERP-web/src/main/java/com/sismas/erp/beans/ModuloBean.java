package com.sismas.erp.beans;

import com.sismas.erp.dominio.Modulo;
import com.sismas.erp.facade.ModuloFacade;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("modulo")
@ViewScoped
public class ModuloBean implements Serializable {

    @EJB
    transient ModuloFacade moduloService;

    private List<Modulo> lista;
    private Modulo seleccionado;

    public ModuloBean() {
        seleccionado = new Modulo();
    }

    public void insertar() {
        try {
            moduloService.save(seleccionado);
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle msg = context.getApplication().getResourceBundle(context, "msg");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("successful_update"), ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
    }

    public void eliminar() {
        try {
            moduloService.remove(seleccionado);
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle msg = context.getApplication().getResourceBundle(context, "msg");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("successful_delete"), ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
    }

    public List<Modulo> getLista() {
        lista = moduloService.findAll("nombre");
        return lista;
    }

    public Modulo getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Modulo seleccionado) {
        this.seleccionado = seleccionado;
    }

    public void limpiar(ActionEvent actionEvent) {
        this.seleccionado = new Modulo();
    }

}
