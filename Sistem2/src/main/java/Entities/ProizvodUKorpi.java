/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import java.text.DecimalFormat;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "proizvod_u_korpi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProizvodUKorpi.findAll", query = "SELECT p FROM ProizvodUKorpi p"),
    @NamedQuery(name = "ProizvodUKorpi.findByIdKorpe", query = "SELECT p FROM ProizvodUKorpi p WHERE p.proizvodUKorpiPK.idKorpe = :idKorpe"),
    @NamedQuery(name = "ProizvodUKorpi.findByIdKorpeAndIdProizvoda", query = "SELECT p FROM ProizvodUKorpi p WHERE p.proizvodUKorpiPK.idKorpe = :idKorpe and  p.proizvodUKorpiPK.idProizvoda = :idProizvoda"),
    @NamedQuery(name = "ProizvodUKorpi.findByIdProizvoda", query = "SELECT p FROM ProizvodUKorpi p WHERE p.proizvodUKorpiPK.idProizvoda = :idProizvoda"),
    @NamedQuery(name = "ProizvodUKorpi.findByKolicina", query = "SELECT p FROM ProizvodUKorpi p WHERE p.kolicina = :kolicina")})
public class ProizvodUKorpi implements Serializable {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProizvodUKorpiPK proizvodUKorpiPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kolicina")
    private int kolicina;
    @JoinColumn(name = "idKorpe", referencedColumnName = "idKorpe", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Korpa korpa;
    @JoinColumn(name = "idProizvoda", referencedColumnName = "idPro", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proizvod proizvod;

    public ProizvodUKorpi() {
    }

    public ProizvodUKorpi(ProizvodUKorpiPK proizvodUKorpiPK) {
        this.proizvodUKorpiPK = proizvodUKorpiPK;
    }

    public ProizvodUKorpi(ProizvodUKorpiPK proizvodUKorpiPK, int kolicina) {
        this.proizvodUKorpiPK = proizvodUKorpiPK;
        this.kolicina = kolicina;
    }

    public ProizvodUKorpi(int idKorpe, int idProizvoda) {
        this.proizvodUKorpiPK = new ProizvodUKorpiPK(idKorpe, idProizvoda);
    }

    public ProizvodUKorpiPK getProizvodUKorpiPK() {
        return proizvodUKorpiPK;
    }

    public void setProizvodUKorpiPK(ProizvodUKorpiPK proizvodUKorpiPK) {
        this.proizvodUKorpiPK = proizvodUKorpiPK;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public Korpa getKorpa() {
        return korpa;
    }

    public void setKorpa(Korpa korpa) {
        this.korpa = korpa;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proizvodUKorpiPK != null ? proizvodUKorpiPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProizvodUKorpi)) {
            return false;
        }
        ProizvodUKorpi other = (ProizvodUKorpi) object;
        if ((this.proizvodUKorpiPK == null && other.proizvodUKorpiPK != null) || (this.proizvodUKorpiPK != null && !this.proizvodUKorpiPK.equals(other.proizvodUKorpiPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.ProizvodUKorpi[ proizvodUKorpiPK=" + proizvodUKorpiPK + " ]";
    }
   
    
public void akumuliraj(float cijena){
    for (int i = 0; i < this.kolicina; i++) {
            this.korpa.setUkupnaCijena(this.korpa.getUkupnaCijena()+ cijena);
    }
    float parepare=(float) (Math.round(this.korpa.getUkupnaCijena() * 100.0) / 100.0);
    this.korpa.setUkupnaCijena(parepare);
}

public void akumuliraj(float cijena,int kolicina){
    for (int i = 0; i < kolicina; i++) {
            this.korpa.setUkupnaCijena(this.korpa.getUkupnaCijena()+ cijena);
    }
    float parepare=(float) (Math.round(this.korpa.getUkupnaCijena() * 100.0) / 100.0);
    this.korpa.setUkupnaCijena(parepare);
}
}
