/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author HBadr
 */
public class ConnectionToRapidAPI {
    private JSONObject job;
    private String response;
    private boolean status;

    /**
     * Create a generic connection to a given api.
     * @param _url
     * @param _host
     * @param _key
     */
    public ConnectionToRapidAPI(String _url, String _key, String _host) {
        HttpResponse<JsonNode> resp;
        try {
            resp = Unirest.get(_url) 
                    .header("x-rapidapi-key", _key)
                    .header("x-rapidapi-host", _host)
                    .asJson();
            this.status = true;
            if (resp.getStatus() != 200) {
                this.status = false;
            }
            this.response = resp.getBody().toString();
            this.job = new JSONObject(resp.getBody().toString());
        } catch (UnirestException ex) {
            Logger.getLogger(ConnectionToRapidAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // ========== GETTERS ========== //

    public JSONObject getJsonObject() {
        return this.job;
    }


    public String getResponseString() {
        return this.response;
    }

    public boolean getStatus() {
        return this.status;
    }
}
