/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.File;

/**
 *
 * @author HBadr
 */
public class User {

    private final String username;
    private final String password;
    private String email;
    private String path;
    private static boolean changed;
    private static ConnectionToDatabase connect;
    String unamee = bluey.FXMLDocumentController.uname;
    public User() {
        username = unamee;
        password = "";
    }

    public User(String _username, String _password) {
        this.username = _username;
        this.password = _password;
        this.connect = new ConnectionToDatabase();
    }

    public static boolean usernameAcceptable(String _uname, String _password, String _email, String _favArtist, String _profilePic) {
        // Call DB connection class
        ConnectionToDatabase conn = new ConnectionToDatabase();
        conn.userDatabase(_uname, _password, _email, _favArtist, _profilePic);
        conn.close();
        System.out.println("Connection successful to lvaidate");
        return true;
    }
    
    public static boolean loginInformationAcceptable(String _uname, String _password){
        ConnectionToDatabase conn = new ConnectionToDatabase();
        if(conn.validateLogin(_uname, _uname)){
            return true;
        }
        return changed; 
    }

    
    public static boolean chooseFavoriteArtist(String _uname, String _aname){
        ConnectionToDatabase conn = new ConnectionToDatabase();
        if(conn.changeFavoriteArtist(_uname, _aname)){
            return true;
        }
        conn.close();
        return changed;
        
    }
    
     public static boolean changeProfilePicture(String _uname, String path){
        ConnectionToDatabase connect = new ConnectionToDatabase();
        if(connect.changeProfilePicture(_uname, path)){
            return true;
        }
        else
            return changed;
    }

    public String getFavoriteArtist(){
        String favArtist;
        ConnectionToDatabase conn = new ConnectionToDatabase();
        favArtist = conn.getFavoriteArtistFromUsername(unamee);
        conn.close();
        return favArtist;
    }
    
    public String getProfilePicture(){
        ConnectionToDatabase conn = new ConnectionToDatabase();
        String profilePicture = conn.getProfilePictureFromUsername(username);
        conn.close();
        return profilePicture;
    }
}
