package com.sismas.erp.beans;

import com.sismas.erp.dominio.Menu;
import com.sismas.erp.facade.MenuFacade;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

@Named("menu")
@ViewScoped
public class MenuBean implements Serializable {

    @EJB
    transient MenuFacade menuService;

    private LazyDataModel<Menu> listaLazy;
    private Menu seleccionado;

    public MenuBean() {
        seleccionado = new Menu();
    }

    @PostConstruct
    public void init() {
        this.listaLazy = new LazyDataModel<Menu>() {
            private static final long serialVersionUID = 1L;

            @Override
            public List<Menu> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                setRowCount(menuService.count(filters));
                return menuService.findLazy(first, pageSize, sortField, SortOrder.ASCENDING.equals(sortOrder), filters);
            }
        };
    }

    public void insertar() {
        try {
            menuService.save(seleccionado);
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle msg = context.getApplication().getResourceBundle(context, "msg");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("successful_update"), ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
    }

    public void eliminar() {
        try {
            menuService.remove(seleccionado);
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle msg = context.getApplication().getResourceBundle(context, "msg");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("successful_delete"), ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
    }

    public LazyDataModel<Menu> getListaLazy() {
        return listaLazy;
    }

    public void setListaLazy(LazyDataModel<Menu> listaLazy) {
        this.listaLazy = listaLazy;
    }

    public Menu getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Menu seleccionado) {
        this.seleccionado = seleccionado;
    }

    public void limpiar(ActionEvent actionEvent) {
        this.seleccionado = new Menu();
    }

}
