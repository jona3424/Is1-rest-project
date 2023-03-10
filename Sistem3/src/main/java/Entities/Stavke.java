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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jona
 */
@Entity
@Table(name = "stavke")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stavke.findAll", query = "SELECT s FROM Stavke s"),
    @NamedQuery(name = "Stavke.findByIdSta", query = "SELECT s FROM Stavke s WHERE s.idSta = :idSta"),
    @NamedQuery(name = "Stavke.findByKolicina", query = "SELECT s FROM Stavke s WHERE s.kolicina = :kolicina"),
    @NamedQuery(name = "Stavke.findByCijenaPoKomadu", query = "SELECT s FROM Stavke s WHERE s.cijenaPoKomadu = :cijenaPoKomadu")})
public class Stavke implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSta")
    private Integer idSta;
    @Column(name = "kolicina")
    private Integer kolicina;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cijenaPoKomadu")
    private Float cijenaPoKomadu;
    @JoinColumn(name = "idNarudzbine", referencedColumnName = "idNar")
    @ManyToOne
    private Narudzbine idNarudzbine;
    @JoinColumn(name = "idProizvoda", referencedColumnName = "idPro")
    @ManyToOne
    private Proizvodi idProizvoda;

    public Stavke() {
    }

    public Stavke(Integer idSta) {
        this.idSta = idSta;
    }

    public Integer getIdSta() {
        return idSta;
    }

    public void setIdSta(Integer idSta) {
        this.idSta = idSta;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public Float getCijenaPoKomadu() {
        return cijenaPoKomadu;
    }

    public void setCijenaPoKomadu(Float cijenaPoKomadu) {
        this.cijenaPoKomadu = cijenaPoKomadu;
    }

    public Narudzbine getIdNarudzbine() {
        return idNarudzbine;
    }

    public void setIdNarudzbine(Narudzbine idNarudzbine) {
        this.idNarudzbine = idNarudzbine;
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
        hash += (idSta != null ? idSta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stavke)) {
            return false;
        }
        Stavke other = (Stavke) object;
        if ((this.idSta == null && other.idSta != null) || (this.idSta != null && !this.idSta.equals(other.idSta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Stavke[ idSta=" + idSta + " ]";
    }
    
}
