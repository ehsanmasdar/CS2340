package com.cs2340.controller;

import com.cs2340.api.UserHandler;
import com.cs2340.app.MainApp;
import com.cs2340.model.AccessLevel;
import com.cs2340.model.Profile;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;


public class ProfileController {
    private MainApp mainApplication;
    @FXML
    private TextField profileTitleText;
    @FXML
    private TextField profileEmailText;
    @FXML
    private TextField profileAddressText;

    public void setMainApplication(MainApp m) {
        mainApplication = m;
        Profile p = (Profile) UserHandler.getProfile(mainApplication.getCookie()).data;
        profileTitleText.setText(p.name);
        profileAddressText.setText(p.address);
        profileEmailText.setText(p.email);
    }

    public void handleProfileSubmit(ActionEvent actionEvent) {
        UserHandler.postProfile(new Profile(profileTitleText.getText(),profileAddressText.getText(),profileEmailText.getText()),mainApplication.getCookie());
        mainApplication.showMainScreen();
    }

}
