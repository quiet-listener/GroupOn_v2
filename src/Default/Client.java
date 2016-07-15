package Default;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Client {
    private static Socket socket;
    private static DataInputStream dio;
    private static DataOutputStream doi;
    private static Peer_to_peer p2p;
    public static boolean flag=false;
    private static MyPrivateServerThread mpst;
    static void close() {
        try {
            flag=false;
            doi.writeUTF("_CLOSE_");
            doi.close();
            doi.close();
            socket.close();
            Talks.close();
            System.out.println("Now For thread1");
            //    mpst.join();//To change body of generated methods, choose Tools | Templates.
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static boolean friend(String friend) {
         boolean b = false;
        try {
            //To change body of generated methods, choose Tools | Templates.
            doi.writeUTF("_FRIEND_");
            doi.writeUTF(friend);
            String s=dio.readUTF();
            if(s.equals("_logout_")){
                JOptionPane.showMessageDialog(null,"User Left Room","Connection Status",JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            p2p=new Peer_to_peer(s,friend);
            if(p2p.connected(friend)){
                b=true;
            }
            else{
                JOptionPane.showMessageDialog(null,"Connection failure","Connection Status",JOptionPane.INFORMATION_MESSAGE);
            }
            System.out.println(s);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return b;
    }

    static void post(String say) {
        String s=say;
//        for(int i=0;i<say.length;i++){
//            s+="\n"+say[i];
//        }
            try {
                doi.writeUTF(s);//To change body of generated methods, choose Tools | Templates.
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    private Client(String usr,String pwd){
        try{
            socket=new Socket("172.31.76.214",5647);
            dio=new DataInputStream(socket.getInputStream());
            doi=new DataOutputStream(socket.getOutputStream());
            doi.writeUTF("_Login_");
            doi.writeUTF(usr);
            doi.writeUTF(pwd);
            if((dio.readUTF()).equals("_NO_")){
                System.out.println(socket.getInetAddress()+"435");
                close();
            }
        }
        catch(UnknownHostException ex){
            JOptionPane.showMessageDialog(null,"Server down for Maintainance","Server Down",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(IOException ex){
            
        }
    }
    public static boolean getInstanceOfClient(String usr,String pwd){
        if(socket==null || socket.isClosed()){
            new Client(usr,pwd);
        }
        if(socket.isClosed()){
            return false;
        }
        flag=true;
        Main.ip=socket.getLocalAddress();
        mpst=new MyPrivateServerThread(usr);
        mpst.start();
        return true;
    }
    public static ArrayList<String> refresh(){
        String s=new String("_REFRESH_");
        ArrayList<String> ar=new ArrayList();
        try {
            doi.writeUTF(s);
        int n=Integer.parseInt(dio.readUTF());
        for(int i=0;i<n;i++){
            String srr=dio.readUTF();
            if(srr.equals(Main.user)){
                continue;
            }
            ar.add(srr);
          }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ar;
    }
    public static String register(String name,String usrname,String pwd,String email,String num){
        String s=null;
        try {
            socket=new Socket("172.31.76.214",5647);
            socket.setReuseAddress(true);
            dio=new DataInputStream(socket.getInputStream());
            doi=new DataOutputStream(socket.getOutputStream());
            doi.writeUTF("_REGISTER_");
            doi.writeUTF(name);
            doi.writeUTF(usrname);
            doi.writeUTF(pwd);
            doi.writeUTF(email);
            doi.writeUTF(num);
            s=dio.readUTF();
            doi.flush();
            doi.close();
            dio.close();
            socket.close();
            return s;
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
}
