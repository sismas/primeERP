package com.sismas.erp.beans;

import com.sismas.erp.dominio.Cliente;
import com.sismas.erp.dominio.Unidad;
import com.sismas.erp.facade.ClienteFacade;
import com.sismas.erp.facade.UnidadFacade;
import com.sismas.erp.facade.UnidadclienteFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

@Named("cliente")
@ViewScoped
public class ClienteBean implements Serializable {

    @EJB
    transient ClienteFacade clienteService;
    @EJB
    transient UnidadclienteFacade unidadclienteService;
    @EJB
    transient UnidadFacade unidadService;

    private LazyDataModel<Cliente> listaLazy;
    private Cliente seleccionado;

    private DualListModel<Unidad> listaUnidad;

    public ClienteBean() {
        seleccionado = new Cliente();
    }

    @PostConstruct
    public void init() {
        limpiar();
        this.listaLazy = new LazyDataModel<Cliente>() {
            private static final long serialVersionUID = 1L;

            @Override
            public List<Cliente> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                setRowCount(clienteService.count(filters));
                return clienteService.findLazy(first, pageSize, sortField, SortOrder.ASCENDING.equals(sortOrder), filters);
            }
        };
    }

    public void insertar() {
        try {
            clienteService.save(seleccionado, listaUnidad.getTarget());
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle msg = context.getApplication().getResourceBundle(context, "msg");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("successful_update"), ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
    }

    public void eliminar() {
        try {
            clienteService.remove(seleccionado);
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle msg = context.getApplication().getResourceBundle(context, "msg");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("successful_delete"), ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
    }

    public LazyDataModel<Cliente> getListaLazy() {
        return listaLazy;
    }

    public Cliente getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Cliente seleccionado) {
        this.seleccionado = seleccionado;
    }

    public void limpiar() {
        this.seleccionado = new Cliente();
        this.listaUnidad = new DualListModel<>(unidadService.findAll(), new ArrayList<Unidad>());
    }

    public void refreshListaUnidad(Cliente cliente) {
        List<Unidad> actuales = unidadclienteService.findUnidadByCliente(cliente);
        List<Unidad> disponibles = unidadService.findAll();
        disponibles.removeAll(actuales);
        this.listaUnidad = new DualListModel<>(disponibles, actuales);
    }

    public DualListModel<Unidad> getListaUnidad() {
        return listaUnidad;
    }

    public void setListaUnidad(DualListModel<Unidad> listaUnidad) {
        this.listaUnidad = listaUnidad;
    }

}
