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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import models.Genius;

/**
 *
 * @author HBadr
 */
public class LyricsTab implements Initializable{

    @FXML
    private TextField searchASong;
    @FXML
    private ListView optionsList;
    @FXML
    public WebView showLyrics;
    public static WebView static_showLyrics;
    public String nameWithoutSpaces;
    public static FXMLDocumentController tas;
    //private User USER = FXMLDocumentController.getUser();
    String unamee = bluey.FXMLDocumentController.uname;
    private LinkedList<String> searchResults = models.Genius.searchResults;

    @FXML
    private void handleSearchButton(ActionEvent _event) {
        if(this.optionsList != null ){
            this.optionsList.getItems().clear();
        }
        if(this.showLyrics != null){
            showLyrics.getEngine().loadContent("");
        }
        String artistChoice = this.searchASong.getText();
        this.nameWithoutSpaces = artistChoice.replaceAll(" ", "%20");
        Genius one = new Genius(nameWithoutSpaces);
        populateSongsOptionList();
    }

    public void populateSongsOptionList() {
        Genius two = new Genius();
        two.songLyrics(optionsList, nameWithoutSpaces);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
        static_showLyrics = showLyrics;
    }
    
   
}
