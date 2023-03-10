/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemjms1;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;

/**
 *
 * @author jona
 */
public class Main {

    @Resource(lookup = "jms/__defaultConnectionFactory")
    static  ConnectionFactory connectionFactory;
    @Resource(lookup = "podsistem1Queue")
    static Queue qpodsistem1;
    @Resource(lookup = "odgovor1")
    static Queue qodgovora;
    public static void main(String[] args) {
       
        Primalac p= new Primalac();
        p.start();
      
    }
    
}
