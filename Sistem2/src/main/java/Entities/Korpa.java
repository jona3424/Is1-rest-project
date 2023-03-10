/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jona
 */
@Entity
@Table(name = "korpa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Korpa.findAll", query = "SELECT k FROM Korpa k"),
    @NamedQuery(name = "Korpa.findByIdKorpe", query = "SELECT k FROM Korpa k WHERE k.idKorpe = :idKorpe"),
    @NamedQuery(name = "Korpa.findUkupnuCijenuByIdKorpe", query = "SELECT  k.ukupnaCijena FROM Korpa k WHERE k.idKorpe = :idKorp"),
    @NamedQuery(name = "Korpa.findByUkupnaCijena", query = "SELECT k FROM Korpa k WHERE k.ukupnaCijena = :ukupnaCijena")})
public class Korpa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idKorpe")
    private Integer idKorpe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ukupnaCijena")
    private float ukupnaCijena;
    @JoinColumn(name = "idKorisnika", referencedColumnName = "idKor")
    @ManyToOne(optional = false)
    private Korisnik idKorisnika;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "korpa")
    private List<ProizvodUKorpi> proizvodUKorpiList;

    public Korpa() {
    }

    public Korpa(Integer idKorpe) {
        this.idKorpe = idKorpe;
    }

    public Korpa(Integer idKorpe, float ukupnaCijena) {
        this.idKorpe = idKorpe;
        this.ukupnaCijena = ukupnaCijena;
    }

    public Integer getIdKorpe() {
        return idKorpe;
    }

    public void setIdKorpe(Integer idKorpe) {
        this.idKorpe = idKorpe;
    }

    public float getUkupnaCijena() {
        return ukupnaCijena;
    }

    public void setUkupnaCijena(float ukupnaCijena) {
        this.ukupnaCijena = ukupnaCijena;
    }

    public Korisnik getIdKorisnika() {
        return idKorisnika;
    }

    public void setIdKorisnika(Korisnik idKorisnika) {
        this.idKorisnika = idKorisnika;
    }

    @XmlTransient
    public List<ProizvodUKorpi> getProizvodUKorpiList() {
        return proizvodUKorpiList;
    }

    public void setProizvodUKorpiList(List<ProizvodUKorpi> proizvodUKorpiList) {
        this.proizvodUKorpiList = proizvodUKorpiList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKorpe != null ? idKorpe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Korpa)) {
            return false;
        }
        Korpa other = (Korpa) object;
        if ((this.idKorpe == null && other.idKorpe != null) || (this.idKorpe != null && !this.idKorpe.equals(other.idKorpe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Korpa[ idKorpe=" + idKorpe + " ]";
    }
    
}
