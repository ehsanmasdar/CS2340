package com.cs2340.model;


public class SourceReport {
    public String name;
    public double lat;
    public double lon;
    public String type;
    public String condition;
    public String id;
    public String date;

    public SourceReport(String name, double lat, double lon, String type, String condition) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.type = type;
        this.condition = condition;
    }

    public SourceReport(String name, double lat, double lon, String type, String condition, String id, String date) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.type = type;
        this.condition = condition;
        this.id = id;
        this.date = date;
    }
}
