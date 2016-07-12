/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author panky4ulive
 */
public class ChatRoom_bean implements Serializable{
        String usr;
        String pwd;
        String friend;

    public ChatRoom_bean(String usr) {
        this.usr = usr;
    }
    public String getFriend() {
        return friend;
    }
    public void setFriend(String friend) {
        this.friend = friend;
    }
    public String getUsr() {
        return usr;
    }
    public boolean friend_Connected() {
         //To change body of generated methods, choose Tools | Templates.
         return Client.friend(friend);
    }
    public void logout() {
         //To change body of generated methods, choose Tools | Templates.
         Client.close();
    }

    public void post(String say) {
       Client.post(say);
    }

    public ArrayList<String> refreshList() {
         //To change body of generated methods, choose Tools | Templates.
         return Client.refresh();
    }
    
}
