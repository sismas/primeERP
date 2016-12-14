package com.sismas.erp.dominio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Ismael Juchazara
 */
@Embeddable
public class UnidadclientePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_cliente")
    private int idCliente;
    @Basic(optional = false)
    @Column(name = "id_unidad")
    private int idUnidad;
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private short idEmpresa;

    /**
     * Default constructor
     */
    public UnidadclientePK() {
    }

    /**
     * @param idCliente El id del cliente relacionado
     * @param unidadPK El objeto unidadPK llave de la unidad relacionada
     */
    public UnidadclientePK(int idCliente, UnidadPK unidadPK) {
        this.idCliente = idCliente;
        this.idUnidad = unidadPK.getIdUnidad();
        this.idEmpresa = unidadPK.getIdEmpresa();
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(int idUnidad) {
        this.idUnidad = idUnidad;
    }

    public short getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(short idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += idCliente;
        hash += idUnidad;
        hash += (int) idEmpresa;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UnidadclientePK)) {
            return false;
        }
        UnidadclientePK other = (UnidadclientePK) object;
        if (this.idCliente != other.idCliente) {
            return false;
        }
        if (this.idUnidad != other.idUnidad) {
            return false;
        }
        if (this.idEmpresa != other.idEmpresa) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sismas.erp.dominio.UnidadclientePK[ idCliente=" + idCliente + ", idUnidad=" + idUnidad + ", idEmpresa=" + idEmpresa + " ]";
    }

}
