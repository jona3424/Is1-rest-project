/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jona
 */
@Entity
@Table(name = "narudzbine")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Narudzbine.findAll", query = "SELECT n FROM Narudzbine n"),
    @NamedQuery(name = "Narudzbine.findByIdNar", query = "SELECT n FROM Narudzbine n WHERE n.idNar = :idNar"),
    @NamedQuery(name = "Narudzbine.findByUkupnaCijena", query = "SELECT n FROM Narudzbine n WHERE n.ukupnaCijena = :ukupnaCijena"),
    @NamedQuery(name = "Narudzbine.findByDatumVrijeme", query = "SELECT n FROM Narudzbine n WHERE n.datumVrijeme = :datumVrijeme"),
    @NamedQuery(name = "Narudzbine.findByAdresaDostave", query = "SELECT n FROM Narudzbine n WHERE n.adresaDostave = :adresaDostave"),
    @NamedQuery(name = "Narudzbine.findByGradDostave", query = "SELECT n FROM Narudzbine n WHERE n.gradDostave = :gradDostave")})
public class Narudzbine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idNar")
    private Integer idNar;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ukupnaCijena")
    private Float ukupnaCijena;
    @Column(name = "datumVrijeme")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumVrijeme;
    @Size(max = 255)
    @Column(name = "adresaDostave")
    private String adresaDostave;
    @Size(max = 100)
    @Column(name = "gradDostave")
    private String gradDostave;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNarudzbine")
    private List<Transakcije> transakcijeList;
    @OneToMany(mappedBy = "idNarudzbine")
    private List<Stavke> stavkeList;

    public Narudzbine() {
    }

    public Narudzbine(Integer idNar) {
        this.idNar = idNar;
    }

    public Integer getIdNar() {
        return idNar;
    }

    public void setIdNar(Integer idNar) {
        this.idNar = idNar;
    }

    public Float getUkupnaCijena() {
        return ukupnaCijena;
    }

    public void setUkupnaCijena(Float ukupnaCijena) {
        this.ukupnaCijena = ukupnaCijena;
    }

    public Date getDatumVrijeme() {
        return datumVrijeme;
    }

    public void setDatumVrijeme(Date datumVrijeme) {
        this.datumVrijeme = datumVrijeme;
    }

    public String getAdresaDostave() {
        return adresaDostave;
    }

    public void setAdresaDostave(String adresaDostave) {
        this.adresaDostave = adresaDostave;
    }

    public String getGradDostave() {
        return gradDostave;
    }

    public void setGradDostave(String gradDostave) {
        this.gradDostave = gradDostave;
    }

    @XmlTransient
    public List<Transakcije> getTransakcijeList() {
        return transakcijeList;
    }

    public void setTransakcijeList(List<Transakcije> transakcijeList) {
        this.transakcijeList = transakcijeList;
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
        hash += (idNar != null ? idNar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Narudzbine)) {
            return false;
        }
        Narudzbine other = (Narudzbine) object;
        if ((this.idNar == null && other.idNar != null) || (this.idNar != null && !this.idNar.equals(other.idNar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Narudzbine[ idNar=" + idNar + " ]";
    }
    
}
