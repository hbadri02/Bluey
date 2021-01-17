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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class DashboardTab implements Initializable{
    @FXML private  TextField favArtist;
    @FXML private Button choose;
    @FXML private WebView favArtistWebView; 
    @FXML private ListView listy;
    public String nameWithoutSpaces;
    public static FXMLDocumentController tas;
    //private User USER = FXMLDocumentController.getUser();
    String unamee = bluey.FXMLDocumentController.uname;
    private LinkedList<String> top10SongsList = models.Shazam.top10SongsList;
    
     @FXML
    private void handleChooseButton(ActionEvent event){
        String artistChoice = this.favArtist.getText();
         this.nameWithoutSpaces = artistChoice.replaceAll(" ", "%20");
        Genius one = new Genius(nameWithoutSpaces);
        WebEngine web = favArtistWebView.getEngine();
        String image = one.artistPicture();
        web.load(image);
        User.chooseFavoriteArtist(unamee, artistChoice);
        populateSongsList();   
    }
    public void populateSongsList() {
        User tempUser = new User();
        String pathFromUser = tempUser.getFavoriteArtist();
        String nameWithoutSpacesForList = pathFromUser.replaceAll(" ", "%20");
        Shazam two = new Shazam ();
        two.populatetop10SongsList(listy, nameWithoutSpacesForList);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateSongsList();
    }

}
