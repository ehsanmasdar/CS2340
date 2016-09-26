package com.cs2340.api;

import com.cs2340.model.Response;
import com.cs2340.model.User;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public class UserHandler {
    public static Response postRegister(User user){
        try {
            JSONObject jsonResponse = Unirest.post(Constants.URL_BASE + "/api/user/register")
                    .field("username", user.username)
                    .field("password", user.password)
                    .field("level", user.level).asJson().getBody().getObject();
            return new Response(jsonResponse.getInt("success"), "", null);
        } catch (UnirestException e) {
            e.printStackTrace();
            return new Response(0,"",null);
        }
    }
    public static Response postLogin(User user){
        try {
            JSONObject jsonResponse = Unirest.post(Constants.URL_BASE + "/api/user/login")
                    .field("username", user.username)
                    .field("password", user.password).asJson().getBody().getObject();
            return new Response(jsonResponse.getInt("success"),jsonResponse.getString("message"), null);
        } catch (UnirestException e) {
            e.printStackTrace();
            return new Response(0,"",null);
        }
    }
}
