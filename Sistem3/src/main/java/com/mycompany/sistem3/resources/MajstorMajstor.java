/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistem3.resources;

import Entities.Korisnici;
import Entities.Korpe;
import Entities.Narudzbine;
import Entities.Proizvodi;
import Entities.ProizvodiUKorpi;
import Entities.Stavke;
import Entities.Transakcije;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("funckije")
@Stateless
@Transactional
public class MajstorMajstor {
    @PersistenceContext
    EntityManager em;
    
    
    
    
    @POST
    @Path("/2/{imePrezime}/{username}/{grad}/{sifra}/{adresa}/{pare}")
    @Transactional
    public Response KreirajKorisnika(@PathParam("imePrezime") String imePrezime,@PathParam("username") String username,
            @PathParam("grad") String grad, @PathParam("sifra") String sifra, @PathParam("adresa") String adresa,@PathParam("pare") float pare){
        
      
        List<Korisnici> korisnici = em.createNamedQuery("Korisnici.findByKorisnickoIme",Korisnici.class).setParameter("korisnickoIme", username).getResultList();
        if(!korisnici.isEmpty()){
            return Response
                .ok("Korisnik vec postoji")
                .build(); 
        }
        Korisnici k= new Korisnici();
        k.setImePrezime(imePrezime);
        k.setKorisnickoIme(username);
        k.setLozinka(sifra);
        k.setNovac(pare);
        em.persist(k);
        em.flush();
        Korpe korp=new Korpe();
        korp.setIdKorisnika(k);
        korp.setUkupnaCijena(0);
        em.persist(korp);
        em.flush();
        return Response
                .ok("Korisnik uspjesno dodat!")
                .build();
       
    }
    
    
      @POST
    @Path("/3/{username}/{pare}")
    @Transactional
    public Response DodajPare(@PathParam("username") String username,@PathParam("pare") float pare){
        
       
          
        List<Korisnici> korisnici = em.createNamedQuery("Korisnici.findByKorisnickoIme",Korisnici.class).setParameter("korisnickoIme", username).getResultList();
        if(korisnici.isEmpty()){
            return Response
                .ok("Korisnik ne postoji!")
                .build(); 
        }
        Korisnici k= korisnici.get(0);
        k.setNovac(k.getNovac()+pare);
        em.persist(k);
        em.flush();
        return Response
                .ok("Novac uspjesno uplacen!")
                .build();
       
    }

    @POST
    @Path("/6/{naziv}/{opis}/{popust}/{cijena}/{kategorija}/{prodavac}")
    @Transactional
    public Response napraviProizvod(@PathParam("naziv") String naziv,@PathParam("opis") String opis,
            @PathParam("popust") float popust,@PathParam("cijena") float cijena,@PathParam("kategorija") String kategorija,@PathParam("prodavac") String prodavac){
        
      
        
        List<Korisnici> korisnici = em.createNamedQuery("Korisnici.findByKorisnickoIme",Korisnici.class).setParameter("korisnickoIme", prodavac).getResultList();
        if(korisnici.isEmpty()){
            return Response
                .ok("Korisnik ne postoji!")
                .build(); 
        }
        
        List<Proizvodi> rez = em.createNativeQuery("select * from proizvodi where naziv='"+naziv+"' and prodavac="+korisnici.get(0).getIdKor()).getResultList();
        if(!rez.isEmpty()){
            return Response
                .ok("Proizvod vec postoji!")
                .build(); 
        }
        
        Proizvodi p= new Proizvodi();
        p.setCijena(cijena);
        p.setNaziv(naziv);
        p.setOpis(opis);
        p.setProdavac(korisnici.get(0));
        p.setPopust(popust);
        em.persist(p);
        em.flush();
        return Response
                .ok("Uspjesno dodat proizvod!")
                .build();
              
       
    }
    
    
    
