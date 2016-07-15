/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import GUI.Private_Room;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author panky4ulive
 */
class Peer_to_peer {
    private Socket socket;
    static Map<String,Private_Room >jmap=new HashMap();
    PrivateThread pt;
    Peer_to_peer(String s,String friend) {
        try {
            System.out.println(InetAddress.getByName(s).toString()+"  p2p");
            socket=new Socket(InetAddress.getByName(s),5645);
            //To change body of generated methods, choose Tools | Templates.
             this.pt=new PrivateThread(socket,friend,false);
            this.pt.start();
            
        } catch (IOException ex) {
            Logger.getLogger(Peer_to_peer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    synchronized boolean connected(String s) {
        //To change body of generated methods, choose Tools | Templates.
       return jmap.containsKey(s);
    }
    
}
