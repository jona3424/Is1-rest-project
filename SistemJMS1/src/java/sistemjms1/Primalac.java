/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemjms1;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.TextMessage;
import static sistemjms1.Main.connectionFactory;
import static sistemjms1.Main.qodgovora;
import static sistemjms1.Main.qpodsistem1;

/**
 *
 * @author jona
 */
public class Primalac extends Thread {

    @Override
    public void run() {

        JMSContext context = connectionFactory.createContext();
        JMSConsumer consumer = context.createConsumer(qpodsistem1,"Tip=1");
        JMSProducer jMSProducer=context.createProducer();

        while (true) {
            try {
                TextMessage message = (TextMessage) consumer.receive(5000);
                if(message==null)continue;
                String odg = message.getText();
                String property = message.getStringProperty("Metod");
                System.out.println("Dobili poruku od Centralnog sistema za metod " + property);
                URL url = new URL(odg);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod(property);

                int responseCode = con.getResponseCode();
                System.out.println("Response Code: " + responseCode);
                Scanner scanner = new Scanner(con.getInputStream());
                String response = scanner.useDelimiter("\\A").next();
                scanner.close();
                System.out.println(response);
                TextMessage msg=context.createTextMessage(response);
                jMSProducer.send(qodgovora, msg);
                
                
                
                
                
            } catch (JMSException ex) {
                Logger.getLogger(Primalac.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Primalac.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Primalac.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}

