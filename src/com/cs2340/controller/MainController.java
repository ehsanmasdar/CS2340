package com.cs2340.controller;

import com.cs2340.api.ReportHandler;
import com.cs2340.app.MainApp;
import com.cs2340.model.SourceReport;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import netscape.javascript.JSObject;


public class MainController implements MapComponentInitializedListener {
    @FXML
    private BorderPane mainViewBorderPane;
    @FXML
    private Button qualityReportSubmit;
    @FXML
    private Button qualityReportView;
    @FXML
    private Button historySetup;

    private GoogleMapView mapView;
    private GoogleMap map;

    private MainApp mainApplication;

    /**
     * Inject Main App dependency
     * @param m Main Application
     */
    public void setMainApplication(MainApp m) {
        mainApplication = m;
        switch (mainApplication.getUser().getLevel()){
            case WORKER:
                qualityReportView.setVisible(false);
                historySetup.setVisible(false);
                break;
            case USER:
                qualityReportView.setVisible(false);
                qualityReportSubmit.setVisible(false);
                historySetup.setVisible(false);
                break;
        }
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
        mainApplication.setUser(null);
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

    /**
     * Open History Report setup
     */
    public void handleHistorySetup (){
        mainApplication.showHistorySetup();
    }

    public void handleQualityReportSubmit(){
        //don't show window if incorrect AccessLevel
        mainApplication.showQualityReportScreen();
    }

    public void handleQualityReportView(){
        //don't show window if incorrect AccessLevel
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
                .zoom(2)
                .overviewMapControl(false)
                .panControl(true)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(true)
                .mapType(MapTypeIdEnum.TERRAIN);
        mapView.setDisableDoubleClick(true);
        map = mapView.createMap(options);
        for (SourceReport report : ReportHandler.getSourceReports(mainApplication.getCookie()).getData()){
            Marker m = report.getMarker();
            map.addUIEventHandler(m, UIEventType.click, jsObject -> report.getInfoWindow().open(map, m));
            map.addMarker(m);
        }
        map.addUIEventHandler(UIEventType.click, (JSObject e) -> {
            LatLong ll = new LatLong((JSObject) e.getMember("latLng"));
            System.out.println("LatLong: lat: " + ll.getLatitude() + " lng: "+ ll.getLongitude());
            mainApplication.showSourceReportScreen(ll);
        });
    }
}
