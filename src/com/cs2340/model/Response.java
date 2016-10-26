package com.cs2340.model;

public class Response<T> {
    public int success;
    public String message;
    public T data;

    public Response(int success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
