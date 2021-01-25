/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controllers.LyricsTab;
import java.util.LinkedList;
import javafx.beans.binding.Bindings;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.web.WebEngine;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author HBadr
 */
public class Genius extends LyricsTab{

    private final String key = "ee0b3a7e09mshb39bb62b99ec98ep1662d5jsn43647b0343b6";
    private final String host = "genius.p.rapidapi.com";
    private final String idURL = "https://genius.p.rapidapi.com/search?q=";
    private final JSONObject track;
    private final JSONObject picturePath;
    private final int idNumber;
    private final String artistPicture;
    private final String artistBanner;
    public static LinkedList<String> searchResults = new LinkedList();
    private ListView list;
    private final JSONArray array;
    private int place;

    public Genius(String name) {
        String url = idURL + name;
        ConnectionToRapidAPI api = new ConnectionToRapidAPI(url, key, host);
        JSONObject job = api.getJsonObject();
        JSONObject tracks = job.getJSONObject("response");
        array = tracks.getJSONArray("hits");
        JSONObject temp = array.getJSONObject(0);
        track = temp.getJSONObject("result");
        idNumber = track.getInt("id");
        picturePath = track.getJSONObject("primary_artist");
        artistPicture = picturePath.getString("image_url");
        artistBanner = picturePath.getString("header_image_url");
    }
    
    public void songLyrics(ListView optionsList, String name) {
        JSONArray hitsArray = getHitsArray();
        this.list = optionsList;
        searchResults.clear();
        optionsList.getItems().removeAll();
        optionsList.getItems().clear();
        for (int count = 0; count < hitsArray.length(); count++) {
            JSONObject temp = hitsArray.getJSONObject(count);
            JSONObject result = temp.getJSONObject("result");
            String teme = result.getString("full_title");
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
            JSONObject tempo = hitsArray.getJSONObject(place);
            JSONObject result = tempo.getJSONObject("result");
            String urlLyrics = result.getString("url");
            WebEngine webq = static_showLyrics.getEngine();
            webq.load(urlLyrics);
        });
        contextMenu.getItems().addAll(viewLyrics);
        optionsList.setContextMenu(contextMenu);
    }
    
    private int getIDNumberFromName() {   
        return idNumber;
    }

    public String artistPicture() {
        return artistPicture;
    }

    public String getPicturePath() {
        return artistPicture;
    }

    public String artistBanner() {    
        return artistBanner;
    }
    
    public JSONArray getHitsArray(){
        return array;
    }
}
