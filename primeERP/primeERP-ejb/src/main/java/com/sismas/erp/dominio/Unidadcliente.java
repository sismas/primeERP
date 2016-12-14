package com.sismas.erp.dominio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ismael Juchazara
 */
@Entity
@Table(name = "pay_unidadcliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unidadcliente.findUnidadByCliente", query = "SELECT u.unidad FROM Unidadcliente u WHERE u.cliente = :cliente AND u.estado = :estado")
})
public class Unidadcliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UnidadclientePK unidadclientePK;
    @Size(max = 1)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumns({
        @JoinColumn(name = "id_unidad", referencedColumnName = "id_unidad", insertable = false, updatable = false)
        , @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Unidad unidad;

    public UnidadclientePK getUnidadclientePK() {
        return unidadclientePK;
    }

    public void setUnidadclientePK(UnidadclientePK unidadclientePK) {
        this.unidadclientePK = unidadclientePK;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (unidadclientePK != null ? unidadclientePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Unidadcliente)) {
            return false;
        }
        Unidadcliente other = (Unidadcliente) object;
        if ((this.unidadclientePK == null && other.unidadclientePK != null) || (this.unidadclientePK != null && !this.unidadclientePK.equals(other.unidadclientePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sismas.erp.dominio.Unidadcliente[ unidadclientePK=" + unidadclientePK + " ]";
    }

}
