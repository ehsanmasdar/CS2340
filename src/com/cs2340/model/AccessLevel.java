package com.cs2340.model;


public enum AccessLevel {
    ADMIN("admin"),
    MANAGER("manager"),
    WORKER("worker"),
    USER("user");
    private String level;

    AccessLevel(String level) {
        this.level = level;
    }
    public String toString() {
        return level;
    }
}
