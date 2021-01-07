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
public class Shazam {
    private final String key = "ee0b3a7e09mshb39bb62b99ec98ep1662d5jsn43647b0343b6";
    private final String host = "shazam.p.rapidapi.com";
    private final String idURL = "https://shazam.p.rapidapi.com/search?term=";
    private final String topSongsURL = "https://shazam.p.rapidapi.com/songs/list-artist-top-tracks?id=";
    public final LinkedList<String> top10SongsList = new LinkedList();
    private JSONArray artists;
    private final JSONObject track;
    private int idNumber;
    
    public Shazam(String name){
        String url = idURL + name + "&locale=en-US&offset=0&limit=5";
        ConnectionToRapidAPI api = new ConnectionToRapidAPI(url, key, host);
        JSONObject job = api.getJsonObject();
        JSONObject tracks = job.getJSONObject("tracks");
        JSONArray array = tracks.getJSONArray("hits");
        JSONObject temp = array.getJSONObject(0);
        this.track = temp.getJSONObject("track");
        top10Songs(getIDNumberFromName());   
    }
    
    private int getIDNumberFromName(){
      this.artists = this.track.getJSONArray("artists");
        JSONObject id = artists.getJSONObject(0);
        this.idNumber = id.getInt("id");
        return idNumber;
    }
    
    private void top10Songs(int id){
        String url = topSongsURL + id + "&locale=en-US";
        ConnectionToRapidAPI api = new ConnectionToRapidAPI(url, key, host);
        JSONObject job = api.getJsonObject();
        JSONArray array = job.getJSONArray("tracks");
        for (int count = 0; count < 9; count++){
        JSONObject temp = array.getJSONObject(count);
        String teme = temp.getString("title");
        top10SongsList.add(teme);          
    }
    }
}