package com.cs2340.controller;

import com.cs2340.api.ReportHandler;
import com.cs2340.app.MainApp;
import com.cs2340.model.SourceReport;

public class SourceReportViewController {

    private MainApp mainApplication;

    public void setMainApplication(MainApp m) {
        mainApplication = m;
        SourceReport[] sourceReport = ReportHandler.getSourceReport(mainApplication.getCookie()).data;
    }
}