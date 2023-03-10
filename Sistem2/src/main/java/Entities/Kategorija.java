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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jona
 */
@Entity
@Table(name = "kategorija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kategorija.findAll", query = "SELECT k FROM Kategorija k"),
    @NamedQuery(name = "Kategorija.findByIdKat", query = "SELECT k FROM Kategorija k WHERE k.idKat = :idKat"),
    @NamedQuery(name = "Kategorija.findByNaziv", query = "SELECT k FROM Kategorija k WHERE k.naziv = :naziv")})
public class Kategorija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idKat")
    private Integer idKat;
    @Size(max = 255)
    @Column(name = "naziv")
    private String naziv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kategorijaKljuc")
    private List<Proizvod> proizvodList;
    @OneToMany(mappedBy = "roditeljskaKategorija")
    private List<Kategorija> kategorijaList;
    @JoinColumn(name = "roditeljska_kategorija", referencedColumnName = "idKat")
    @ManyToOne
    private Kategorija roditeljskaKategorija;

    public Kategorija() {
    }

    public Kategorija(Integer idKat) {
        this.idKat = idKat;
    }

    public Integer getIdKat() {
        return idKat;
    }

    public void setIdKat(Integer idKat) {
        this.idKat = idKat;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @XmlTransient
    public List<Proizvod> getProizvodList() {
        return proizvodList;
    }

    public void setProizvodList(List<Proizvod> proizvodList) {
        this.proizvodList = proizvodList;
    }

    @XmlTransient
    public List<Kategorija> getKategorijaList() {
        return kategorijaList;
    }

    public void setKategorijaList(List<Kategorija> kategorijaList) {
        this.kategorijaList = kategorijaList;
    }

    public Kategorija getRoditeljskaKategorija() {
        return roditeljskaKategorija;
    }

    public void setRoditeljskaKategorija(Kategorija roditeljskaKategorija) {
        this.roditeljskaKategorija = roditeljskaKategorija;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKat != null ? idKat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kategorija)) {
            return false;
        }
        Kategorija other = (Kategorija) object;
        if ((this.idKat == null && other.idKat != null) || (this.idKat != null && !this.idKat.equals(other.idKat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Kategorija[ idKat=" + idKat + " ]";
    }
    
}
