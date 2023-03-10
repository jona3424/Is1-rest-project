/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jona
 */
@Embeddable
public class ProizvodUKorpiPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idKorpe")
    private int idKorpe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idProizvoda")
    private int idProizvoda;

    public ProizvodUKorpiPK() {
    }

    public ProizvodUKorpiPK(int idKorpe, int idProizvoda) {
        this.idKorpe = idKorpe;
        this.idProizvoda = idProizvoda;
    }

    public int getIdKorpe() {
        return idKorpe;
    }

    public void setIdKorpe(int idKorpe) {
        this.idKorpe = idKorpe;
    }

    public int getIdProizvoda() {
        return idProizvoda;
    }

    public void setIdProizvoda(int idProizvoda) {
        this.idProizvoda = idProizvoda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idKorpe;
        hash += (int) idProizvoda;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProizvodUKorpiPK)) {
            return false;
        }
        ProizvodUKorpiPK other = (ProizvodUKorpiPK) object;
        if (this.idKorpe != other.idKorpe) {
            return false;
        }
        if (this.idProizvoda != other.idProizvoda) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.ProizvodUKorpiPK[ idKorpe=" + idKorpe + ", idProizvoda=" + idProizvoda + " ]";
    }
    
}
