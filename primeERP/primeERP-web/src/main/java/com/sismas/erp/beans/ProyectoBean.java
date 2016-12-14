package com.sismas.erp.beans;

import com.sismas.erp.dominio.Unidad;
import com.sismas.erp.facade.UnidadFacade;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("proyecto")
@ViewScoped
public class ProyectoBean implements Serializable {

    @EJB
    transient UnidadFacade proyectoService;

    private List<Unidad> lista;
    private Unidad seleccionado;

    public ProyectoBean() {
        seleccionado = new Unidad();
    }

    public void insertar() {
        try {
            proyectoService.save(seleccionado);
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle msg = context.getApplication().getResourceBundle(context, "msg");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("successful_update"), ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
    }

    public void eliminar() {
        try {
            proyectoService.remove(seleccionado);
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle msg = context.getApplication().getResourceBundle(context, "msg");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("successful_delete"), ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
    }

    public List<Unidad> getLista() {
        lista = proyectoService.findAll("nombre");
        return lista;
    }

    public Unidad getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Unidad seleccionado) {
        this.seleccionado = seleccionado;
    }

    public void limpiar(ActionEvent actionEvent) {
        this.seleccionado = new Unidad();
    }

}
