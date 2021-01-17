/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import bluey.FXMLDocumentController;
import java.util.LinkedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import models.Genius;
import models.Shazam;
import models.User;

/**
 *
 * @author HBadr
 */
public class LyricsTab{
    @FXML private  TextField searchASong;
    @FXML private Button search;
    @FXML private WebView lyrics; 
    @FXML private ListView optionsList;
    public String nameWithoutSpaces;
    public static FXMLDocumentController tas;
    //private User USER = FXMLDocumentController.getUser();
    String unamee = bluey.FXMLDocumentController.uname;
    private LinkedList<String> searchResults = models.Genius.searchResults;
    
     @FXML
    private void handleSearchButton(ActionEvent event){
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
        Genius two = new Genius ();
        two.songLyrics(optionsList, nameWithoutSpaces);
    }
}
