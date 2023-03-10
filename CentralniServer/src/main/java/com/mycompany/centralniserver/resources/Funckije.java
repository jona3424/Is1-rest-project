/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.centralniserver.resources;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
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
public class Funckije {

    @Resource(lookup = "jms/__defaultConnectionFactory")
     ConnectionFactory connectionFactory;
    @Resource(lookup = "podsistem1Queue")
     Queue qpodsistem1;
    @Resource(lookup = "podsistem2Queue")
     Queue qpodsistem2;
    @Resource(lookup = "podsistem3Queue")
     Queue qpodsistem3;
    @Resource(lookup = "odgovor1")
     Queue qodgovora1;
     @Resource(lookup = "odgovor2")
     Queue qodgovora2;
     @Resource(lookup = "odgovor3")
     Queue qodgovora3;
     
     
    @POST
    @Path("/1/{Naziv}")
    public Response kreirajGrad(@PathParam("Naziv") String Naziv ){
           
        try {
            String s= "http://localhost:8080/Sistem1/resources/funckije/1/"+Naziv;
            
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem1);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "POST");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
              
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora1);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                return Response
                        .ok(((TextMessage) message).getText())
                        .build();
                
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
            
            } 
   
    
    @POST
    @Path("/2/{imePrezime}/{user}/{sifra}/{adresa}/{grad}/{pare}")
    public Response kreirajKorisnika(@PathParam("imePrezime") String imePrezime,@PathParam("user") String user,@PathParam("sifra") String sifra,
            @PathParam("adresa") String adresa,@PathParam("grad") String grad,@PathParam("pare") float pare ){
            String returnmsg="";
        try {
            String s= "http://localhost:8080/Sistem1/resources/funckije/2/"+imePrezime+"/"+user+"/"+grad+"/"+sifra+"/"+adresa+"/"+pare;
            
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem1);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "POST");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
            
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora1);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                returnmsg+=((TextMessage) message).getText();
                returnmsg+="\n";
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
         try {
            String s= "http://localhost:8080/Sistem2/resources/funckije/2/"+imePrezime+"/"+user+"/"+grad+"/"+sifra+"/"+adresa+"/"+pare;
            
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem2);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "POST");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
            
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora2);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                returnmsg+=((TextMessage) message).getText();
                returnmsg+="\n";
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
          try {
            String s= "http://localhost:8080/Sistem3/resources/funckije/2/"+imePrezime+"/"+user+"/"+grad+"/"+sifra+"/"+adresa+"/"+pare;
            
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem3);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "POST");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
            
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora3);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                returnmsg+=((TextMessage) message).getText();
                returnmsg+="\n";
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
        
        return Response
                        .ok(returnmsg)
                        .build();
            } 
    
    
    @POST
    @Path("/3/{username}/{pare}")
    public Response dadajPare(@PathParam("username") String user,@PathParam("pare") float pare  ){
           
            String returnmsg="";
//        try {
//            String s= "http://localhost:8080/Sistem1/resources/funckije/3/"+user+"/"+pare;
//            Connection connection = connectionFactory.createConnection();
//            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            MessageProducer producer = session.createProducer(qpodsistem1);
//            producer.setPriority(9);
//            Message message = session.createTextMessage(s);
//            
//            message.setStringProperty("Metod", "POST");
//            message.setIntProperty("Tip", 1);
//            producer.send(message);
//            connection.close();
//            
//        
//            connection = connectionFactory.createConnection();
//            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            MessageConsumer consumer = session.createConsumer(qodgovora1);
//            connection.start();
//            message = consumer.receive(5000);
//            if (message instanceof TextMessage) {
//                connection.close();
//                returnmsg+=((TextMessage) message).getText();
//                returnmsg+="\n";
//            }
//            else{
//               return Response
//                        .status(Response.Status.CONFLICT)
//                        .build(); 
//            }
//        } catch (JMSException ex) {
//            
//            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
//            return Response
//                        .status(Response.Status.CONFLICT)
//                        .build();
//        }
         try {
            String s= "http://localhost:8080/Sistem2/resources/funckije/3/"+user+"/"+pare;
            
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem2);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "POST");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
            
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora2);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                returnmsg+=((TextMessage) message).getText();
                returnmsg+="\n";
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
          try {
            String s= "http://localhost:8080/Sistem3/resources/funckije/3/"+user+"/"+pare;
            
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem3);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "POST");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
            
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora3);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                returnmsg+=((TextMessage) message).getText();
                returnmsg+="\n";
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
        
        return Response
                        .ok(returnmsg)
                        .build();
            } 

   
       @POST
    @Path("/4/{username}/{grad}/{adresa}")
    public Response promijeniAdresuIGrad(@PathParam("username") String username,@PathParam("grad") String grad,@PathParam("adresa") String adresa){
           
        try {
            String s= "http://localhost:8080/Sistem1/resources/funckije/4/"+username+"/"+grad+"/"+adresa;
            
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem1);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "POST");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
              
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora1);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                return Response
                        .ok(((TextMessage) message).getText())
                        .build();
                
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
            
            } 
   
    @POST
    @Path("/5/{naziv}/{kategorija}")
    public Response kreirajKategoriju(@PathParam("naziv") String naziv, @PathParam("kategorija") String kategorija){
           
        try {
            String s= "http://localhost:8080/Sistem2/resources/funckije/5/"+naziv+"/"+kategorija;
            
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem1);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "POST");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
              
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora1);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                return Response
                        .ok(((TextMessage) message).getText())
                        .build();
                
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
            
            } 
   
   
    @POST
    @Path("/6/{naziv}/{opis}/{popust}/{cijena}/{kategorija}/{prodavac}")
    public Response kreirajArtikat(@PathParam("naziv") String naziv,@PathParam("opis") String opis,
            @PathParam("popust") float popust,@PathParam("cijena") float cijena,@PathParam("kategorija") String kategorija,@PathParam("prodavac") String prodavac ){
            String returnmsg="";
            //ako prvi vrati los response onda ne izvrsavam drugi in case da bude trebala da se unese pogresna kategorija!
        try {
            String s= "http://localhost:8080/Sistem2/resources/funckije/6/"+naziv+"/"+opis+"/"+popust+"/"+cijena+"/"+kategorija+"/"+prodavac;
            
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem1);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "POST");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
            
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora1);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                returnmsg+=((TextMessage) message).getText();
                returnmsg+="\n";
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
         
          try {
            String s= "http://localhost:8080/Sistem3/resources/funckije/6/"+naziv+"/"+opis+"/"+popust+"/"+cijena+"/"+kategorija+"/"+prodavac;
            
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem3);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "POST");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
            
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora3);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                returnmsg+=((TextMessage) message).getText();
                returnmsg+="\n";
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
        
        return Response
                        .ok(returnmsg)
                        .build();
            } 
    
    
    @POST
    @Path("/7/{idProizvoda}/{cijena}")
    public Response mijenjajCijenuArtiklu(@PathParam("idProizvoda") int idProizvoda,@PathParam("cijena") float cijena){
            String returnmsg="";
        try {
            String s= "http://localhost:8080/Sistem2/resources/funckije/7/"+idProizvoda+"/"+cijena;
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem1);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "POST");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
            
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora1);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                returnmsg+=((TextMessage) message).getText();
                returnmsg+="\n";
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
         
          try {
            String s= "http://localhost:8080/Sistem3/resources/funckije/7/"+idProizvoda+"/"+cijena;
            
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem3);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "POST");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
            
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora3);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                returnmsg+=((TextMessage) message).getText();
                returnmsg+="\n";
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
        
        return Response
                        .ok(returnmsg)
                        .build();
            } 

    
    @POST
    @Path("/8/{idProizvoda}/{popust}")
    public Response mijenjajPopustArtiklu(@PathParam("idProizvoda") int idProizvoda,@PathParam("popust") float popust){
            String returnmsg="";
        try {
            String s= "http://localhost:8080/Sistem2/resources/funckije/8/"+idProizvoda+"/"+popust;
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem1);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "POST");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
            
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora1);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                returnmsg+=((TextMessage) message).getText();
                returnmsg+="\n";
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
         
          try {
            String s= "http://localhost:8080/Sistem3/resources/funckije/8/"+idProizvoda+"/"+popust;
            
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem3);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "POST");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
            
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora3);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                returnmsg+=((TextMessage) message).getText();
                returnmsg+="\n";
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
        
        return Response
                        .ok(returnmsg)
                        .build();
            } 

    
     
    @POST
    @Path("/9/{idProizvoda}/{username}/{kolicina}")
    public Response dodajUKorpu(@PathParam("idProizvoda") int idProizvoda,@PathParam("username") String username,@PathParam("kolicina") int kolicina){
           
           String returnmsg="";
        try {
            String s= "http://localhost:8080/Sistem2/resources/funckije/9/"+idProizvoda+"/"+username+"/"+kolicina;
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem2);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "POST");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
            
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora2);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                returnmsg+=((TextMessage) message).getText();
                returnmsg+="\n";
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
         
          try {
            String s= "http://localhost:8080/Sistem3/resources/funckije/9/"+idProizvoda+"/"+username+"/"+kolicina;
            
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem3);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "POST");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
            
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora3);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                returnmsg+=((TextMessage) message).getText();
                returnmsg+="\n";
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
        
        return Response
                        .ok(returnmsg)
                        .build();
            } 
             
   
    @POST
    @Path("/10/{idProizvoda}/{username}/{kolicina}")
    public Response ukloniIzKorpe(@PathParam("idProizvoda") int idProizvoda,@PathParam("username") String username,@PathParam("kolicina") int kolicina){
           
           String returnmsg="";
        try {
            String s= "http://localhost:8080/Sistem2/resources/funckije/10/"+idProizvoda+"/"+username+"/"+kolicina;
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem1);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "POST");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
            
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora1);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                returnmsg+=((TextMessage) message).getText();
                returnmsg+="\n";
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
         
          try {
            String s= "http://localhost:8080/Sistem3/resources/funckije/10/"+idProizvoda+"/"+username+"/"+kolicina;
            
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem3);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "POST");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
            
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora3);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                returnmsg+=((TextMessage) message).getText();
                returnmsg+="\n";
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
        
        return Response
                        .ok(returnmsg)
                        .build();
            } 
       
    
    @POST
    @Path("/11/{username}/{adresa}/{grad}")
    public Response Placanje(@PathParam("username") String username,@PathParam("adresa") String adresa,@PathParam("grad") String grad){
    
        String returnmsg="";
        try {
            String s= "http://localhost:8080/Sistem2/resources/funckije/11/"+username+"/"+adresa+"/"+grad;
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem1);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "POST");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
            
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora1);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                returnmsg+=((TextMessage) message).getText();
                returnmsg+="\n";
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
         
          try {
            String s= "http://localhost:8080/Sistem3/resources/funckije/11/"+username+"/"+adresa+"/"+grad;
            
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem3);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "POST");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
            
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora3);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                returnmsg+=((TextMessage) message).getText();
                returnmsg+="\n";
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
        
        return Response
                        .ok(returnmsg)
                        .build();
            } 
     @GET
    @Path("/12")
    public Response dohvatiGradove(){
           
        try {
            String s= "http://localhost:8080/Sistem1/resources/funckije/12/";
            
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem1);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "GET");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
              
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora1);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                return Response
                        .ok(((TextMessage) message).getText())
                        .build();
                
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
            
            } 
   
    
    @GET
    @Path("/13")
    public Response dohvatiKorisnike(){
           
        try {
            String s= "http://localhost:8080/Sistem1/resources/funckije/13/";
            
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem1);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "GET");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
              
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora1);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                return Response
                        .ok(((TextMessage) message).getText())
                        .build();
                
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
            
            } 
    
    
     @GET
    @Path("/14")
    public Response dohvatiKategorije(){
           
        try {
            String s= "http://localhost:8080/Sistem2/resources/funckije/14/";
            
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem2);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "GET");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
              
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora2);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                return Response
                        .ok(((TextMessage) message).getText())
                        .build();
                
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
            
            } 
    
    
      @GET
    @Path("/15/{username}")
    public Response dohvatiArtikle(@PathParam("username") String username){
           
        try {
            String s= "http://localhost:8080/Sistem2/resources/funckije/15/"+username;
            
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem2);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "GET");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
              
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora2);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                return Response
                        .ok(((TextMessage) message).getText())
                        .build();
                
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
            
            } 
    
    
      @GET
    @Path("/16/{username}")
    public Response dohvatiKorpu(@PathParam("username") String username){
           
        try {
            String s= "http://localhost:8080/Sistem2/resources/funckije/16/"+username;
            
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem2);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "GET");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
              
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora2);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                return Response
                        .ok(((TextMessage) message).getText())
                        .build();
                
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
            
            } 
    
    
    
    @GET
    @Path("/17/{username}")
    public Response dohvatiSveNarudzbinezaUsera(@PathParam("username") String username){
           
        try {
            String s= "http://localhost:8080/Sistem3/resources/funckije/17/"+username;
            
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem3);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "GET");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
              
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora3);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                return Response
                        .ok(((TextMessage) message).getText())
                        .build();
                
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
            
            } 
    
    
   
    
     @GET
    @Path("/18")
    public Response dohvatiSveNarudzbine(){
           
        try {
            String s= "http://localhost:8080/Sistem3/resources/funckije/18/";
            
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem3);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "GET");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
              
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora3);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                return Response
                        .ok(((TextMessage) message).getText())
                        .build();
                
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
            
            } 
    
    
    
     @GET
    @Path("/19")
    public Response dohvatisveTransakcije(){
           
        try {
            String s= "http://localhost:8080/Sistem3/resources/funckije/19/";
            
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(qpodsistem3);
            producer.setPriority(9);
            Message message = session.createTextMessage(s);
            
            message.setStringProperty("Metod", "GET");
            message.setIntProperty("Tip", 1);
            producer.send(message);
            connection.close();
              
        
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(qodgovora3);
            connection.start();
            message = consumer.receive(5000);
            if (message instanceof TextMessage) {
                connection.close();
                return Response
                        .ok(((TextMessage) message).getText())
                        .build();
                
            }
            else{
               return Response
                        .status(Response.Status.CONFLICT)
                        .build(); 
            }
        } catch (JMSException ex) {
            
            Logger.getLogger(Funckije.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                        .status(Response.Status.CONFLICT)
                        .build();
        }
            
            } 
    
    
    
    
    }
    
    
    

