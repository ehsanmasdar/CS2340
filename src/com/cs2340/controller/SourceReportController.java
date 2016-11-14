package com.cs2340.controller;

import com.cs2340.api.ReportHandler;
import com.cs2340.app.MainApp;
import com.cs2340.model.Response;
import com.cs2340.model.SourceReport;
import com.lynden.gmapsfx.javascript.object.LatLong;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SourceReportController {
    private MainApp mainApplication;
    @FXML
    private TextField sourceLat;

    @FXML
    private TextField sourceLon;

    @FXML
    private ComboBox<String> sourceType;

    @FXML
    private ComboBox<String> qualityCondition;

    private static final String[] types = { "Bottled", "Well", "Stream", "Lake", "Spring", "Other"};
    private static final String[] conditions = {"Waste", "Treatable-Clear", "Treatable-Muddy", "Potable"};
    @FXML
    private void initialize() {
        sourceType.setItems(FXCollections.observableArrayList(types));
        qualityCondition.setItems(FXCollections.observableArrayList(conditions));
        //needed to make program not crash on certain machines
        sourceType.setOnMousePressed(event -> sourceType.requestFocus());
        //needed to make program not crash on certain machines
        qualityCondition.setOnMousePressed(event -> qualityCondition.requestFocus());
        if(latVal!=0&&lonVal!=0){
            sourceLat.setText(latVal + "");
            sourceLon.setText(lonVal + "");
        }
    }

    /**
     * Inject Main App dependency
     * @param m Main Application
     */
    public void setMainApplication(MainApp m){
        mainApplication = m;
    }

    private double latVal = 0;
    private double lonVal = 0;
    /**
     * Sets lat and lon values for when report is added by clicking the map
     * @param ll object containing lat and lon values
     */
    public void setSourceLatLon(LatLong ll){
        latVal = ll.getLatitude();
        lonVal = ll.getLongitude();
        sourceLat.setText(latVal + "");
        sourceLon.setText(lonVal + "");
    }

    /**
     * Handle validating source report input and committing report to database
     */
    public void handleSubmitPressed() {
        try{
            Double latD = Double.parseDouble(sourceLat.getText());
            Double lonD = Double.parseDouble(sourceLon.getText());
            if ( latD > 90 || latD < -90 || lonD > 180 || lonD < -180){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Report Submit Error");
                alert.setHeaderText("Error Submitting Report");
                alert.setContentText("Lat/Long not valid");
                alert.showAndWait();
            }
            else {
                Response r = ReportHandler.postSourceReport(new SourceReport(mainApplication.getUser().getUsername(), latD, lonD, sourceType.getSelectionModel().getSelectedItem(), qualityCondition.getSelectionModel().getSelectedItem()), mainApplication.getCookie());
                if (r.getSuccess() == 1) {
                    mainApplication.showMainScreen();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Report Submit Error");
                    alert.setHeaderText("Error Submitting Report");
                    alert.setContentText(r.getMessage());

                    alert.showAndWait();
                }
            }
        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Report Submit Error");
            alert.setHeaderText("Error Submitting Report");
            alert.setContentText("Lat/Long must be numbers");

            alert.showAndWait();
        }

    }

    /**
     * Cancel a source report submission
     */
    public void handleCancelPressed (){
        mainApplication.showMainScreen();
    }
}
