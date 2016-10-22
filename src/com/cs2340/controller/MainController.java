package com.cs2340.controller;

import com.cs2340.app.MainApp;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

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
    }
}
