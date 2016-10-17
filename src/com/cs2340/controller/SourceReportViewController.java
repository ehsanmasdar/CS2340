package com.cs2340.controller;

import com.cs2340.api.ReportHandler;
import com.cs2340.app.MainApp;
import com.cs2340.model.SourceReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class SourceReportViewController {
    private MainApp mainApplication;

    @FXML
    private ListView<SourceReport> sourceReportViewListView;

    public void setMainApplication(MainApp m) {
        mainApplication = m;
        SourceReport[] sourceReports = ReportHandler.getSourceReport(mainApplication.getCookie()).data;
        ArrayList<SourceReport> ReportList = new ArrayList<SourceReport>();
        for(SourceReport r: sourceReports) {
            ReportList.add(r);
        }
        ObservableList<SourceReport> observableList = FXCollections.observableList(ReportList);
        sourceReportViewListView.setItems(observableList);
        //System.out.println(sourceReports[0]);
    }
}