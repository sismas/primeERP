package com.sismas.erp.dominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "pay_unidad")
@XmlRootElement
public class Unidad implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidad")
    private List<Unidadcliente> unidadclienteList;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UnidadPK unidadPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 1)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empresa empresa;

    public UnidadPK getUnidadPK() {
        return unidadPK;
    }

    public void setUnidadPK(UnidadPK unidadPK) {
        this.unidadPK = unidadPK;
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (unidadPK != null ? unidadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Unidad)) {
            return false;
        }
        Unidad other = (Unidad) object;
        if ((this.unidadPK == null && other.unidadPK != null) || (this.unidadPK != null && !this.unidadPK.equals(other.unidadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sismas.erp.dominio.Unidad[ unidadPK=" + unidadPK + " ]";
    }

    @XmlTransient
    public List<Unidadcliente> getUnidadclienteList() {
        return unidadclienteList;
    }

    public void setUnidadclienteList(List<Unidadcliente> unidadclienteList) {
        this.unidadclienteList = unidadclienteList;
    }

}
