/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author panky4ulive
 */
 public class Talks extends Thread{
     static boolean flag=true;
    static void close(){
        flag=false;
    }
     DefaultListModel<String> adp;
     JList<String> jlist2;
    public Talks(DefaultListModel<String> adp, JList<String> jList2) {
      this.adp=adp;
      this.jlist2=jList2;
    }
    public void run(){
         try {
            // int port=5649;
            ServerSocket ss=new ServerSocket(5988); 
         while(flag){          
             Socket s=ss.accept();
             ss.setReuseAddress(true);
             s.setReuseAddress(true);
             DataInputStream dio=new DataInputStream(s.getInputStream());
             String sq23[]=dio.readUTF().split("\n");
             String sqw[]=sq23[0].split(" >>>> ");
             int len=sqw[0].length()+14;
             char[] ch=new char[len];
             Arrays.fill(ch,' ');
             adp.addElement(sq23[0]);           
             for(int i=1;i<sq23.length;i++)
             adp.addElement( new String(ch)+sq23[i]);
             jlist2=new JList(adp);
             dio.close();
             s.close();
            
         }
           ss.close();
         } catch (IOException ex) {
             Logger.getLogger(Talks.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
}
