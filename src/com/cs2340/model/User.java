package com.cs2340.model;

public class User {
    public String username;
    public String password;
    public int failed;
    public boolean banned;
    public boolean blocked;
    public AccessLevel level;

    public User(String username, String password, AccessLevel level) {
        this.username = username;
        this.password = password;
        this.level = level;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, AccessLevel level) {
        this.username = username;
        this.level = level;
    }
}
