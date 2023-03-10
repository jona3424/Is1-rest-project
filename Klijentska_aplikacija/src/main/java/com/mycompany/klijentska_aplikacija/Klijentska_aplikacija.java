/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.klijentska_aplikacija;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jona
 */
public class Klijentska_aplikacija {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int choice = 0;

        while (choice != -1) {
            System.out.println("Molimo vas da izaberete opciju:");
            System.out.println("1. Kreiraj grad");
            System.out.println("2. Kreiraj korisnika");
            System.out.println("3. Dodaj novac korisniku");
            System.out.println("4. Promeni adresu i grad korisnika");
            System.out.println("5. Kreiraj kategoriju");
            System.out.println("6. Kreiraj stavku");
            System.out.println("7. Promeni cenu stavke");
            System.out.println("8. Postavi popust za stavku");
            System.out.println("9. Dodaj stavku u korpu");
            System.out.println("10. Ukloni stavku iz korpe");
            System.out.println("11. Plati (kreiraj transakciju, porudžbinu i praznu korpu)");
            System.out.println("12. Pronađi sve gradove");
            System.out.println("13. Pronađi sve korisnike");
            System.out.println("14. Pronađi sve kategorije");
            System.out.println("15. Pronađi stavke koje su prodali korisnik koji je napravio zahtev");
            System.out.println("16. Pronađi sadržaj korpe za korisnika koji je napravio zahtev");
            System.out.println("17. Pronađi sve porudžbine za korisnika koji je napravio zahtev");
            System.out.println("18. Pronađi sve porudžbine");
            System.out.println("19. Pronađi sve transakcije");
            System.out.println("-1. Izlaz");

            choice = in.nextInt();

            switch (choice) {
                case 1:
                    in = new Scanner(System.in);
                    System.out.println("Unesite naziv grada");
                    String grad= in.nextLine();
            try {
                    
                    URL url = new URL("http://localhost:8080/CentralniServer/resources/funckije/1/"+grad);

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");

                    int responseCode = con.getResponseCode();
                    System.out.println("Response Code: " + responseCode);

                    Scanner scanner = new Scanner(con.getInputStream());
                    String response = scanner.useDelimiter("\\A").next();
                    System.out.println(response);
                    scanner.close();
                } catch (IOException ex) {
                    Logger.getLogger(Klijentska_aplikacija.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                case 2:
                    
                    in = new Scanner(System.in);
                    System.out.println("Unesite ime i prezime korisnika");
                    String imePrezime= in.nextLine();
                    System.out.println("Unesite username korisnika");
                    String user= in.nextLine();
                    System.out.println("Unesite sifru");
                    String sifra= in.nextLine();
                    System.out.println("Unesite adresu");
                    String adresa= in.nextLine();
                    System.out.println("Unesite grad");
                    grad= in.nextLine();
                    System.out.println("Unesite pare");
                    String pare= in.nextLine();
            try {
                    
                    URL url = new URL("http://localhost:8080/CentralniServer/resources/funckije/2/"+imePrezime+"/"+user+"/"+sifra+"/"+adresa+"/"+grad+"/"+pare);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    int responseCode = con.getResponseCode();
                    System.out.println("Response Code: " + responseCode);

                    Scanner scanner = new Scanner(con.getInputStream());
                    String response = scanner.useDelimiter("\\A").next();
                    System.out.println(response);
                    scanner.close();
                } catch (IOException ex) {
                    Logger.getLogger(Klijentska_aplikacija.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                    
                    break;
                case 3:
                     in = new Scanner(System.in);
                    
                    System.out.println("Unesite username korisnika");
                     user= in.nextLine();
   
                    System.out.println("Unesite pare");
                     pare= in.nextLine();
            try {
                    
                    URL url = new URL("http://localhost:8080/CentralniServer/resources/funckije/3/"+user+"/"+pare);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    int responseCode = con.getResponseCode();
                    System.out.println("Response Code: " + responseCode);

                    Scanner scanner = new Scanner(con.getInputStream());
                    String response = scanner.useDelimiter("\\A").next();
                    System.out.println(response);
                    scanner.close();
                } catch (IOException ex) {
                    Logger.getLogger(Klijentska_aplikacija.class.getName()).log(Level.SEVERE, null, ex);
                }
                    break;
                case 4:
                    in = new Scanner(System.in);
                    
                    System.out.println("Unesite username korisnika");
                    user= in.nextLine();
                    System.out.println("Unesite adresu");
                    adresa= in.nextLine();
                    System.out.println("Unesite grad");
                    grad= in.nextLine();
                    
            try {
                    
                    URL url = new URL("http://localhost:8080/CentralniServer/resources/funckije/4/"+user+"/"+grad+"/"+adresa);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    int responseCode = con.getResponseCode();
                    System.out.println("Response Code: " + responseCode);

                    Scanner scanner = new Scanner(con.getInputStream());
                    String response = scanner.useDelimiter("\\A").next();
                    System.out.println(response);
                    scanner.close();
                } catch (IOException ex) {
                    Logger.getLogger(Klijentska_aplikacija.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                    
                    break;
                    
                case 5:
                    in = new Scanner(System.in);
                    System.out.println("Unesite naziv kategorije");
                    String kategorija= in.nextLine();
                    System.out.println("Unesite naziv nadkategorije ako nema nadkategorije unesite 0");
                    String nadkategorija= in.nextLine();
            try {
                    
                    URL url = new URL("http://localhost:8080/CentralniServer/resources/funckije/5/"+kategorija+"/"+nadkategorija);

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");

                    int responseCode = con.getResponseCode();
                    System.out.println("Response Code: " + responseCode);

                    Scanner scanner = new Scanner(con.getInputStream());
                    String response = scanner.useDelimiter("\\A").next();
                    System.out.println(response);
                    scanner.close();
                } catch (IOException ex) {
                    Logger.getLogger(Klijentska_aplikacija.class.getName()).log(Level.SEVERE, null, ex);
                }
                    break;
                case 6:
                    
                    in = new Scanner(System.in);
                    System.out.println("Unesite naziv artikla");
                    String naziv= in.nextLine();
                    System.out.println("Unesite opis artikla");
                    String opis= in.nextLine();
                    System.out.println("Unesite popust");
                    String popust= in.nextLine();
                    System.out.println("Unesite cijenu");
                    String cijena= in.nextLine();
                    System.out.println("Unesite kategorija");
                    kategorija= in.nextLine();
                    System.out.println("Unesite prodavac");
                    String prodavac= in.nextLine();
            try {
                    
                    URL url = new URL("http://localhost:8080/CentralniServer/resources/funckije/6/"+naziv+"/"+opis+"/"+popust+"/"+cijena+"/"+kategorija+"/"+prodavac);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    int responseCode = con.getResponseCode();
                    System.out.println("Response Code: " + responseCode);

                    Scanner scanner = new Scanner(con.getInputStream());
                    String response = scanner.useDelimiter("\\A").next();
                    System.out.println(response);
                    scanner.close();
                } catch (IOException ex) {
                    Logger.getLogger(Klijentska_aplikacija.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                    
                    break;
                case 7:
                    in = new Scanner(System.in);
                    System.out.println("Unesite id artikla ciju cijenu mijenjate");
                    String idProizvoda= in.nextLine();
                    System.out.println("Unesite cijenu na koju mijenjate proizvod");
                    cijena= in.nextLine();
            try {
                    
                    URL url = new URL("http://localhost:8080/CentralniServer/resources/funckije/7/"+idProizvoda+"/"+cijena);

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");

                    int responseCode = con.getResponseCode();
                    System.out.println("Response Code: " + responseCode);

                    Scanner scanner = new Scanner(con.getInputStream());
                    String response = scanner.useDelimiter("\\A").next();
                    System.out.println(response);
                    scanner.close();
                } catch (IOException ex) {
                    Logger.getLogger(Klijentska_aplikacija.class.getName()).log(Level.SEVERE, null, ex);
                }


                    break;
                case 8:
                     in = new Scanner(System.in);
                    System.out.println("Unesite id artikla ciji popust mijenjate");
                     idProizvoda= in.nextLine();
                    System.out.println("Unesite popust na koju mijenjate popust proizvoda");
                    popust= in.nextLine();
            try {
                    
                    URL url = new URL("http://localhost:8080/CentralniServer/resources/funckije/8/"+idProizvoda+"/"+popust);

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");

                    int responseCode = con.getResponseCode();
                    System.out.println("Response Code: " + responseCode);

                    Scanner scanner = new Scanner(con.getInputStream());
                    String response = scanner.useDelimiter("\\A").next();
                    System.out.println(response);
                    scanner.close();
                } catch (IOException ex) {
                    Logger.getLogger(Klijentska_aplikacija.class.getName()).log(Level.SEVERE, null, ex);
                }

                    break;
                case 9:
                    
                    in = new Scanner(System.in);
                    System.out.println("Unesite id artikla koji dodajete u korpu");
                    idProizvoda= in.nextLine();
                    System.out.println("Unesite korisnika koji dodaje artikal");
                    user= in.nextLine();
                    System.out.println("Unesite kolicinu");
                    String kolicina= in.nextLine();
            try {
                    
                    URL url = new URL("http://localhost:8080/CentralniServer/resources/funckije/9/"+idProizvoda+"/"+user+"/"+kolicina);

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");

                    int responseCode = con.getResponseCode();
                    System.out.println("Response Code: " + responseCode);

                    Scanner scanner = new Scanner(con.getInputStream());
                    String response = scanner.useDelimiter("\\A").next();
                    System.out.println(response);
                    scanner.close();
                } catch (IOException ex) {
                    Logger.getLogger(Klijentska_aplikacija.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                    
                    break;
                case 10:
                    in = new Scanner(System.in);
                    System.out.println("Unesite id artikla koji uklanjate");
                    idProizvoda= in.nextLine();
                    System.out.println("Unesite korisnika kojem uklanjate artikal");
                    user= in.nextLine();
                    System.out.println("Unesite kolicinu");
                     kolicina= in.nextLine();
            try {
                    
                    URL url = new URL("http://localhost:8080/CentralniServer/resources/funckije/10/"+idProizvoda+"/"+user+"/"+kolicina);

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");

                    int responseCode = con.getResponseCode();
                    System.out.println("Response Code: " + responseCode);

                    Scanner scanner = new Scanner(con.getInputStream());
                    String response = scanner.useDelimiter("\\A").next();
                    System.out.println(response);
                    scanner.close();
                } catch (IOException ex) {
                    Logger.getLogger(Klijentska_aplikacija.class.getName()).log(Level.SEVERE, null, ex);
                }
                  

                    break;
                case 11:
                    in = new Scanner(System.in);
                    System.out.println("Unesite korisnicko ime korisnika koji kupuje");
                    user= in.nextLine();
                    System.out.println("Unesite adresu");
                    adresa= in.nextLine();
                    System.out.println("Unesite grad");
                    grad= in.nextLine();
            try {
                    
                    URL url = new URL("http://localhost:8080/CentralniServer/resources/funckije/11/"+user+"/"+adresa+"/"+grad);

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");

                    int responseCode = con.getResponseCode();
                    System.out.println("Response Code: " + responseCode);

                    Scanner scanner = new Scanner(con.getInputStream());
                    String response = scanner.useDelimiter("\\A").next();
                    System.out.println(response);
                    scanner.close();
                } catch (IOException ex) {
                    Logger.getLogger(Klijentska_aplikacija.class.getName()).log(Level.SEVERE, null, ex);
                }
                  
                    break;
                case 12:
                   
            try {
                    
                    URL url = new URL("http://localhost:8080/CentralniServer/resources/funckije/12/");

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");

                    int responseCode = con.getResponseCode();
                    System.out.println("Response Code: " + responseCode);

                    Scanner scanner = new Scanner(con.getInputStream());
                    String response = scanner.useDelimiter("\\A").next();
                    System.out.println(response);
                    scanner.close();
                } catch (IOException ex) {
                    Logger.getLogger(Klijentska_aplikacija.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                    
                case 13:
                    try {
                    
                    URL url = new URL("http://localhost:8080/CentralniServer/resources/funckije/13/");

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");

                    int responseCode = con.getResponseCode();
                    System.out.println("Response Code: " + responseCode);

                    Scanner scanner = new Scanner(con.getInputStream());
                    String response = scanner.useDelimiter("\\A").next();
                    System.out.println(response);
                    scanner.close();
                } catch (IOException ex) {
                    Logger.getLogger(Klijentska_aplikacija.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                case 14:
                try {
                    
                    URL url = new URL("http://localhost:8080/CentralniServer/resources/funckije/14/");

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");

                    int responseCode = con.getResponseCode();
                    System.out.println("Response Code: " + responseCode);

                    Scanner scanner = new Scanner(con.getInputStream());
                    String response = scanner.useDelimiter("\\A").next();
                    System.out.println(response);
                    scanner.close();
                } catch (IOException ex) {
                    Logger.getLogger(Klijentska_aplikacija.class.getName()).log(Level.SEVERE, null, ex);
                }
                    break;
                case 15:
                    in = new Scanner(System.in);
                    System.out.println("Unesite korisnika za kojeg trazite koje artikle prodaje");
                    user= in.nextLine();
                   
            try {
                    
                    URL url = new URL("http://localhost:8080/CentralniServer/resources/funckije/15/"+user);

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");

                    int responseCode = con.getResponseCode();
                    System.out.println("Response Code: " + responseCode);

                    Scanner scanner = new Scanner(con.getInputStream());
                    String response = scanner.useDelimiter("\\A").next();
                    System.out.println(response);
                    scanner.close();
                } catch (IOException ex) {
                    Logger.getLogger(Klijentska_aplikacija.class.getName()).log(Level.SEVERE, null, ex);
                }

                    break;
                case 16:
                    in = new Scanner(System.in);
                    System.out.println("Unesite korisnika za kojeg trazite artikle u korpi");
                    user= in.nextLine();
                   
            try {
                    
                    URL url = new URL("http://localhost:8080/CentralniServer/resources/funckije/16/"+user);

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");

                    int responseCode = con.getResponseCode();
                    System.out.println("Response Code: " + responseCode);

                    Scanner scanner = new Scanner(con.getInputStream());
                    String response = scanner.useDelimiter("\\A").next();
                    System.out.println(response);
                    scanner.close();
                } catch (IOException ex) {
                    Logger.getLogger(Klijentska_aplikacija.class.getName()).log(Level.SEVERE, null, ex);
                }                    break;
                case 17:
                    in = new Scanner(System.in);
                    System.out.println("Unesite korisnika za kojeg trazite sve narudzbine");
                    user= in.nextLine();
                   
            try {
                    
                    URL url = new URL("http://localhost:8080/CentralniServer/resources/funckije/17/"+user);

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");

                    int responseCode = con.getResponseCode();
                    System.out.println("Response Code: " + responseCode);

                    Scanner scanner = new Scanner(con.getInputStream());
                    String response = scanner.useDelimiter("\\A").next();
                    System.out.println(response);
                    scanner.close();
                } catch (IOException ex) {
                    Logger.getLogger(Klijentska_aplikacija.class.getName()).log(Level.SEVERE, null, ex);
                }

                    break;
                case 18:
                    try {

                      URL url = new URL("http://localhost:8080/CentralniServer/resources/funckije/18/");

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");

                    int responseCode = con.getResponseCode();
                    System.out.println("Response Code: " + responseCode);

                    Scanner scanner = new Scanner(con.getInputStream());
                    String response = scanner.useDelimiter("\\A").next();
                    System.out.println(response);
                    scanner.close();
                } catch (IOException ex) {
                    Logger.getLogger(Klijentska_aplikacija.class.getName()).log(Level.SEVERE, null, ex);
                }
                    break;
                case 19:
                    try {

                      URL url = new URL("http://localhost:8080/CentralniServer/resources/funckije/19/");

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");

                    int responseCode = con.getResponseCode();
                    System.out.println("Response Code: " + responseCode);

                    Scanner scanner = new Scanner(con.getInputStream());
                    String response = scanner.useDelimiter("\\A").next();
                    System.out.println(response);
                    scanner.close();
                } catch (IOException ex) {
                    Logger.getLogger(Klijentska_aplikacija.class.getName()).log(Level.SEVERE, null, ex);
                }
                    break;
                case -1:
                    System.out.println("POYPOY!");
                    break;
                default:
                    System.out.println("Pogresan unos pokusaj opet");
            }
        }
    }
}
