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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "proizvod")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proizvod.findAll", query = "SELECT p FROM Proizvod p"),
    @NamedQuery(name = "Proizvod.findByIdPro", query = "SELECT p FROM Proizvod p WHERE p.idPro = :idPro"),
    @NamedQuery(name = "Proizvod.findByNaziv", query = "SELECT p FROM Proizvod p WHERE p.naziv = :naziv"),
    @NamedQuery(name = "Proizvod.findByPopust", query = "SELECT p FROM Proizvod p WHERE p.popust = :popust"),
    @NamedQuery(name = "Proizvod.findByCijena", query = "SELECT p FROM Proizvod p WHERE p.cijena = :cijena")})
public class Proizvod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPro")
    private Integer idPro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "naziv")
    private String naziv;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "opis")
    private String opis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "popust")
    private float popust;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cijena")
    private float cijena;
    @JoinColumn(name = "kategorija_kljuc", referencedColumnName = "idKat")
    @ManyToOne(optional = false)
    private Kategorija kategorijaKljuc;
    @JoinColumn(name = "prodavac", referencedColumnName = "idKor")
    @ManyToOne(optional = false)
    private Korisnik prodavac;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proizvod")
    private List<ProizvodUKorpi> proizvodUKorpiList;

    public Proizvod() {
    }

    public Proizvod(Integer idPro) {
        this.idPro = idPro;
    }

    public Proizvod(Integer idPro, String naziv, String opis, float popust, float cijena) {
        this.idPro = idPro;
        this.naziv = naziv;
        this.opis = opis;
        this.popust = popust;
        this.cijena = cijena;
    }

    public Integer getIdPro() {
        return idPro;
    }

    public void setIdPro(Integer idPro) {
        this.idPro = idPro;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public float getPopust() {
        return popust;
    }

    public void setPopust(float popust) {
        this.popust = popust;
    }

    public float getCijena() {
        return cijena;
    }

    public void setCijena(float cijena) {
        this.cijena = cijena;
    }

    public Kategorija getKategorijaKljuc() {
        return kategorijaKljuc;
    }

    public void setKategorijaKljuc(Kategorija kategorijaKljuc) {
        this.kategorijaKljuc = kategorijaKljuc;
    }

    public Korisnik getProdavac() {
        return prodavac;
    }

    public void setProdavac(Korisnik prodavac) {
        this.prodavac = prodavac;
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
        hash += (idPro != null ? idPro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proizvod)) {
            return false;
        }
        Proizvod other = (Proizvod) object;
        if ((this.idPro == null && other.idPro != null) || (this.idPro != null && !this.idPro.equals(other.idPro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Proizvod[ idPro=" + idPro + " ]";
    }
    
}
