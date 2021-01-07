/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author HBadr
 */
public class ConnectionToDatabase {

    private Connection con;
    public static boolean DEBUG;

    public ConnectionToDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9384753", "sql9384753", "Fck1T7QhJM");
            System.out.println("DB connection successful");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnectionToDatabase.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("or not");
        }
    }
    
    /**
     * Insert data to the "users" table.
     *
     * @param _username
     * @param _password
     * @param _email
     * @param _favoriteArtist
     * @param _profilePicture
     */
    public void userDatabase(String _username, String _password, String _email, String _favoriteArtist, String _profilePicture) {
        try {
            /**
             * This works
             */
            // Insert statement, using prepared statements
            String query = " INSERT INTO users (Username, Password, Email, FavoriteArtist, ProfilePicture)"
                    + " VALUES (?, ?, ?, ?, ?)";
            String hashedPass = getSHA256(_password);
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = this.con.prepareStatement(query);
            preparedStmt.setString(1, _username);
            preparedStmt.setString(2, hashedPass);
            preparedStmt.setString(3, _email);
            preparedStmt.setString(4, _favoriteArtist);
            preparedStmt.setString(5, _profilePicture);
            // execute the preparedstatement
            preparedStmt.execute();
            if (DEBUG){System.out.println("Registration Success");}
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public String getSHA256(String _input) {
        // MessageDigest used to hash bytes from input string
        MessageDigest md = null;
        try {
            // Select hash type
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ConnectionToDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Add hashed bytes to byte array
        byte[] hash = md.digest(_input.getBytes(StandardCharsets.UTF_8));
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexStr = new StringBuilder(number.toString(16));
        while (hexStr.length() < 32) {
            hexStr.insert(0, '0');
        }
        return hexStr.toString();
    }
     /**
     * Get the id of a user given their username.
     * @param _username
     * @return
     */
    public int getIdFromUsername(String _username) {
        int id = -1;
        try {
            // Insert statement, using prepared statements
            String query = "SELECT * from users where username = '" + _username + "'";
            // create the mysql insert preparedstatement
            if (DEBUG){
                System.out.println(_username + " user");
            }
            PreparedStatement preparedStmt = this.con.prepareStatement(query);
            ResultSet result = preparedStmt.executeQuery(query);
            while(result.next()) {
                id = result.getInt("userID");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return id;
    }
    
    public boolean validateLogin(String _userName, String _userPass) {
        boolean isValid = false;
        try {
            // Insert statement, using prepared statements
            String query = "SELECT * from users where Username = '" + _userName + "'";
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = this.con.prepareStatement(query);
//            preparedStmt.setString(1, _userName);
//            preparedStmt.execute();
            ResultSet result = preparedStmt.executeQuery(query);
            String userName = "";
            String hash = "";
            // Get values from data fields in database
            while (result.next()) {
                userName = result.getString("Username");
                hash = result.getString("Password");
            }
            // Hash user input
            String hashedPass = getSHA256(_userPass);
            // Compare both hashes
            return validatePassword(_userPass, hash);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return isValid;
    }
    
    private boolean validatePassword(String _password, String _dbHash) {
        return getSHA256(_password).equals(_dbHash);
    }

    void close() {
        try {
            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionToDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}