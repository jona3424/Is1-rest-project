/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jona
 */
@Entity
@Table(name = "transakcije")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transakcije.findAll", query = "SELECT t FROM Transakcije t"),
    @NamedQuery(name = "Transakcije.findById", query = "SELECT t FROM Transakcije t WHERE t.id = :id"),
    @NamedQuery(name = "Transakcije.findByIznos", query = "SELECT t FROM Transakcije t WHERE t.iznos = :iznos"),
    @NamedQuery(name = "Transakcije.findByIdKup", query = "SELECT t FROM Transakcije t WHERE t.idKupca.idKor = :username"),    
    @NamedQuery(name = "Transakcije.findByVrijemePlacanja", query = "SELECT t FROM Transakcije t WHERE t.vrijemePlacanja = :vrijemePlacanja")})
public class Transakcije implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iznos")
    private float iznos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vrijeme_placanja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vrijemePlacanja;
    @JoinColumn(name = "id_kupca", referencedColumnName = "idKor")
    @ManyToOne(optional = false)
    private Korisnici idKupca;
    @JoinColumn(name = "id_narudzbine", referencedColumnName = "idNar")
    @ManyToOne(optional = false)
    private Narudzbine idNarudzbine;

    public Transakcije() {
    }

    public Transakcije(Integer id) {
        this.id = id;
    }

    public Transakcije(Integer id, float iznos, Date vrijemePlacanja) {
        this.id = id;
        this.iznos = iznos;
        this.vrijemePlacanja = vrijemePlacanja;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getIznos() {
        return iznos;
    }

    public void setIznos(float iznos) {
        this.iznos = iznos;
    }

    public Date getVrijemePlacanja() {
        return vrijemePlacanja;
    }

    public void setVrijemePlacanja(Date vrijemePlacanja) {
        this.vrijemePlacanja = vrijemePlacanja;
    }

    public Korisnici getIdKupca() {
        return idKupca;
    }

    public void setIdKupca(Korisnici idKupca) {
        this.idKupca = idKupca;
    }

    public Narudzbine getIdNarudzbine() {
        return idNarudzbine;
    }

    public void setIdNarudzbine(Narudzbine idNarudzbine) {
        this.idNarudzbine = idNarudzbine;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transakcije)) {
            return false;
        }
        Transakcije other = (Transakcije) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Transakcije[ id=" + id + " ]";
    }
    
}
