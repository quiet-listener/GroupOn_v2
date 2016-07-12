/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import GUI.Private_Room;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;

/**
 *
 * @author panky4ulive
 */
public class PrivateThread extends Thread{
       private Socket socket;
       private String friend;
       private DataInputStream dio;
       private DataOutputStream doi;
    public static LinkedList<String> outlist=new LinkedList();
    public static LinkedList<String> inlist=new LinkedList<>();
    Private_Room pr;
    PrivateThread(Socket socket, String friend,boolean flag) {
         //To change body of generated methods, choose Tools | Templates.
         this.socket=socket;
           try {
               dio=new DataInputStream(this.socket.getInputStream());
               doi=new DataOutputStream(this.socket.getOutputStream());
           } catch (IOException ex) {
               Logger.getLogger(PrivateThread.class.getName()).log(Level.SEVERE, null, ex);
           }
         if(flag){
             try {
                 this.friend=dio.readUTF();
             } catch (IOException ex) {
                 Logger.getLogger(PrivateThread.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         else{
             try {
                 doi.writeUTF(friend);
                 this.friend=friend;
             } catch (IOException ex) {
                 Logger.getLogger(PrivateThread.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
    }
       @Override
    public void run(){
        Thread input = null,output = null;
        System.out.println("hello");
           GetPrivate(this);
       // while(Client.flag){
        addFrame(friend,pr);
       input= new Thread(new Runnable() {
            @Override
            public void run() {
                 //To change body of generated methods, choose Tools | Templates.
                 while(Client.flag){
                     try {
                         String s=outgetElement();
                         doi.writeUTF(s);
                     } catch (IOException ex) {
                         Logger.getLogger(PrivateThread.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
            }
          });
       output=new Thread(new Runnable() {
            @Override
            public void run() {
                while(Client.flag){
                    try {
                        String s=dio.readUTF(); 
                        inaddElement(s);  
                        //To change body of generated methods, choose Tools | Templates.
                    } catch (IOException ex) {
                        Logger.getLogger(PrivateThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
       Thread print=new Thread(new Runnable() {
            @Override
            public void run() {
               while(Client.flag){
                   String s=ingetElement();
                   String aopdiw[]=s.split("\n");
                   String sqw[]=aopdiw[0].split(" >>>> ");
                    int len=sqw[0].length()+14;
                  char[] ch=new char[len];
                   Arrays.fill(ch,' ');
                pr.str.addElement(aopdiw[0]);           
             for(int i=1;i<aopdiw.length;i++)
                 pr.str.addElement( new String(ch)+aopdiw[i]);
                  pr.jList1=new JList<>(pr.str);
               }//To change body of generated methods, choose Tools | Templates.
            }
        });
   //   }  
       input.start();
       output.start();
       print.start();
           try {
               input.join();
                output.join();
                print.join();
           } catch (InterruptedException ex) {
               Logger.getLogger(PrivateThread.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    
    public synchronized void inaddElement(String s){
        inlist.offer(s);
        notifyAll();
    }
    public synchronized String ingetElement(){
        if(inlist.isEmpty()){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(PrivateThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return inlist.poll();
    }
    public synchronized void outaddElement(String s){
        outlist.offer(s);
        notifyAll();
    }
    public synchronized String outgetElement(){
        if(outlist.isEmpty()){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(PrivateThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return outlist.poll();
    }
    public String getFrien(){
        return friend;
    }
    synchronized void addFrame(String s,Private_Room pr){
        Peer_to_peer.jmap.put(s,pr);
    }
     public synchronized void GetPrivate(PrivateThread prrom) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        //Private_Room pri=null;
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Private_Room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            pr=new Private_Room(prrom);
            pr.setVisible(true);
            }
        });
    }
}
