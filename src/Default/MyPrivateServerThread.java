/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
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
              InetAddress ser=Main.ip;
            // TODO code application logic here
          /*  Enumeration e=NetworkInterface.getNetworkInterfaces();
            while(e.hasMoreElements()){
                NetworkInterface n=(NetworkInterface) e.nextElement();
             //  System.out.println(n.toString()+"  "+n.getInetAddresses());
                 if(n.getName().equals("eth1")){
                    //  System.out.println("jhgy1");
                    Enumeration seer=n.getInetAddresses();
                    while(seer.hasMoreElements()){
                        InetAddress ein=(InetAddress) seer.nextElement();
                        System.out.println(ein.getHostAddress()+" "+Main.ip.getHostAddress());
                        if(ein.getHostAddress().equals(Main.ip.getHostAddress())){
                       //      System.out.println("khyuy2");
                            ser=ein;
                            System.out.println(ein.toString());
                            break;
                        }
                    }
                    break;
                 }
            }*/
            ss=new ServerSocket(5645,55,ser);
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
