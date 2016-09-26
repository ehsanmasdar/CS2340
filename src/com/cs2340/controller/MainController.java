package com.cs2340.controller;

import com.cs2340.app.MainApp;
import javafx.event.ActionEvent;

public class MainController {
    private MainApp mainApplication;

    public void setMainApplication(MainApp m) {
        mainApplication = m;
    }

    public void handleLogout(ActionEvent actionEvent) {
        mainApplication.showLoginScreen();
    }
}
