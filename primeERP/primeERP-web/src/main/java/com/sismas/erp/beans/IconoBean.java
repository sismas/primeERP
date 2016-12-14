package com.sismas.erp.beans;

import com.sismas.erp.dominio.Icono;
import com.sismas.erp.facade.IconoFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("icono")
@ViewScoped
public class IconoBean implements Serializable {

    @EJB
    transient IconoFacade iconoService;

    private List<Icono> lista;

    public List<Icono> getLista() {
        lista = iconoService.findAll("codigo");
        return lista;
    }

}
