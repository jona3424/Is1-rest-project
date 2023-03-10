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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jona
 */
@Entity
@Table(name = "korisnici")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Korisnici.findAll", query = "SELECT k FROM Korisnici k"),
    @NamedQuery(name = "Korisnici.findByIdKor", query = "SELECT k FROM Korisnici k WHERE k.idKor = :idKor"),
    @NamedQuery(name = "Korisnici.findByKorisnickoIme", query = "SELECT k FROM Korisnici k WHERE k.korisnickoIme = :korisnickoIme"),
    @NamedQuery(name = "Korisnici.findByLozinka", query = "SELECT k FROM Korisnici k WHERE k.lozinka = :lozinka"),
    @NamedQuery(name = "Korisnici.findByImePrezime", query = "SELECT k FROM Korisnici k WHERE k.imePrezime = :imePrezime"),
    @NamedQuery(name = "Korisnici.findByNovac", query = "SELECT k FROM Korisnici k WHERE k.novac = :novac")})
public class Korisnici implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idKor")
    private Integer idKor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "korisnickoIme")
    private String korisnickoIme;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "lozinka")
    private String lozinka;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "imePrezime")
    private String imePrezime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "novac")
    private float novac;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prodavac")
    private List<Proizvodi> proizvodiList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKupca")
    private List<Transakcije> transakcijeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKorisnika")
    private List<Korpe> korpeList;

    public Korisnici() {
    }

    public Korisnici(Integer idKor) {
        this.idKor = idKor;
    }

    public Korisnici(Integer idKor, String korisnickoIme, String lozinka, String imePrezime, float novac) {
        this.idKor = idKor;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.imePrezime = imePrezime;
        this.novac = novac;
    }

    public Integer getIdKor() {
        return idKor;
    }

    public void setIdKor(Integer idKor) {
        this.idKor = idKor;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public float getNovac() {
        return novac;
    }

    public void setNovac(float novac) {
        this.novac = novac;
    }

    @XmlTransient
    public List<Proizvodi> getProizvodiList() {
        return proizvodiList;
    }

    public void setProizvodiList(List<Proizvodi> proizvodiList) {
        this.proizvodiList = proizvodiList;
    }

    @XmlTransient
    public List<Transakcije> getTransakcijeList() {
        return transakcijeList;
    }

    public void setTransakcijeList(List<Transakcije> transakcijeList) {
        this.transakcijeList = transakcijeList;
    }

    @XmlTransient
    public List<Korpe> getKorpeList() {
        return korpeList;
    }

    public void setKorpeList(List<Korpe> korpeList) {
        this.korpeList = korpeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKor != null ? idKor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Korisnici)) {
            return false;
        }
        Korisnici other = (Korisnici) object;
        if ((this.idKor == null && other.idKor != null) || (this.idKor != null && !this.idKor.equals(other.idKor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Korisnici[ idKor=" + idKor + " ]";
    }
    
}
