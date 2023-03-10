/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistem2.resources;

import Entities.Kategorija;
import Entities.Korisnik;
import Entities.Korpa;
import Entities.Proizvod;
import Entities.ProizvodUKorpi;
import Entities.ProizvodUKorpiPK;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author jona
 */
@Path("funckije")
@Stateless
@Transactional
public class MajstorBob {
    @PersistenceContext
    EntityManager em;
    
    
    
    
    @POST
    @Path("/2/{imePrezime}/{username}/{grad}/{sifra}/{adresa}/{pare}")
    @Transactional
    public Response KreirajKorisnika(@PathParam("imePrezime") String imePrezime,@PathParam("username") String username,
            @PathParam("grad") String grad, @PathParam("sifra") String sifra, @PathParam("adresa") String adresa,@PathParam("pare") float pare){
        
      
        List<Korisnik> korisnici = em.createNamedQuery("Korisnik.findByKorisnickoIme",Korisnik.class).setParameter("korisnickoIme", username).getResultList();
        if(!korisnici.isEmpty()){
            return Response
                .ok("Korisnik vec postoji")
                .build(); 
        }
        Korisnik k= new Korisnik();
        k.setAdresa(adresa);
        k.setImePrezime(imePrezime);
        k.setKorisnickoIme(username);
        k.setSifra(sifra);
        k.setNovac(pare);
        em.persist(k);
        em.flush();
        Korpa korp=new Korpa();
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
        
       
         
        List<Korisnik> korisnici = em.createNamedQuery("Korisnik.findByKorisnickoIme",Korisnik.class).setParameter("korisnickoIme", username).getResultList();
        if(korisnici.isEmpty()){
            return Response
                .ok("Korisnik ne postoji!")
                .build(); 
        }
        Korisnik k= korisnici.get(0);
        k.setNovac(k.getNovac()+pare);
        em.persist(k);
        em.flush();
        return Response
                .ok("Novac uspjesno uplacen!")
                .build();
       
    }
    
    
    @POST
    @Path("/5/{naziv}/{kategorija}")
    @Transactional
    public Response NapraviKategoriju(@PathParam("naziv") String naziv,@PathParam("kategorija") String kategorija){
        
        List<Kategorija> resultList = em.createNamedQuery("Kategorija.findByNaziv",Kategorija.class).setParameter("naziv", naziv).getResultList();
        if(!resultList.isEmpty()){
            
            return Response
                .ok("Kategorija vec  postoji!")
                .build();
        }
        if("0".equals(kategorija)){
        Kategorija k = new Kategorija();
        k.setNaziv(naziv);
        k.setRoditeljskaKategorija(null);
        em.persist(k);
        em.flush();
        return Response
                .ok("Uspjesno dodata kategorija!")
                .build();
       
    }
        
         resultList = em.createNamedQuery("Kategorija.findByNaziv",Kategorija.class).setParameter("naziv", kategorija).getResultList();
        
        if(resultList.isEmpty()){
            
            return Response
                .ok("NadKategorija ne postoji!")
                .build();
        }
        Kategorija k = new Kategorija();
        k.setNaziv(naziv);
        k.setRoditeljskaKategorija(resultList.get(0));
        em.persist(k);
        em.flush();
        return Response
                .ok("Uspjesno dodata kategorija!")
                .build();
       
    }
    
