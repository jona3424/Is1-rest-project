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
@Table(name = "korpe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Korpe.findAll", query = "SELECT k FROM Korpe k"),
    @NamedQuery(name = "Korpe.findByIdKorp", query = "SELECT k FROM Korpe k WHERE k.idKorp = :idKorp"),
    @NamedQuery(name = "Korpe.findUkupnuCijenuByIdKorpe", query = "SELECT  k.ukupnaCijena FROM Korpe k WHERE k.idKorp = :idKorp"),
    @NamedQuery(name = "Korpe.findByUkupnaCijena", query = "SELECT k FROM Korpe k WHERE k.ukupnaCijena = :ukupnaCijena")})
public class Korpe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idKorp")
    private Integer idKorp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ukupna_cijena")
    private float ukupnaCijena;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKorpe")
    private List<ProizvodiUKorpi> proizvodiUKorpiList;
    @JoinColumn(name = "id_korisnika", referencedColumnName = "idKor")
    @ManyToOne(optional = false)
    private Korisnici idKorisnika;

    public Korpe() {
    }

    public Korpe(Integer idKorp) {
        this.idKorp = idKorp;
    }

    public Korpe(Integer idKorp, float ukupnaCijena) {
        this.idKorp = idKorp;
        this.ukupnaCijena = ukupnaCijena;
    }

    public Integer getIdKorp() {
        return idKorp;
    }

    public void setIdKorp(Integer idKorp) {
        this.idKorp = idKorp;
    }

    public float getUkupnaCijena() {
        return ukupnaCijena;
    }

    public void setUkupnaCijena(float ukupnaCijena) {
        this.ukupnaCijena = ukupnaCijena;
    }

    @XmlTransient
    public List<ProizvodiUKorpi> getProizvodiUKorpiList() {
        return proizvodiUKorpiList;
    }

    public void setProizvodiUKorpiList(List<ProizvodiUKorpi> proizvodiUKorpiList) {
        this.proizvodiUKorpiList = proizvodiUKorpiList;
    }

    public Korisnici getIdKorisnika() {
        return idKorisnika;
    }

    public void setIdKorisnika(Korisnici idKorisnika) {
        this.idKorisnika = idKorisnika;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKorp != null ? idKorp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Korpe)) {
            return false;
        }
        Korpe other = (Korpe) object;
        if ((this.idKorp == null && other.idKorp != null) || (this.idKorp != null && !this.idKorp.equals(other.idKorp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Korpe[ idKorp=" + idKorp + " ]";
    }
    
}
