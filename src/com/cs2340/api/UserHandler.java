package com.cs2340.api;

import com.cs2340.model.Profile;
import com.cs2340.model.Response;
import com.cs2340.model.User;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.MultipartBody;
import com.mashape.unirest.http.HttpResponse;
import org.json.JSONObject;

public class UserHandler {
    public static Response postRegister(User user){
        try {
            JSONObject jsonResponse = Unirest.post(Constants.URL_BASE + "/api/user/register")
                    .field("username", user.username)
                    .field("password", user.password)
                    .field("level", user.level).asJson().getBody().getObject();
            if (jsonResponse.has("message")){
                return new Response(jsonResponse.getInt("success"), jsonResponse.getString("message"), null);
            }
            else{
                return new Response(jsonResponse.getInt("success"), "", null);
            }
        } catch (UnirestException e) {
            e.printStackTrace();
            return new Response(0,"",null);
        }
    }
    public static Response<String> postLogin(User user){
        try {
            MultipartBody resp = Unirest.post(Constants.URL_BASE + "/api/user/login")
                    .field("username", user.username)
                    .field("password", user.password);
            JSONObject jsonResponse = resp.asJson().getBody().getObject();
            HttpResponse response = resp.asString();
            String cookie = response.getHeaders().getFirst("Set-Cookie");
            if (jsonResponse.has("message")){
                return new Response(jsonResponse.getInt("success"), jsonResponse.getString("message"), null);
            }
            else{
                return new Response<String>(jsonResponse.getInt("success"), "", cookie);
            }
        } catch (UnirestException e) {
            e.printStackTrace();
            return new Response(0,"",null);
        }
    }
    public static Response postProfile(Profile p, String cookie){
        try {
            JSONObject jsonResponse = Unirest.post(Constants.URL_BASE + "/api/user/profile").header("Cookie", cookie)
                    .field("firstname", p.name)
                    .field("lastname", "empty")
                    .field("address", p.address)
                    .field("email", p.email)
                    .asJson().getBody().getObject();
            if (jsonResponse.has("message")){
                return new Response(jsonResponse.getInt("success"), jsonResponse.getString("message"), null);
            }
            else{
                return new Response(jsonResponse.getInt("success"), "", null);
            }
        } catch (UnirestException e) {
            e.printStackTrace();
            return new Response(0,"",null);
        }
    }
    public static Response<Profile> getProfile(String cookie){
        try {
            JSONObject jsonResponse = Unirest.get(Constants.URL_BASE + "/api/user/profile").header("Cookie", cookie).asJson().getBody().getObject();
            if (jsonResponse.getInt("success") == 1){
                JSONObject data = jsonResponse.getJSONObject("data");
                if (data.has("firstname"))
                    return new Response<Profile>(jsonResponse.getInt("success"), "", new Profile(data.getString("firstname"), data.getString("address"), data.getString("email")));
                return new Response<Profile>(jsonResponse.getInt("success"), "", new Profile("","",""));
            }
            else{
                System.out.println(jsonResponse.getString("message"));
                return new Response(jsonResponse.getInt("success"), "", null);
            }
        } catch (UnirestException e) {
            e.printStackTrace();
            return new Response(0,"",null);
        }
    }
}
