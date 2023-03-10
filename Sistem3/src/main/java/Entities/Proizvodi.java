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
@Table(name = "proizvodi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proizvodi.findAll", query = "SELECT p FROM Proizvodi p"),
    @NamedQuery(name = "Proizvodi.findByIdPro", query = "SELECT p FROM Proizvodi p WHERE p.idPro = :idPro"),
    @NamedQuery(name = "Proizvodi.findByNaziv", query = "SELECT p FROM Proizvodi p WHERE p.naziv = :naziv"),
    @NamedQuery(name = "Proizvodi.findByCijena", query = "SELECT p FROM Proizvodi p WHERE p.cijena = :cijena"),
    @NamedQuery(name = "Proizvodi.findByPopust", query = "SELECT p FROM Proizvodi p WHERE p.popust = :popust")})
public class Proizvodi implements Serializable {

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
    @Column(name = "cijena")
    private float cijena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "popust")
    private float popust;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProizvoda")
    private List<ProizvodiUKorpi> proizvodiUKorpiList;
    @JoinColumn(name = "prodavac", referencedColumnName = "idKor")
    @ManyToOne(optional = false)
    private  Korisnici prodavac;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProizvoda")
    private List<Recenzije> recenzijeList;
    @OneToMany(mappedBy = "idProizvoda")
    private List<Stavke> stavkeList;

    public Proizvodi() {
    }

    public Proizvodi(Integer idPro) {
        this.idPro = idPro;
    }

    public Proizvodi(Integer idPro, String naziv, String opis, float cijena, float popust) {
        this.idPro = idPro;
        this.naziv = naziv;
        this.opis = opis;
        this.cijena = cijena;
        this.popust = popust;
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

    public float getCijena() {
        return cijena;
    }

    public void setCijena(float cijena) {
        this.cijena = cijena;
    }

    public float getPopust() {
        return popust;
    }

    public void setPopust(float popust) {
        this.popust = popust;
    }

    @XmlTransient
    public List<ProizvodiUKorpi> getProizvodiUKorpiList() {
        return proizvodiUKorpiList;
    }

    public void setProizvodiUKorpiList(List<ProizvodiUKorpi> proizvodiUKorpiList) {
        this.proizvodiUKorpiList = proizvodiUKorpiList;
    }
    public Korisnici getProdavac() {
        return prodavac;
    }

    public void setProdavac(Korisnici prodavac) {
        this.prodavac = prodavac;
    }

    @XmlTransient
    public List<Recenzije> getRecenzijeList() {
        return recenzijeList;
    }

    public void setRecenzijeList(List<Recenzije> recenzijeList) {
        this.recenzijeList = recenzijeList;
    }

    @XmlTransient
    public List<Stavke> getStavkeList() {
        return stavkeList;
    }

    public void setStavkeList(List<Stavke> stavkeList) {
        this.stavkeList = stavkeList;
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
        if (!(object instanceof Proizvodi)) {
            return false;
        }
        Proizvodi other = (Proizvodi) object;
        if ((this.idPro == null && other.idPro != null) || (this.idPro != null && !this.idPro.equals(other.idPro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Proizvodi[ idPro=" + idPro + " ]";
    }
    
}
