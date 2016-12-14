package com.sismas.erp.beans;

import com.sismas.erp.dominio.Empresa;
import com.sismas.erp.facade.EmpresaFacade;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named("empresa")
@SessionScoped
public class EmpresaBean implements Serializable {

    @EJB
    transient EmpresaFacade empresaService;

    private List<Empresa> lista;
    private Empresa seleccionado;

    private byte[] file;

    public EmpresaBean() {
        seleccionado = new Empresa();
    }

    public void insertar() {
        try {
            if (file != null) {
                seleccionado.setLogo(file);
            }
            empresaService.save(seleccionado);
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle msg = context.getApplication().getResourceBundle(context, "msg");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("successful_update"), ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
    }

    public void eliminar() {
        try {
            empresaService.remove(seleccionado);
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle msg = context.getApplication().getResourceBundle(context, "msg");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("successful_delete"), ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            file = event.getFile().getContents();
        } catch (Exception e) {
            file = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
    }

    public StreamedContent showFile() {
        StreamedContent content = null;
        if (file != null) {
            content = new DefaultStreamedContent(new ByteArrayInputStream(file));
        }
        return content;
    }

    public StreamedContent showFile(byte[] file) {
        StreamedContent content = null;
        if (file != null) {
            content = new DefaultStreamedContent(new ByteArrayInputStream(file));
        }
        return content;
    }

    public List<Empresa> getLista() {
        lista = empresaService.findAll("nombre");
        return lista;
    }

    public Empresa getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Empresa seleccionado) {
        this.seleccionado = seleccionado;
    }

    public void limpiar(ActionEvent actionEvent) {
        this.seleccionado = new Empresa();
        file = null;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

}