    @POST
    @Path("/6/{naziv}/{opis}/{popust}/{cijena}/{kategorija}/{prodavac}")
    @Transactional
    public Response napraviProizvod(@PathParam("naziv") String naziv,@PathParam("opis") String opis,
            @PathParam("popust") float popust,@PathParam("cijena") float cijena,@PathParam("kategorija") String kategorija,@PathParam("prodavac") String prodavac){
        
        List<Kategorija> kategorije = em.createNamedQuery("Kategorija.findByNaziv",Kategorija.class).setParameter("naziv", kategorija).getResultList();
        if(kategorije.isEmpty()){ 
            return Response
                .ok("Kategorija  ne  postoji!")
                .build();
        }
        
        List<Korisnik> korisnici = em.createNamedQuery("Korisnik.findByKorisnickoIme",Korisnik.class).setParameter("korisnickoIme", prodavac).getResultList();
        if(korisnici.isEmpty()){
            return Response
                .ok("Korisnik ne postoji!")
                .build(); 
        }
        
        List<Proizvod> rez = em.createNativeQuery("select * from proizvod where naziv='"+naziv+"' and prodavac="+korisnici.get(0).getIdKor()).getResultList();
        if(!rez.isEmpty()){
            return Response
                .ok("Proizvod vec postoji!")
                .build(); 
        }
        
        Proizvod p= new Proizvod();
        p.setCijena(cijena);
        p.setNaziv(naziv);
        p.setOpis(opis);
        p.setProdavac(korisnici.get(0));
        p.setKategorijaKljuc(kategorije.get(0));
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
        
       
        List<Proizvod> proizvodi = em.createNamedQuery("Proizvod.findByIdPro",Proizvod.class).setParameter("idPro", idProizvoda).getResultList();
        if(proizvodi.isEmpty()){
            return Response
                .ok("Ne postoji taj proizvod")
                .build();
        }
        Proizvod p=proizvodi.get(0);
        float starac=p.getCijena();
        float starip=p.getPopust();
        p.setCijena(cijena);
        em.persist(p);
        em.flush();
        List<ProizvodUKorpi> puklist = em.createNamedQuery("ProizvodUKorpi.findByIdProizvoda",ProizvodUKorpi.class).setParameter("idProizvoda", idProizvoda).getResultList();
        for(ProizvodUKorpi puk:puklist){
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
        
       
        List<Proizvod> proizvodi = em.createNamedQuery("Proizvod.findByIdPro",Proizvod.class).setParameter("idPro", idProizvoda).getResultList();
        if(proizvodi.isEmpty()){
            return Response
                .ok("Ne postoji taj proizvod")
                .build();
        }
        Proizvod p=proizvodi.get(0);
        float starac=p.getCijena();
        float starip=p.getPopust();
        p.setPopust(popust);
        em.persist(p);
        em.flush();
        List<ProizvodUKorpi> puklist = em.createNamedQuery("ProizvodUKorpi.findByIdProizvoda",ProizvodUKorpi.class).setParameter("idProizvoda", idProizvoda).getResultList();
        for(ProizvodUKorpi puk:puklist){
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
        
       
        List<Proizvod> proizvodi = em.createNamedQuery("Proizvod.findByIdPro",Proizvod.class).setParameter("idPro", idProizvoda).getResultList();
        if(proizvodi.isEmpty()){
            return Response
                .ok("Ne postoji taj proizvod")
                .build();
        }
        List<Korisnik> korisnici = em.createNamedQuery("Korisnik.findByKorisnickoIme",Korisnik.class).setParameter("korisnickoIme", username).getResultList();
        if(korisnici.isEmpty()){
            return Response
                .ok("Korisnik ne postoji!")
                .build(); 
        }
        Proizvod pro=proizvodi.get(0);
        Korpa k=korisnici.get(0).getKorpaList().get(0);
        List<ProizvodUKorpi> puk = em.createNamedQuery("ProizvodUKorpi.findByIdKorpeAndIdProizvoda",ProizvodUKorpi.class).setParameter("idKorpe", k.getIdKorpe()).setParameter("idProizvoda", idProizvoda).getResultList();
        if(puk.isEmpty()){
            ProizvodUKorpi pruk=new ProizvodUKorpi();
            pruk.setKolicina(kolicina);
            pruk.setKorpa(k);
            pruk.setProizvodUKorpiPK(new ProizvodUKorpiPK(k.getIdKorpe(), idProizvoda));
            pruk.setProizvod(pro);
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
            ProizvodUKorpi pruk=puk.get(0);
            pruk.setKolicina(pruk.getKolicina()+kolicina);
            em.persist(pruk);
            em.flush();
            pruk.akumuliraj(pruk.getProizvod().getCijena()*((100-pruk.getProizvod().getPopust())/100));
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
        
       
        List<Proizvod> proizvodi = em.createNamedQuery("Proizvod.findByIdPro",Proizvod.class).setParameter("idPro", idProizvoda).getResultList();
        if(proizvodi.isEmpty()){
            return Response
                .ok("Ne postoji taj proizvod")
                .build();
        }
        List<Korisnik> korisnici = em.createNamedQuery("Korisnik.findByKorisnickoIme",Korisnik.class).setParameter("korisnickoIme", username).getResultList();
        if(korisnici.isEmpty()){
            return Response
                .ok("Korisnik ne postoji!")
                .build(); 
        }
        Proizvod pro=proizvodi.get(0);
        Korpa k=korisnici.get(0).getKorpaList().get(0);
        List<ProizvodUKorpi> puk = em.createNamedQuery("ProizvodUKorpi.findByIdKorpeAndIdProizvoda",ProizvodUKorpi.class).setParameter("idKorpe", k.getIdKorpe()).setParameter("idProizvoda", idProizvoda).getResultList();
        if(puk.isEmpty()){
            return Response
                .ok("Proizvod ne postoji u korpi !")
                .build();
        }
        else{
            ProizvodUKorpi pruk=puk.get(0);
            
          
            pruk.akumuliraj(-pruk.getProizvod().getCijena()*((100-pruk.getProizvod().getPopust())/100),(pruk.getKolicina()-kolicina)<0?pruk.getKolicina():kolicina);
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
        
        List<Korisnik> korisnici = em.createNamedQuery("Korisnik.findByKorisnickoIme",Korisnik.class).setParameter("korisnickoIme", username).getResultList();
        if(korisnici.isEmpty()){
            return Response
                .ok("Korisnik ne postoji!")
                .build(); 
        }
        int idkrpe=korisnici.get(0).getKorpaList().get(0).getIdKorpe();
        
        
        List<Float> ukupnacijena = em.createNamedQuery("Korpa.findUkupnuCijenuByIdKorpe").setParameter("idKorp", idkrpe).getResultList();
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
        List<Korpa> korpa = em.createNamedQuery("Korpa.findByIdKorpe",Korpa.class).setParameter("idKorpe", idkrpe).getResultList();
        if(korpa.isEmpty()){
             return Response
                .ok("wtf se odje desilo faila upit lgn")
                .build();
        }
        korpa.get(0).setUkupnaCijena(0);
        em.persist(korpa.get(0));
        em.flush();
        
        
        //sad update iteme u korpi
       
        List<Object[]> itemiukorpi = em.createNativeQuery("SELECT * FROM proizvod_u_korpi p WHERE p.idKorpe ="+idkrpe+" AND p.kolicina > 0").getResultList();
        for (Object[] array : itemiukorpi) {
            int kolicina=(int) array[2];
            int idproizvoda=(int)array[1];
            List<Proizvod> item = em.createNamedQuery("Proizvod.findByIdPro",Proizvod.class).setParameter("idPro", idproizvoda).getResultList();
            float cijena=item.get(0).getCijena();
            float popust=item.get(0).getPopust();
            Korisnik prodavac = item.get(0).getProdavac();
            prodavac.setNovac(prodavac.getNovac()+(cijena*(1-popust/100))*kolicina);
            em.persist(prodavac);
            em.flush();
           //mora odje jedan update native  proizvodiUKorpi.setKolicina(0);
             em.createNativeQuery("UPDATE proizvod_u_korpi SET kolicina=0 WHERE idProizvoda="+idproizvoda +" and idKorpe="+idkrpe).executeUpdate();
             
            
        }
                
        
                  
       return Response
                .ok("Uspjesno ste obavili kupovinu")
                .build(); 
    }
    
     @GET
    @Path("/14")
    @Transactional
    public Response dohvatiSveKategorije(){
        
               
        List<Object[]>  resultList =  em.createNativeQuery("select * from Kategorija").getResultList();
        if (resultList.isEmpty()){
           return Response
                .ok("Nema kategorija")
                .build(); 
        }
        
        return Response.status(200).entity(resultList).build();

       
    }
    
    @GET
    @Path("/15/{username}")
    @Transactional
    public Response dohvatiArtikle(@PathParam("username") String username){
        
        List<Korisnik> korisnici = em.createNamedQuery("Korisnik.findByKorisnickoIme",Korisnik.class).setParameter("korisnickoIme", username).getResultList();
        if(korisnici.isEmpty()){
            return Response
                .ok("Korisnik ne postoji!")
                .build(); 
        }
        
        
        List<Object[]>  resultList =  em.createNativeQuery("select * from Proizvod where prodavac="+korisnici.get(0).getIdKor()).getResultList();
        if (resultList.isEmpty()){
           return Response
                .ok("Nema Proizvoda")
                .build(); 
        }
        
        return Response.status(200).entity(resultList).build();

       
    }
    
    @GET
    @Path("/16/{username}")
    @Transactional
    public Response dohvatiKorpu(@PathParam("username") String username){
        
        List<Korisnik> korisnici = em.createNamedQuery("Korisnik.findByKorisnickoIme",Korisnik.class).setParameter("korisnickoIme", username).getResultList();
        if(korisnici.isEmpty()){
            return Response
                .ok("Korisnik ne postoji!")
                .build(); 
        }
        
        int idkorpe=korisnici.get(0).getKorpaList().get(0).getIdKorpe();
        System.out.println(idkorpe);
        List<Object[]>  resultList =  em.createNativeQuery("select idPro,naziv,opis,popust,cijena from proizvod_u_korpi,proizvod where idPro=idProizvoda and kolicina>0 and idKorpe="+idkorpe).getResultList();
        if (resultList.isEmpty()){
           return Response
                .ok("Nema proizvoda u korpi")
                .build(); 
        }
        
        return Response.status(200).entity(resultList).build();

       
    }
}
