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
@Table(name = "sec_perfilmenu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perfilmenu.findMenuByPerfil", query = "SELECT p.menu FROM Perfilmenu p WHERE p.perfil = :perfil AND p.estado = :estado")
})
public class Perfilmenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PerfilmenuPK perfilmenuPK;
    @Size(max = 1)
    @Column(name = "estado")
    private String estado;
    @JoinColumns({
        @JoinColumn(name = "id_menu", referencedColumnName = "id_menu", insertable = false, updatable = false)
        ,
        @JoinColumn(name = "id_modulo", referencedColumnName = "id_modulo", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Menu menu;
    @JoinColumn(name = "id_perfil", referencedColumnName = "id_perfil", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Perfil perfil;

    public PerfilmenuPK getPerfilmenuPK() {
        return perfilmenuPK;
    }

    public void setPerfilmenuPK(PerfilmenuPK perfilmenuPK) {
        this.perfilmenuPK = perfilmenuPK;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perfilmenuPK != null ? perfilmenuPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Perfilmenu)) {
            return false;
        }
        Perfilmenu other = (Perfilmenu) object;
        if ((this.perfilmenuPK == null && other.perfilmenuPK != null) || (this.perfilmenuPK != null && !this.perfilmenuPK.equals(other.perfilmenuPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sismas.erp.dominio.Perfilmenu[ perfilmenuPK=" + perfilmenuPK + " ]";
    }

}
