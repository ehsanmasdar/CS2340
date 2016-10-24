package com.cs2340.controller;

import com.cs2340.api.ReportHandler;
import com.cs2340.app.MainApp;
import com.cs2340.model.Response;
import com.cs2340.model.SourceReport;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class SourceReportController {
    private MainApp mainApplication;
    @FXML
    private TextField lat;

    @FXML
    private TextField lon;

    @FXML
    private ComboBox<String> type;

    @FXML
    private ComboBox<String> condition;

    private static final String[] types = { "Bottled", "Well", "Stream", "Lake", "Spring", "Other"};
    private static final String[] conditions = {"Waste", "Treatable-Clear", "Treatable-Muddy", "Potable"};
    @FXML
    private void initialize() {
        type.setItems(FXCollections.observableArrayList(types));
        condition.setItems(FXCollections.observableArrayList(conditions));
        //needed to make program not crash on certain machines
        type.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                type.requestFocus();
            }
        });
        //needed to make program not crash on certain machines
        condition.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                condition.requestFocus();
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

    /**
     * Handle validating source report input and committing report to database
     */
    public void handleSubmitPressed() {
        try{
            Double latd = Double.parseDouble(lat.getText());
            Double lond = Double.parseDouble(lon.getText());
            if ( latd > 90 || latd < -90 || lond > 180 || lond < -180){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Report Submit Error");
                alert.setHeaderText("Error Submitting Report");
                alert.setContentText("Lat/Long not valid");
                alert.showAndWait();
            }
            else {
                Response r = ReportHandler.postSourceReport(new SourceReport(mainApplication.getUsername(), latd, lond, type.getSelectionModel().getSelectedItem(), condition.getSelectionModel().getSelectedItem()), mainApplication.getCookie());
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

    /**
     * Cancel a source report submission
     */
    public void handleCancelPressed (){
        mainApplication.showMainScreen();
    }
}
