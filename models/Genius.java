/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.LinkedList;
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
    //private final String topSongsURL = "https://shazam.p.rapidapi.com/songs/list-artist-top-tracks?id=";
    public final LinkedList<String> top10SongsList = new LinkedList();
    private JSONArray artists;
    private final JSONObject track;
    private int idNumber;
    private String artistPicture;

    public Genius(String name) {
        String url = idURL + name;
        ConnectionToRapidAPI api = new ConnectionToRapidAPI(url, key, host);
        JSONObject job = api.getJsonObject();
        JSONObject tracks = job.getJSONObject("response");
        JSONArray array = tracks.getJSONArray("hits");
        JSONObject temp = array.getJSONObject(0);
        this.track = temp.getJSONObject("result");

    }

    public Genius() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int getIDNumberFromName() {
        this.idNumber = track.getInt("id");
        return idNumber;
    }

    public String artistPicture() {
        JSONObject picturePath = track.getJSONObject("primary_artist");
        this.artistPicture = picturePath.getString("image_url");
        return artistPicture;
    }
    public String getPicturePath(){
        return this.artistPicture;
    }
}
