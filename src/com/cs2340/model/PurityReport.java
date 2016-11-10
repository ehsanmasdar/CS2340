package com.cs2340.model;

public class PurityReport {
    private String name;
    public double lat;
    public double lon;
    private String id;
    public String date;
    public String condition;
    public int virus;
    public int contaminant;

    public PurityReport(String name, double lat, double lon, String condition, int virus, int contaminant, String id, String date) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.id = id;
        this.date = date;
        this.condition = condition;
        this.virus = virus;
        this.contaminant = contaminant;
    }

    public PurityReport(String name, double lat, double lon, String condition, int virus, int contaminant) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.condition = condition;
        this.virus = virus;
        this.contaminant = contaminant;
    }
    @Override
    public String toString(){
        return "Submitted by: " + name + "; " +
                "Location: (" + lat + ", " + lon + "); " +
                "Condition: " + condition + "; " +
                "Virus PPM: " + virus + "; " +
                "Contaminant PPM: " + contaminant + "; " +
                "Report Id: " + id + "; " +
                "Date: " + date;
    }

}
