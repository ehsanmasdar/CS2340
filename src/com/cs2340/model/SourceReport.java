package com.cs2340.model;


public class SourceReport {
    private String name;
    public double lat;
    public double lon;
    public String type;
    public String condition;
    private String id;
    private String date;

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

    @Override
    public String toString(){
        StringBuilder build = new StringBuilder();
        build.append("Submitted by: " + name + "; ");
        build.append("Location: (" + lat + ", " + lon + "); ");
        build.append("Type: " + type + "; ");
        build.append("Condition: " + condition + "; ");
        build.append("Report Id: " + id +"; ");
        build.append("Date: " + date);

        return build.toString();
    }
}
