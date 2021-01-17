/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import bluey.FXMLDocumentController;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import models.Genius;
import models.Shazam;
import models.User;

/**
 *
 * @author HBadr
 */
public class LyricsTab implements Initializable{

    @FXML
    private TextField searchASong;
    @FXML
    private Button search;
    @FXML
    private WebView lyrics;
    @FXML
    private ListView optionsList;
    @FXML
    public static WebView showLyrics;
    public String nameWithoutSpaces;
    public static FXMLDocumentController tas;
    //private User USER = FXMLDocumentController.getUser();
    String unamee = bluey.FXMLDocumentController.uname;
    private LinkedList<String> searchResults = models.Genius.searchResults;

    @FXML
    private void handleSearchButton(ActionEvent event) {
        String artistChoice = this.searchASong.getText();
        this.nameWithoutSpaces = artistChoice.replaceAll(" ", "%20");
        Genius one = new Genius(nameWithoutSpaces);
        //WebEngine web = lyrics.getEngine();
        //String image = one.artistPicture();
        //web.load(image);
        //User.chooseFavoriteArtist(unamee, artistChoice);
        populateSongsOptionList();
    }

    public void populateSongsOptionList() {
        //User tempUser = new User();
        //String pathFromUser = tempUser.getFavoriteArtist();
        //String nameWithoutSpacesForList = pathFromUser.replaceAll(" ", "%20");
        Genius two = new Genius();
        two.songLyrics(optionsList, nameWithoutSpaces);
        
        
    }
    
    public void initialize(URL location, ResourceBundle resources){
        
    }
    
   
}
