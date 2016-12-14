package com.sismas.erp.dominio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Ismael Juchazara
 */
@Embeddable
public class UnidadPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_unidad")
    private int idUnidad;
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private short idEmpresa;

    /**
     * Default constructor
     */
    public UnidadPK() {
    }

    /**
     * @param idUnidad El id de la unidad
     * @param idEmpresa El id de la empresa relacionada
     */
    public UnidadPK(int idUnidad, short idEmpresa) {
        this.idUnidad = idUnidad;
        this.idEmpresa = idEmpresa;
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
        hash += idUnidad;
        hash += (int) idEmpresa;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UnidadPK)) {
            return false;
        }
        UnidadPK other = (UnidadPK) object;
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
        return "com.sismas.erp.dominio.UnidadPK[ idUnidad=" + idUnidad + ", idEmpresa=" + idEmpresa + " ]";
    }

}
