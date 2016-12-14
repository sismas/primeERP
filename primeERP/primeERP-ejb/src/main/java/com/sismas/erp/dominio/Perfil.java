package com.sismas.erp.dominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "sec_perfil")
@XmlRootElement
public class Perfil implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perfil")
    private List<Perfilmenu> secPerfilmenuList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_perfil")
    private Short idPerfil;
    @Size(max = 25)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 1)
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perfil")
    private List<Perfilmenu> perfilmenuList;
    @OneToMany(mappedBy = "perfil")
    private List<Usuario> usuarioList;

    public Short getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Short idPerfil) {
        this.idPerfil = idPerfil;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Perfilmenu> getPerfilmenuList() {
        return perfilmenuList;
    }

    public void setPerfilmenuList(List<Perfilmenu> perfilmenuList) {
        this.perfilmenuList = perfilmenuList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerfil != null ? idPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Perfil)) {
            return false;
        }
        Perfil other = (Perfil) object;
        if ((this.idPerfil == null && other.idPerfil != null) || (this.idPerfil != null && !this.idPerfil.equals(other.idPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sismas.erp.dominio.Perfil[ idPerfil=" + idPerfil + " ]";
    }

    @XmlTransient
    public List<Perfilmenu> getSecPerfilmenuList() {
        return secPerfilmenuList;
    }

    public void setSecPerfilmenuList(List<Perfilmenu> secPerfilmenuList) {
        this.secPerfilmenuList = secPerfilmenuList;
    }

}
