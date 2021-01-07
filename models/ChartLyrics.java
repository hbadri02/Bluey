/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import org.json.JSONObject;

/**
 *
 * @author HBadr
 */
public class ChartLyrics {
    private final String key = "ee0b3a7e09mshb39bb62b99ec98ep1662d5jsn43647b0343b6";
    private final String host = "sridurgayadav-chart-lyrics-v1.p.rapidapi.com";
    private final String test;
    public ChartLyrics(String name, String song){
        String url = "https://sridurgayadav-chart-lyrics-v1.p.rapidapi.com/apiv1.asmx/SearchLyricDirect?artist=michael%20jackson&song=bad";
        ConnectionToRapidAPI api = new ConnectionToRapidAPI(url, key, host);
        JSONObject Lyric = api.getJsonObject();
        this.test = Lyric.getString("<Lyric>");
        System.out.println(Lyric);
    }
}