       @POST
    @Path("/7/{idProizvoda}/{cijena}")
    @Transactional
    public Response MijenjanjeCijene(@PathParam("idProizvoda") int idProizvoda,@PathParam("cijena") float cijena){
        
       
        List<Proizvodi> proizvodi = em.createNamedQuery("Proizvodi.findByIdPro",Proizvodi.class).setParameter("idPro", idProizvoda).getResultList();
        if(proizvodi.isEmpty()){
            return Response
                .ok("Ne postoji taj proizvod")
                .build();
        }
        Proizvodi p=proizvodi.get(0);
        float starac=p.getCijena();
        float starip=p.getPopust();
        p.setCijena(cijena);
        em.persist(p);
        em.flush();
        List<ProizvodiUKorpi> puklist = em.createNamedQuery("ProizvodiUKorpi.findByIdProizvoda",ProizvodiUKorpi.class).setParameter("idP", idProizvoda).getResultList();
        for(ProizvodiUKorpi puk:puklist){
            puk.akumuliraj(-(starac*((100-starip)/100)));
            em.persist(puk);
            em.flush();
            puk.akumuliraj(cijena*((100-starip)/100));
            em.persist(puk);
            em.flush();
        }
        return Response
                .ok("Uspjesno promijenjena cijena proizvodu!")
                .build();
   
       
    }
    
     
       @POST
    @Path("/8/{idProizvoda}/{popust}")
    @Transactional
    public Response MijenjanjePopusta(@PathParam("idProizvoda") int idProizvoda,@PathParam("popust") float popust){
        
       
        List<Proizvodi> proizvodi = em.createNamedQuery("Proizvodi.findByIdPro",Proizvodi.class).setParameter("idPro", idProizvoda).getResultList();
        if(proizvodi.isEmpty()){
            return Response
                .ok("Ne postoji taj proizvod")
                .build();
        }
        
        Proizvodi p=proizvodi.get(0);
        float starac=p.getCijena();
        float starip=p.getPopust();
        p.setPopust(popust);
        em.persist(p);
        em.flush();
        List<ProizvodiUKorpi> puklist = em.createNamedQuery("ProizvodiUKorpi.findByIdProizvoda",ProizvodiUKorpi.class).setParameter("idP", idProizvoda).getResultList();
        for(ProizvodiUKorpi puk:puklist){
            puk.akumuliraj(-(starac*((100-starip)/100)));
            em.persist(puk);
            em.flush();
            puk.akumuliraj(starac*((100-popust)/100));
            em.persist(puk);
            em.flush();
        }
        return Response
                .ok("Uspjesno promijenjen popust proizvodu!")
                .build();
   
       
    }
    
    
      @POST
    @Path("/9/{idProizvoda}/{username}/{kolicina}")
    @Transactional
    public Response dodajUKorpu(@PathParam("idProizvoda") int idProizvoda,@PathParam("username") String username,@PathParam("kolicina") int kolicina){
        
       
        
        List<Proizvodi> proizvodi = em.createNamedQuery("Proizvodi.findByIdPro",Proizvodi.class).setParameter("idPro", idProizvoda).getResultList();
        if(proizvodi.isEmpty()){
            return Response
                .ok("Ne postoji taj proizvod")
                .build();
        }
        List<Korisnici> korisnici = em.createNamedQuery("Korisnici.findByKorisnickoIme",Korisnici.class).setParameter("korisnickoIme", username).getResultList();
        if(korisnici.isEmpty()){
            return Response
                .ok("Korisnik ne postoji!")
                .build(); 
        }
        
        Proizvodi pro=proizvodi.get(0);
        Korpe k=korisnici.get(0).getKorpeList().get(0);
        List<ProizvodiUKorpi> puk = em.createNamedQuery("ProizvodiUKorpi.findByIdKorpeandIdProizvoda",ProizvodiUKorpi.class).setParameter("idK", k.getIdKorp()).setParameter("idP", idProizvoda).getResultList();
        if(puk.isEmpty()){
            ProizvodiUKorpi pruk=new ProizvodiUKorpi();
            pruk.setKolicina(kolicina);
            pruk.setIdKorpe(k);
            pruk.setIdProizvoda(pro);
            em.persist(pruk);
            em.flush();
            pruk.akumuliraj(pro.getCijena()*((100-pro.getPopust())/100));
            em.persist(pruk);
            em.flush();
            return Response
                .ok("Uspjesno dodat proizvod u korpu!")
                .build();
        }
        else{
            ProizvodiUKorpi pruk=puk.get(0);
            pruk.setKolicina(pruk.getKolicina()+kolicina);
            em.persist(pruk);
            em.flush();
            pruk.akumuliraj(pruk.getIdProizvoda().getCijena()*((100-pruk.getIdProizvoda().getPopust())/100));
            em.persist(pruk);
            em.flush();
            
            return Response
                .ok("Uspjesno dodat proizvod u korpu!")
                .build();
        }
        
    }
    
    
    @POST
    @Path("/10/{idProizvoda}/{username}/{kolicina}")
    @Transactional
    public Response ukloniIzKorpe(@PathParam("idProizvoda") int idProizvoda,@PathParam("username") String username,@PathParam("kolicina") int kolicina){
        
       
         List<Proizvodi> proizvodi = em.createNamedQuery("Proizvodi.findByIdPro",Proizvodi.class).setParameter("idPro", idProizvoda).getResultList();
        if(proizvodi.isEmpty()){
            return Response
                .ok("Ne postoji taj proizvod")
                .build();
        }
        List<Korisnici> korisnici = em.createNamedQuery("Korisnici.findByKorisnickoIme",Korisnici.class).setParameter("korisnickoIme", username).getResultList();
        if(korisnici.isEmpty()){
            return Response
                .ok("Korisnik ne postoji!")
                .build(); 
        }
        
        Proizvodi pro=proizvodi.get(0);
        Korpe k=korisnici.get(0).getKorpeList().get(0);
        List<ProizvodiUKorpi> puk = em.createNamedQuery("ProizvodiUKorpi.findByIdKorpeandIdProizvoda",ProizvodiUKorpi.class).setParameter("idK", k.getIdKorp()).setParameter("idP", idProizvoda).getResultList();
        if(puk.isEmpty()){
            return Response
                .ok("Proizvod ne postoji u korpi !")
                .build();
        }
        else{
            ProizvodiUKorpi pruk=puk.get(0);
            
          
            pruk.akumuliraj(-pruk.getIdProizvoda().getCijena()*((100-pruk.getIdProizvoda().getPopust())/100),(pruk.getKolicina()-kolicina)<0?pruk.getKolicina():kolicina);
            pruk.setKolicina((pruk.getKolicina()-kolicina)<0?0:pruk.getKolicina()-kolicina);

            em.persist(pruk);
            em.flush();
            
            return Response
                .ok("Uspjesno uklonjeni proizvodi iz korpu!")
                .build();
        }
                  
       
    }
    
    
    
    
    @POST
    @Path("/11/{username}/{adresa}/{grad}")
    @Transactional
    public Response Placanje(@PathParam("username") String username,@PathParam("adresa") String adresa,@PathParam("grad") String grad){
        
        List<Korisnici> korisnici = em.createNamedQuery("Korisnici.findByKorisnickoIme",Korisnici.class).setParameter("korisnickoIme", username).getResultList();
        if(korisnici.isEmpty()){
            return Response
                .ok("Korisnik ne postoji!")
                .build(); 
        }
        int idkrpe=korisnici.get(0).getKorpeList().get(0).getIdKorp();
        
        
        List<Float> ukupnacijena = em.createNamedQuery("Korpe.findUkupnuCijenuByIdKorpe").setParameter("idKorp", idkrpe).getResultList();
        if(ukupnacijena.isEmpty() || ukupnacijena.get(0)==0 ){
             return Response
                .ok("Korpa je prazna")
                .build();
        }
      
        if(korisnici.get(0).getNovac()<ukupnacijena.get(0)){
            return Response
                .ok("Nemate dovoljno para,stanje na vasem racunu je: "+korisnici.get(0).getNovac()+ " a porudzbina kosta: "+ukupnacijena.get(0))
                .build();
        }
        //skidaj pare
        korisnici.get(0).setNovac(korisnici.get(0).getNovac()-ukupnacijena.get(0));
        em.persist(korisnici.get(0));
        em.flush();
        
        //update korpu jelte
        List<Korpe> korpa = em.createNamedQuery("Korpe.findByIdKorp",Korpe.class).setParameter("idKorp", idkrpe).getResultList();
        if(korpa.isEmpty()){
             return Response
                .ok("wtf se odje desilo faila upit lgn")
                .build();
        }
        korpa.get(0).setUkupnaCijena(0);
        em.persist(korpa.get(0));
        em.flush();
        //pravi narudzbinu
        Narudzbine narudzbine=new Narudzbine();
        narudzbine.setAdresaDostave(adresa);
        narudzbine.setGradDostave(grad);
        narudzbine.setDatumVrijeme(new Date());
        narudzbine.setUkupnaCijena(ukupnacijena.get(0));
        em.persist(narudzbine);
        em.flush();
        
        //pravi transakciju
        Transakcije trans=new Transakcije();
        trans.setIdKupca(korisnici.get(0));
        trans.setIznos(ukupnacijena.get(0));
        trans.setIdNarudzbine(narudzbine);
        trans.setVrijemePlacanja(new Date());
        em.persist(trans);
        em.flush();
        
        //sad update iteme u korpi
       
        List<Object[]> itemiukorpi = em.createNativeQuery("SELECT * FROM proizvodi_u_korpi p WHERE p.id_korpe ="+idkrpe+" AND p.kolicina > 0").getResultList();
        for (Object[] array : itemiukorpi) {
            int kolicina=(int) array[3];
            int idproizvoda=(int)array[2];
            List<Proizvodi> item = em.createNamedQuery("Proizvodi.findByIdPro",Proizvodi.class).setParameter("idPro", idproizvoda).getResultList();
            float cijena=item.get(0).getCijena();
            float popust=item.get(0).getPopust();
            Korisnici prodavac = item.get(0).getProdavac();
            prodavac.setNovac(prodavac.getNovac()+(cijena*(1-popust/100))*kolicina);
            em.persist(prodavac);
            em.flush();
           //mora odje jedan update native  proizvodiUKorpi.setKolicina(0);
             em.createNativeQuery("UPDATE proizvodi_u_korpi SET kolicina=0 WHERE id_proizvoda="+idproizvoda +" and id_korpe="+idkrpe).executeUpdate();
             
             Stavke stavka=new Stavke();
             stavka.setCijenaPoKomadu(cijena*(1-popust/100));
             stavka.setIdNarudzbine(narudzbine);
             stavka.setIdProizvoda(item.get(0));
             stavka.setKolicina(kolicina);
             em.persist(stavka);
             em.flush();
        }
                
        
                  
       return Response
                .ok("Uspjesno ste obavili kupovinu")
                .build(); 
    }
    
