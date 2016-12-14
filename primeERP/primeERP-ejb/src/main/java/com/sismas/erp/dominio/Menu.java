package com.sismas.erp.dominio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ismael Juchazara
 */
@Entity
@Table(name = "sec_menu")
@XmlRootElement
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MenuPK menuPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 1)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "id_modulo", referencedColumnName = "id_modulo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Modulo modulo;
    @JoinColumn(name = "id_icono", referencedColumnName = "id_icono")
    @ManyToOne
    private Icono idIcono;

    public MenuPK getMenuPK() {
        return menuPK;
    }

    public void setMenuPK(MenuPK menuPK) {
        this.menuPK = menuPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public Icono getIdIcono() {
        return idIcono;
    }

    public void setIdIcono(Icono idIcono) {
        this.idIcono = idIcono;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menuPK != null ? menuPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.menuPK == null && other.menuPK != null) || (this.menuPK != null && !this.menuPK.equals(other.menuPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sismas.erp.dominio.Menu[ menuPK=" + menuPK + " ]";
    }

}
