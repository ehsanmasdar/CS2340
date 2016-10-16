package com.cs2340.model;

public class Response<T> {
    public int sucess;
    public String message;
    public T data;

    public Response(int sucess, String message, T data) {
        this.sucess = sucess;
        this.message = message;
        this.data = data;
    }
}
