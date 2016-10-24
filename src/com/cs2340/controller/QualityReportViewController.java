package com.cs2340.controller;

import com.cs2340.api.ReportHandler;
import com.cs2340.app.MainApp;
import com.cs2340.model.SourceReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.Arrays;

public class QualityReportViewController {
    public void setMainApplication(MainApp mainApp) {
    //TODO: make new api function and model so uncommenting this actually works
    /*
    @FXML
    private ListView<QualityReport> qualityReportViewListView;
    *//**
     * Inject Main App dependency
     * @param m Main Application
     *//*

        QualityReport[] qualityReports = ReportHandler.getQualityReport(m.getCookie()).data;
        ObservableList<QualityReport> observableList = FXCollections.observableList(Arrays.asList(qualityReports));
        qualityReportViewListView.setItems(observableList);
        //System.out.println(sourceReports[0]);*/
    }
}
