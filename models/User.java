/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author HBadr
 */
public class User {

    private final String username;
    private final String password;
    private String email;
    private String path;
    private final ConnectionToDatabase CONN;

    public User(String _username, String _password) {
        this.username = _username;
        this.password = _password;
        this.CONN = new ConnectionToDatabase();
    }

    public static boolean usernameAcceptable(String _uname, String _password, String _email, String _favArtist, String _profilePic) {
        // Call DB connection class
        ConnectionToDatabase conn = new ConnectionToDatabase();
        conn.userDatabase(_uname, _password, _email, _favArtist, _profilePic);
        conn.close();
        System.out.println("Connection successful");
        return true;
    }
    
    public static boolean loginInformationAcceptable(String _uname, String _password){
        boolean isValid = false;
        ConnectionToDatabase conn = new ConnectionToDatabase();
        if(conn.validateLogin(_uname, _uname)){
            return true;
        }
        return isValid; 
    }
}
