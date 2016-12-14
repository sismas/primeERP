package com.sismas.erp.beans;

import com.sismas.erp.dominio.Menu;
import com.sismas.erp.dominio.Modulo;
import com.sismas.erp.facade.MenuFacade;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

@Named("principal")
@SessionScoped
public class PrincipalBean implements Serializable {

    @EJB
    transient MenuFacade menuService;
    private Menu menuElegido;
    private String menuContenido;
    private String localeCode;
    private static Map<String, Object> paises;

    static {
        paises = new LinkedHashMap<>();
        Locale espanol = new Locale("ES");
        paises.put("Español", espanol);
        paises.put("English", Locale.ENGLISH);

    }

    public PrincipalBean() {
        menuContenido = "";
    }

    public List<Menu> getMenuPermitido(Modulo modulo) {
        return menuService.findByModulo(modulo);
    }

    public void irAOpcionMenu(Menu menu) {
        this.setMenuContenido("/views" + menu.getDireccion() + ".xhtml");
        this.setMenuElegido(menu);
    }

    public Menu getMenuElegido() {
        return menuElegido;
    }

    public void setMenuElegido(Menu menuElegido) {
        this.menuElegido = menuElegido;
    }

    public String getMenuContenido() {
        return menuContenido;
    }

    public void setMenuContenido(String menuContenido) {
        this.menuContenido = menuContenido;
    }

    public Map<String, Object> getCountriesInMap() {
        return paises;
    }

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    public void countryLocaleCodeChanged(ValueChangeEvent e) {
        String newLocaleValue = e.getNewValue().toString();
        /*paises.entrySet().stream().filter((entry) -> (entry.getValue().toString().equals(newLocaleValue))).forEach((entry) -> {
            FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
        });*/
    }

}
