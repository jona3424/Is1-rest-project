/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jona
 */
@Entity
@Table(name = "proizvodi_u_korpi")
@XmlRootElement

@NamedQueries({
    @NamedQuery(name = "ProizvodiUKorpi.findAll", query = "SELECT p FROM ProizvodiUKorpi p"),
    @NamedQuery(name = "ProizvodiUKorpi.findByIdPUK", query = "SELECT p FROM ProizvodiUKorpi p WHERE p.idPUK = :idPUK"),
    @NamedQuery(name = "ProizvodiUKorpi.findByKolicina", query = "SELECT p FROM ProizvodiUKorpi p WHERE p.kolicina = :kolicina"),
    @NamedQuery(name = "ProizvodiUKorpi.findByIdKorpe", query = "SELECT p FROM ProizvodiUKorpi p WHERE p.idKorpe.idKorp = :idKorpe"),
    @NamedQuery(name = "ProizvodiUKorpi.findByIdKorpeAndKolicinaNotZero", query = "SELECT p FROM ProizvodiUKorpi p WHERE p.idKorpe.idKorp = :idKorpe and  p.kolicina>0"),
    @NamedQuery(name = "ProizvodiUKorpi.findByIdKorpeandIdProizvoda", query = "SELECT p FROM ProizvodiUKorpi p WHERE p.idKorpe.idKorp = :idK and p.idProizvoda.idPro = :idP"),
    @NamedQuery(name = "ProizvodiUKorpi.findByIdProizvoda", query = "SELECT p FROM ProizvodiUKorpi p WHERE p.idProizvoda.idPro = :idP")})
public class ProizvodiUKorpi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPUK")
    private Integer idPUK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kolicina")
    private int kolicina;
    @JoinColumn(name = "id_korpe", referencedColumnName = "idKorp")
    @ManyToOne(optional = false)
    private Korpe idKorpe;
    @JoinColumn(name = "id_proizvoda", referencedColumnName = "idPro")
    @ManyToOne(optional = false)
    private Proizvodi idProizvoda;

    public ProizvodiUKorpi() {
    }

    public ProizvodiUKorpi(Integer idPUK) {
        this.idPUK = idPUK;
    }

    public ProizvodiUKorpi(Integer idPUK, int kolicina, float jedinicnaCijena) {
        this.idPUK = idPUK;
        this.kolicina = kolicina;
    }

    public Integer getIdPUK() {
        return idPUK;
    }

    public void setIdPUK(Integer idPUK) {
        this.idPUK = idPUK;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

  

    public Korpe getIdKorpe() {
        return idKorpe;
    }

    public void setIdKorpe(Korpe idKorpe) {
        this.idKorpe = idKorpe;
    }

    public Proizvodi getIdProizvoda() {
        return idProizvoda;
    }

    public void setIdProizvoda(Proizvodi idProizvoda) {
        this.idProizvoda = idProizvoda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPUK != null ? idPUK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProizvodiUKorpi)) {
            return false;
        }
        ProizvodiUKorpi other = (ProizvodiUKorpi) object;
        if ((this.idPUK == null && other.idPUK != null) || (this.idPUK != null && !this.idPUK.equals(other.idPUK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.ProizvodiUKorpi[ idPUK=" + idPUK + " ]";
    }
   
    public void akumuliraj(float cijena){
        for (int i = 0; i < this.kolicina; i++) {
                this.idKorpe.setUkupnaCijena(this.idKorpe.getUkupnaCijena()+ cijena);
        }
        float parepare=(float) (Math.round(this.idKorpe.getUkupnaCijena() * 100.0) / 100.0);
        this.idKorpe.setUkupnaCijena(parepare);
}
    public void akumuliraj(float cijena,int kolicina){
        for (int i = 0; i < kolicina; i++) {
                this.idKorpe.setUkupnaCijena(this.idKorpe.getUkupnaCijena()+ cijena);
        }
        float parepare=(float) (Math.round(this.idKorpe.getUkupnaCijena() * 100.0) / 100.0);
        this.idKorpe.setUkupnaCijena(parepare);
}
}
