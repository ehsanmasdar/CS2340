package com.cs2340.controller;

import com.cs2340.api.ReportHandler;
import com.cs2340.app.MainApp;
import com.cs2340.model.SourceReport;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.StateEventHandler;
import com.lynden.gmapsfx.javascript.event.UIEventHandler;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import netscape.javascript.JSObject;


public class MainController implements MapComponentInitializedListener {
    @FXML
    private BorderPane mainViewBorderPane;

    private GoogleMapView mapView;
    private GoogleMap map;

    private MainApp mainApplication;

    /**
     * Inject Main App depencency
     * @param m Main Application
     */
    public void setMainApplication(MainApp m) {
        mainApplication = m;;
    }

    /**
     * Set the Map object
     */
    public void setMap(){
        mapView = new GoogleMapView();
        mapView.addMapInializedListener(this);
        mainViewBorderPane.setCenter(mapView);
    }

    /**
     * Clear session information on user logout
     */
    public void handleLogout() {
        mainApplication.setCookie(null);
        mainApplication.setUsername(null);
        mainApplication.showLoginScreen();
    }

    /**
     * Open profile
     */
    public void handleProfile() {
        mainApplication.showProfileScreen();
    }

    /**
     * Open Source Report creation
     */
    public void handleSourceReportSubmit(){
        mainApplication.showSourceReportScreen();
    }

    /**
     * Open Source Report list
     */
    public void handleSourceReportView (){
        mainApplication.showSourceReportViewScreen();
    }

    public void handleQualityReportSubmit(){
        //dont show window if incorrect AccessLevel
        mainApplication.showQualityReportScreen();
    }

    public void handleQualityReportView(){
        //dont show window if incorrect AccessLevel
        mainApplication.showQualityReportViewScreen();
    }

    /**
     * Populate map with markers
     */
    @Override
    public void mapInitialized() {
        mapView.addMapInializedListener(this);

        //Set the initial properties of the map

        MapOptions options = new MapOptions();

        //set up the center location for the map
        LatLong center = new LatLong(33.7490, -84.3880);

        options.center(center)
                .zoom(9)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .mapType(MapTypeIdEnum.TERRAIN);

        map = mapView.createMap(options);
        for (SourceReport report : ReportHandler.getSourceReport(mainApplication.getCookie()).data){
            InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
            infoWindowOptions.content("Report for " + report.lat + "," + report.lon + "<br>"
                    + "Condition: " + report.condition + "<br>"
                    + "Type: " + report.type + "<br>");
            InfoWindow infoWindow = new InfoWindow(infoWindowOptions);
            MarkerOptions mo  = new MarkerOptions().position(new LatLong(report.lat,report.lon));
            Marker marker = new Marker(mo);
            map.addUIEventHandler(marker, UIEventType.click, new UIEventHandler() {
                @Override
                public void handle(JSObject jsObject) {
                    infoWindow.open(map,marker);
                }
            });
            map.addMarker(marker);
        }
    }
}
