/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controllers.LyricsTab;
import java.util.LinkedList;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author HBadr
 */
public class Genius {

    private final String key = "ee0b3a7e09mshb39bb62b99ec98ep1662d5jsn43647b0343b6";
    private final String host = "genius.p.rapidapi.com";
    private final String idURL = "https://genius.p.rapidapi.com/search?q=";
    private JSONObject track;
    private JSONObject picturePath;
    private int idNumber;
    private String artistPicture;
    private String artistBanner;
    public static LinkedList<String> searchResults = new LinkedList();
    @FXML
    //public WebView showLyrics = LyricsTab.showLyrics;
    private ListView list;
    private JSONArray array;
    private JSONArray arrayl;

    private int place;

    public Genius(String name) {
        String url = idURL + name;
        ConnectionToRapidAPI api = new ConnectionToRapidAPI(url, key, host);
        JSONObject job = api.getJsonObject();
        JSONObject tracks = job.getJSONObject("response");
        array = tracks.getJSONArray("hits");
        JSONObject temp = array.getJSONObject(0);
        this.track = temp.getJSONObject("result");

    }

    public Genius() {

    }

    private int getIDNumberFromName() {
        this.idNumber = track.getInt("id");
        return idNumber;
    }

    public String artistPicture() {
        this.picturePath = track.getJSONObject("primary_artist");
        this.artistPicture = picturePath.getString("image_url");
        return artistPicture;
    }

    public String getPicturePath() {
        return this.artistPicture;
    }

    public String artistBanner() {
        this.picturePath = track.getJSONObject("primary_artist");
        this.artistBanner = picturePath.getString("header_image_url");
        return artistBanner;
    }

    public void songLyrics(ListView optionsList, String name) {
        String url = idURL + name;
        ConnectionToRapidAPI api = new ConnectionToRapidAPI(url, key, host);
        JSONObject job = api.getJsonObject();
        JSONObject tracks = job.getJSONObject("response");
        this.arrayl = tracks.getJSONArray("hits");
        this.list = optionsList;
        searchResults.clear();
        optionsList.getItems().removeAll();
        optionsList.getItems().clear();
        for (int count = 0; count < arrayl.length(); count++) {
            JSONObject temp = arrayl.getJSONObject(count);
            this.track = temp.getJSONObject("result");
            String teme = track.getString("full_title");
            searchResults.add(teme);
        }
        for (int i = 0; i < this.searchResults.size(); i++) {
            optionsList.getItems().add(this.searchResults.get(i));
        }
        ListCell<String> cell = new ListCell<>();
        ContextMenu contextMenu = new ContextMenu();
        MenuItem viewLyrics = new MenuItem();
        viewLyrics.textProperty().bind(Bindings.format("View lyrics"));
        viewLyrics.setOnAction(event -> {
            String nameee = cell.getItem();
            this.place = optionsList.getSelectionModel().getSelectedIndex();
            JSONObject tempo = arrayl.getJSONObject(place);
            this.track = tempo.getJSONObject("result");
            String urlLyrics = track.getString("url");
            //WebEngine webq = LyricsTab.showLyrics.getEngine();
            //webq.load(urlLyrics);

        });
        contextMenu.getItems().addAll(viewLyrics);
        optionsList.setContextMenu(contextMenu);
    }

    public String getLyricsURL() {
        JSONObject tempo = arrayl.getJSONObject(place);
        this.track = tempo.getJSONObject("result");
        String urlLyrics = track.getString("url");
        return urlLyrics;
    }
}
