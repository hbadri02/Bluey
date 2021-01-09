/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import models.Genius;

/**
 *
 * @author HBadr
 */
public class Dashboard {
    @FXML private TextField favArtist;
    @FXML private Button choose;
    @FXML private WebView favArtistWebView;
    private String artistChoice;
    
     @FXML
    private void handleChooseButton(ActionEvent event){
        artistChoice = this.favArtist.getText();
        String nameWithoutSpaces = artistChoice.replaceAll(" ", "%20");
        Genius one = new Genius(nameWithoutSpaces);
        final WebEngine web = favArtistWebView.getEngine();
        String image = one.artistPicture();
         web.load(image);
    }

}
