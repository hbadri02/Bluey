/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import bluey.FXMLDocumentController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import models.Genius;
import models.User;

/**
 *
 * @author HBadr
 */
public class Dashboard {
    @FXML private  TextField favArtist;
    @FXML private Button choose;
    @FXML private WebView favArtistWebView; 
    @FXML private WebView bannerviewww;
    public String artistChoice;
    public static FXMLDocumentController tas;
    //private User USER = FXMLDocumentController.getUser();
    String unamee = bluey.FXMLDocumentController.uname;
    
     @FXML
    private void handleChooseButton(ActionEvent event){
        this.artistChoice = this.favArtist.getText();
        String nameWithoutSpaces = artistChoice.replaceAll(" ", "%20");
        Genius one = new Genius(nameWithoutSpaces);
        final WebEngine web = favArtistWebView.getEngine();
        String image = one.artistPicture();
        web.load(image);
        User.chooseFavoriteArtist(unamee, artistChoice);
        
    }

}
