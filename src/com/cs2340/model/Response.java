package com.cs2340.model;

public class Response {
    public int sucess;
    public String message;
    public Object data;

    public Response(int sucess, String message, Object data) {
        this.sucess = sucess;
        this.message = message;
        this.data = data;
    }
}
