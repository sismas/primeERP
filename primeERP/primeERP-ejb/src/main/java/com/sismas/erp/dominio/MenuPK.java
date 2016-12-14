package com.sismas.erp.dominio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Ismael Juchazara
 */
@Embeddable
public class MenuPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_menu")
    private short idMenu;
    @Basic(optional = false)
    @Column(name = "id_modulo")
    private short idModulo;

    /**
     * Default constructor
     */
    public MenuPK() {
    }

    /**
     * @param idMenu El id del menu
     * @param idModulo El id del modulo relacionado
     */
    public MenuPK(short idMenu, short idModulo) {
        this.idMenu = idMenu;
        this.idModulo = idModulo;
    }

    public short getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(short idMenu) {
        this.idMenu = idMenu;
    }

    public short getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(short idModulo) {
        this.idModulo = idModulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idMenu;
        hash += (int) idModulo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MenuPK)) {
            return false;
        }
        MenuPK other = (MenuPK) object;
        if (this.idMenu != other.idMenu) {
            return false;
        }
        if (this.idModulo != other.idModulo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sismas.erp.dominio.MenuPK[ idMenu=" + idMenu + ", idModulo=" + idModulo + " ]";
    }

}
