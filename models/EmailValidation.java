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
public class EmailValidation {
    private final String key = "ee0b3a7e09mshb39bb62b99ec98ep1662d5jsn43647b0343b6";
    private final String host = "whoisapi-email-verification-v1.p.rapidapi.com";
    private final String test;
    public EmailValidation(String email){
        String url = "https://whoisapi-email-verification-v1.p.rapidapi.com/api/v1?apiKey=at_lLntAhPT0NwMdEjENAXot8D9r5HDe&emailAddress=" + email + "&outputformat=JSON";
        ConnectionToRapidAPI api = new ConnectionToRapidAPI(url, key, host);
        JSONObject catchAllCheck = api.getJsonObject();
        this.test = catchAllCheck.getString("catchAllCheck");
    }
    public String getTest(){
        return this.test;
    }
}
