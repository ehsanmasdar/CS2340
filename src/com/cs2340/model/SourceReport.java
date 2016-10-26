package com.cs2340.model;


import com.lynden.gmapsfx.javascript.object.*;

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

    /**
     * Generate Map Marker
     * @return Map Marker
     */
    public Marker getMarker(){
        MarkerOptions mo  = new MarkerOptions().position(new LatLong(lat,lon));
        return new Marker(mo);
    }

    /**
     * Generate Information Window
     * @return Information Window
     */
    public InfoWindow getInfoWindow(){
        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("Report for " + lat + "," + lon + "<br>"
                + "Condition: " + condition + "<br>"
                + "Type: " + type + "<br>");
        return  new InfoWindow(infoWindowOptions);
    }
    @Override
    public String toString(){
         return "Submitted by: " + name + "; " +
                "Location: (" + lat + ", " + lon + "); " +
                "Type: " + type + "; " +
                "Condition: " + condition + "; " +
                "Report Id: " + id + "; " +
                "Date: " + date;
    }
}