    @GET
    @Path("/17/{username}")
    @Transactional
    public Response dohvatiSveNarudzbinezaUsera(@PathParam("username") String username){
        
         List<Korisnici> korisnici = em.createNamedQuery("Korisnici.findByKorisnickoIme",Korisnici.class).setParameter("korisnickoIme", username).getResultList();
        if(korisnici.isEmpty()){
            return Response
                .ok("Korisnik ne postoji!")
                .build(); 
        }
         List<Transakcije> trans = em.createNamedQuery("Transakcije.findByIdKup",Transakcije.class).setParameter("username", korisnici.get(0).getIdKor()).getResultList();
        if(trans.isEmpty()){
            return Response
                .ok("Narudzbine ne postoje!")
                .build(); 
        }
        List<List<Object[]>>  finallist = new ArrayList<List<Object[]>>();
        for(Transakcije t:trans){
            trans.get(0).getIdNarudzbine();
            List<Object[]>  resultList=em.createNativeQuery("select * from Narudzbine where idNar="+t.getIdNarudzbine().getIdNar()).getResultList();
            finallist.add(resultList);
        }
        
        
        return Response.status(200).entity(finallist).build();

       
    } 
    
    @GET
    @Path("/18")
    @Transactional
    public Response dohvatiSveNarudzbine(){
        
               
        List<Object[]>  resultList =  em.createNativeQuery("select * from Narudzbine").getResultList();
        if (resultList.isEmpty()){
           return Response
                .ok("Nema narudzbina")
                .build(); 
        }
        
        return Response.status(200).entity(resultList).build();

       
    } 
     @GET
    @Path("/19")
    @Transactional
    public Response dohvatisveTransakcije(){
        
               
        List<Object[]>  resultList =  em.createNativeQuery("select * from Transakcije").getResultList();
        if (resultList.isEmpty()){
           return Response
                .ok("Nema transakcija")
                .build(); 
        }
        
        return Response.status(200).entity(resultList).build();

       
    } 
}