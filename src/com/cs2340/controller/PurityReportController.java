package com.cs2340.controller;

import com.cs2340.api.ReportHandler;
import com.cs2340.app.MainApp;
import com.cs2340.model.PurityReport;
import com.cs2340.model.Response;
import com.cs2340.model.SourceReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PurityReportController {
    private MainApp mainApplication;
    @FXML
    private TextField qualityLat;

    @FXML
    private TextField qualityLon;

    @FXML
    private ComboBox<String> qualityCondition;
    private static final String[] conditions = {"Waste", "Treatable-Clear", "Treatable-Muddy", "Potable"};

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
        qualityCondition.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                qualityCondition.requestFocus();
            }
        });
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
            Double latd = Double.parseDouble(qualityLat.getText());
            Double lond = Double.parseDouble(qualityLon.getText());
            if ( latd > 90 || latd < -90 || lond > 180 || lond < -180){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Report Submit Error");
                alert.setHeaderText("Error Submitting Report");
                alert.setContentText("Lat/Long not valid");
                alert.showAndWait();
            }
            else {
                Response r = ReportHandler.postPurityReport(new PurityReport(mainApplication.getUser().username, latd, lond,
                        qualityCondition.getSelectionModel().getSelectedItem(), Integer.parseInt(qualityVirus.getText())
                        , Integer.parseInt(qualityContaminant.getText())), mainApplication.getCookie());
                if (r.success == 1) {
                    mainApplication.showMainScreen();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Report Submit Error");
                    alert.setHeaderText("Error Submitting Report");
                    alert.setContentText(r.message);

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

    @FXML
    void handleCancelPressed(ActionEvent event) {
        mainApplication.showMainScreen();
    }

}
