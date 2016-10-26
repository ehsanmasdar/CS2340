package com.cs2340.controller;

import com.cs2340.api.ReportHandler;
import com.cs2340.app.MainApp;
import com.cs2340.model.PurityReport;
import com.cs2340.model.Response;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

import java.util.Arrays;

public class PurityReportViewController {

    @FXML
    private ListView<PurityReport> purityReportListView;

    /**
     * Inject Main App dependency
     * @param m Main Application
     */
    public void setMainApplication(MainApp m) {
        Response<PurityReport[]> response = ReportHandler.getPurityReports(m.getCookie());
        if (response.success == 1){
            ObservableList<PurityReport> observableList = FXCollections.observableList(Arrays.asList(response.data));
            purityReportListView.setItems(observableList);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Permission Denied");
            alert.setContentText("Cannot view purity reports due to user level.");
            alert.showAndWait();
        }
    }
}
