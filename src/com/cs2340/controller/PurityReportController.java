package com.cs2340.controller;

import com.cs2340.api.ReportHandler;
import com.cs2340.app.MainApp;
import com.cs2340.model.PurityReport;
import com.cs2340.model.Response;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class PurityReportController {
    private MainApp mainApplication;
    @FXML
    private TextField qualityLat;

    @FXML
    private TextField qualityLon;

    @FXML
    private ComboBox<String> qualityCondition;
    private static final String[] conditions = {"Safe", "Treatable", "Unsafe"};

    @FXML
    private TextField qualityVirus;

    @FXML
    private TextField qualityContaminant;

    @FXML
    private Button qualitySubmit;

    @FXML
    private Button qualityCancel;

    @FXML
    public void initialize(){
        qualityCondition.setItems(FXCollections.observableArrayList(conditions));
        //needed so program doesn't crash on certain machines
        qualityCondition.setOnMousePressed(event -> qualityCondition.requestFocus());
    }

    /**
     * Inject Main App dependency
     * @param m Main Application
     */
    public void setMainApplication(MainApp m){
        mainApplication = m;
    }

    @FXML
    void handleSubmitPressed(ActionEvent event) {
        try{
            Double latD = Double.parseDouble(qualityLat.getText());
            Double lonD = Double.parseDouble(qualityLon.getText());
            String valLatLonMessage = valLatLon(latD, lonD);
            if (valLatLonMessage != null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Report Submit Error");
                alert.setHeaderText("Error Submitting Report");
                alert.setContentText(valLatLonMessage);
                alert.showAndWait();
            } else {
                Response r = ReportHandler.postPurityReport(new PurityReport(mainApplication.getUser().getUsername(), latD, lonD,
                        qualityCondition.getSelectionModel().getSelectedItem(), Integer.parseInt(qualityVirus.getText())
                        , Integer.parseInt(qualityContaminant.getText())), mainApplication.getCookie());
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
     * Validates lat and lon inputs
     * @param lat latitude value
     * @param lon longitude value
     * @return null if values are valid, error message if invalid
     */
     static String valLatLon(Double lat, Double lon) {
        String returnStr = "";
        if (lat > 90) {
            returnStr = returnStr + "latitude is too large";
        } else if (lat < -90) {
            returnStr = returnStr + "latitude is too small";
        }
        if (lon > 180) {
            if (returnStr.length() > 0) {
                returnStr = returnStr + " and ";
            }
            returnStr = returnStr + "longitude is too large";
        } else if (lon < -180) {
            if (returnStr.length() > 0) {
                returnStr = returnStr + " and ";
            }
            returnStr = returnStr + "longitude is too small";
        }
        if (returnStr.length() > 0) {
            return returnStr;
        } else {
            return null;
        }

    }

    @FXML
    void handleCancelPressed(ActionEvent event) {
        mainApplication.showMainScreen();
    }

}
