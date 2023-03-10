/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistem1.resources;

import Etities.Grad;
import Etities.Korisnik;
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
public class Majstor {
    @PersistenceContext
    EntityManager em;
    
    
    @POST
    @Path("/1/{Naziv}")
    @Transactional
    public Response KreirajGrad(@PathParam("Naziv") String Naziv ){
        
        List<Grad> resultList = em.createNamedQuery("Grad.findByNaziv",Grad.class).setParameter("naziv", Naziv).getResultList();
        if (!resultList.isEmpty()){
           return Response
                .ok("Grad vec postoji")
                .build(); 
        }
        Grad g=new Grad();
        g.setNaziv(Naziv);
        em.persist(g);
        em.flush();
        return Response
                .ok("Grad dodat uspjesno!")
                .build();
       
    }
    
    
    @POST
    @Path("/2/{imePrezime}/{username}/{grad}/{sifra}/{adresa}/{pare}")
    @Transactional
    public Response KreirajKorisnika(@PathParam("imePrezime") String imePrezime,@PathParam("username") String username,
            @PathParam("grad") String grad, @PathParam("sifra") String sifra, @PathParam("adresa") String adresa,@PathParam("pare") float pare){
        
        List<Grad> resultList = em.createNamedQuery("Grad.findByNaziv",Grad.class).setParameter("naziv", grad).getResultList();
        if (resultList.isEmpty()){
           return Response
                .ok("Ne postoji taj grad")
                .build(); 
        }
        List<Korisnik> korisnici = em.createNamedQuery("Korisnik.findByKorisnickoIme",Korisnik.class).setParameter("korisnickoIme", username).getResultList();
        if(!korisnici.isEmpty()){
            return Response
                .ok("Korisnik vec postoji")
                .build(); 
        }
        Korisnik k= new Korisnik();
        k.setAdresa(adresa);
        k.setGrad(resultList.get(0));
        k.setImePrezima(imePrezime);
        k.setKorisnickoIme(username);
        k.setLozinka(sifra);
        em.persist(k);
        em.flush();
       
        return Response
                .ok("Korisnik uspjesno dodat!")
                .build();
       
    }
    
//     @POST
//    @Path("/3/{username}/{pare}")
//    @Transactional
//    public Response DodajPare(@PathParam("username") String username,@PathParam("pare") float pare){
//        
//       
//        List<Korisnik> korisnici = em.createNamedQuery("Korisnik.findByKorisnickoIme",Korisnik.class).setParameter("korisnickoIme", username).getResultList();
//        if(korisnici.isEmpty()){
//            return Response
//                .ok("Korisnik ne postoji!")
//                .build(); 
//        }
//        Korisnik k= korisnici.get(0);
//        k.setNovac(k.getNovac()+pare);
//        em.persist(k);
//        em.flush();
//        return Response
//                .ok("Novac uspjesno uplacen!")
//                .build();
//       
//    }


@POST
    @Path("/4/{username}/{grad}/{adresa}")
    @Transactional
    public Response PomijeniAdresuiGrad(@PathParam("username") String username,@PathParam("grad") String grad,@PathParam("adresa") String adresa){
        
               
        List<Grad> resultList = em.createNamedQuery("Grad.findByNaziv",Grad.class).setParameter("naziv", grad).getResultList();
        if (resultList.isEmpty()){
           return Response
                .ok("Ne postoji taj grad")
                .build(); 
        }
        
        List<Korisnik> korisnici = em.createNamedQuery("Korisnik.findByKorisnickoIme",Korisnik.class).setParameter("korisnickoIme", username).getResultList();
        if(korisnici.isEmpty()){
            return Response
                .ok("Korisnik ne postoji!")
                .build(); 
        }
        Korisnik k= korisnici.get(0);
        k.setAdresa(adresa);
        k.setGrad(resultList.get(0));
        em.persist(k);
        em.flush();
        return Response
                .ok("Uspijesno prominjenjena adresa i grad!")
                .build();
       
    }
    
    @GET
    @Path("/12")
    @Transactional
    public Response PomijeniAdresuiGrad(){
        
               
        List<Object[]>  resultList =  em.createNativeQuery("select * from Grad").getResultList();
        if (resultList.isEmpty()){
           return Response
                .ok("Nema gradova")
                .build(); 
        }
        
        return Response.status(200).entity(resultList).build();

       
    }
    @GET
    @Path("/13")
    @Transactional
    public Response dohvatiSveKorisnike(){
        
               
        List<Object[]>  resultList =  em.createNativeQuery("select * from Korisnik").getResultList();
        if (resultList.isEmpty()){
           return Response
                .ok("Nema korisnika")
                .build(); 
        }
        
        return Response.status(200).entity(resultList).build();

       
    }
}
