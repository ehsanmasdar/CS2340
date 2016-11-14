package com.cs2340.controller;

import com.cs2340.api.ReportHandler;
import com.cs2340.app.MainApp;
import com.cs2340.model.SourceReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.Arrays;

public class SourceReportViewController {

    @FXML
    private ListView<SourceReport> sourceReportViewListView;
    /**
     * Inject Main App dependency
     * @param m Main Application
     */
    public void setMainApplication(MainApp m) {
        SourceReport[] sourceReports = ReportHandler.getSourceReports(m.getCookie()).getData();
        ObservableList<SourceReport> observableList = FXCollections.observableList(Arrays.asList(sourceReports));
        sourceReportViewListView.setItems(observableList);
        //System.out.println(sourceReports[0]);
    }
}