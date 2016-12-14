package com.sismas.erp.dominio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Ismael Juchazara
 */
@Embeddable
public class PerfilmenuPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_perfil")
    private int idPerfil;
    @Basic(optional = false)
    @Column(name = "id_menu")
    private short idMenu;
    @Basic(optional = false)
    @Column(name = "id_modulo")
    private short idModulo;

    /**
     * Default constructor
     */
    public PerfilmenuPK() {
    }

    /**
     * @param idPerfil El id del perfil relacionado
     * @param menuPK El objeto menuPK llave del menu relacionado
     */
    public PerfilmenuPK(int idPerfil, MenuPK menuPK) {
        this.idPerfil = idPerfil;
        this.idMenu = menuPK.getIdMenu();
        this.idModulo = menuPK.getIdModulo();
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
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
        hash += idPerfil;
        hash += (int) idMenu;
        hash += (int) idModulo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PerfilmenuPK)) {
            return false;
        }
        PerfilmenuPK other = (PerfilmenuPK) object;
        if (this.idPerfil != other.idPerfil) {
            return false;
        }
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
        return "com.sismas.erp.dominio.PerfilmenuPK[ idPerfil=" + idPerfil + ", idMenu=" + idMenu + ", idModulo=" + idModulo + " ]";
    }

}
