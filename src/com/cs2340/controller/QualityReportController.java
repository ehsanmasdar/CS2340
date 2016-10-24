package com.cs2340.controller;

import com.cs2340.app.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class QualityReportController {
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

    }

    @FXML
    void handleCancelPressed(ActionEvent event) {
        mainApplication.showMainScreen();
    }

}
