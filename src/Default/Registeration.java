package Default;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author panky4ulive
 */
public class Registeration {
    private final String name;
    private final String usrname;
    private final String pwd;
    private final String email;
    private final String num;
    private String noti;
    public Registeration(String name,String usrname,String pwd,String email,String num){
        this.name=name;
        this.usrname=usrname;
        this.pwd=pwd;
        this.email=email;
        this.num=num;
    }

    public boolean register() {
         boolean b=false;
         String s=Client.register(name, usrname, pwd, email, num);
        if(s.equals("_Successfull_")){
            b=true;
        } 
        else{
            noti=s;
        }
            return b;
    }

    public String notification() {
        
        return noti;
       //To change body of generated methods, choose Tools | Templates.
    }
    
}