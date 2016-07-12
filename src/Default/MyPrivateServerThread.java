/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author panky4ulive
 */
class MyPrivateServerThread extends Thread{
    ServerSocket ss;
    String usr;
    MyPrivateServerThread(String usr) {
        try {
            this.usr=usr;
            //To change body of generated methods, choose Tools | Templates.
            ss=new ServerSocket(5645);
        } catch (IOException ex) {
            Logger.getLogger(MyPrivateServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run(){
         System.out.println("mpst1 "+Client.flag);
        try {
            while(Client.flag){
                try {
                    // System.out.println("mpst21 "+Client.flag);
                    Socket s=ss.accept();
                  //  System.out.println("mpst21 "+Client.flag);
                    PrivateThread pts=new PrivateThread(s,usr,true);       
                    pts.start();
            //         System.out.println("mpst21 "+Client.flag);
                } catch (IOException ex) {
                    Logger.getLogger(MyPrivateServerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
           //     System.out.println(Client.flag);
            }
       //      System.out.println(Client.flag+"  MyPrivatend123 1212");
            ss.close();
        } catch (IOException ex) {
            Logger.getLogger(MyPrivateServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("MyPrivateend 1212");
    }
}
