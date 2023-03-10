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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jona
 */
@Entity
@Table(name = "recenzije")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recenzije.findAll", query = "SELECT r FROM Recenzije r"),
    @NamedQuery(name = "Recenzije.findByIdRec", query = "SELECT r FROM Recenzije r WHERE r.idRec = :idRec"),
    @NamedQuery(name = "Recenzije.findByOcjena", query = "SELECT r FROM Recenzije r WHERE r.ocjena = :ocjena")})
public class Recenzije implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRec")
    private Integer idRec;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ocjena")
    private int ocjena;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "opis")
    private String opis;
    @JoinColumn(name = "id_proizvoda", referencedColumnName = "idPro")
    @ManyToOne(optional = false)
    private Proizvodi idProizvoda;

    public Recenzije() {
    }

    public Recenzije(Integer idRec) {
        this.idRec = idRec;
    }

    public Recenzije(Integer idRec, int ocjena, String opis) {
        this.idRec = idRec;
        this.ocjena = ocjena;
        this.opis = opis;
    }

    public Integer getIdRec() {
        return idRec;
    }

    public void setIdRec(Integer idRec) {
        this.idRec = idRec;
    }

    public int getOcjena() {
        return ocjena;
    }

    public void setOcjena(int ocjena) {
        this.ocjena = ocjena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
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
        hash += (idRec != null ? idRec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recenzije)) {
            return false;
        }
        Recenzije other = (Recenzije) object;
        if ((this.idRec == null && other.idRec != null) || (this.idRec != null && !this.idRec.equals(other.idRec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Recenzije[ idRec=" + idRec + " ]";
    }
    
}
