package com.sismas.erp.dominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Ismael Juchazara
 */
@Entity
@Table(name = "ui_icono")
@XmlRootElement
public class Icono implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_icono")
    private Integer idIcono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 15)
    @Column(name = "prefijo")
    private String prefijo;
    @OneToMany(mappedBy = "idIcono")
    private List<Menu> menuList;

    public Integer getIdIcono() {
        return idIcono;
    }

    public void setIdIcono(Integer idIcono) {
        this.idIcono = idIcono;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    @XmlTransient
    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIcono != null ? idIcono.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Icono)) {
            return false;
        }
        Icono other = (Icono) object;
        if ((this.idIcono == null && other.idIcono != null) || (this.idIcono != null && !this.idIcono.equals(other.idIcono))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sismas.erp.dominio.Icono[ idIcono=" + idIcono + " ]";
    }

}
