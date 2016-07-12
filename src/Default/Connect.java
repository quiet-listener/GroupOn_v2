package Default;


import java.sql.Connection;
import java.sql.DriverManager;
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
  public class Connect {
      private static Connection con=null;
    public Connection getConnect(){
        return connecting();
    }
    private Connection connecting(){
        if(con!=null){
            return con;
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/groupon","root","pan104ulive");
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    public boolean closeConnection(Connection con){
        return closing(con);
    }
    private boolean closing(Connection con){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
           return true;
    }
}
