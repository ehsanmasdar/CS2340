package com.cs2340.controller;

import com.cs2340.api.ReportHandler;
import com.cs2340.app.MainApp;
import com.cs2340.model.PurityReport;
import com.cs2340.model.Response;
import javafx.collections.FXCollections;
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
            Double latD = Double.parseDouble(qualityLat.getText());
            Double lonD = Double.parseDouble(qualityLon.getText());
            if ( latD > 90 || latD < -90 || lonD > 180 || lonD < -180){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Report Submit Error");
                alert.setHeaderText("Error Submitting Report");
                alert.setContentText("Lat/Long not valid");
                alert.showAndWait();
            }
            else {
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

    @FXML
    void handleCancelPressed(ActionEvent event) {
        mainApplication.showMainScreen();
    }

}
