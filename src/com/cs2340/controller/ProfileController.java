package com.cs2340.controller;

import com.cs2340.api.UserHandler;
import com.cs2340.app.MainApp;
import com.cs2340.model.Profile;
import com.cs2340.model.Response;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class ProfileController {
    private MainApp mainApplication;
    @FXML
    private TextField profileTitleText;
    @FXML
    private TextField profileEmailText;
    @FXML
    private TextField profileAddressText;

    /**
     * Inject Main App dependency
     * @param m Main Application
     */
    public void setMainApplication(MainApp m) {
        mainApplication = m;
        Profile p = UserHandler.getProfile(mainApplication.getCookie()).data;
        profileTitleText.setText(p.name);
        profileAddressText.setText(p.address);
        profileEmailText.setText(p.email);
    }

    /**
     * Submit a profile change
     */
    public void handleProfileSubmit() {
        Response<String> resp = UserHandler.postProfile(new Profile(profileTitleText.getText(),profileAddressText.getText(),profileEmailText.getText()),mainApplication.getCookie());
        if (resp.success == 1){
            mainApplication.showMainScreen();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Profile Update Error");
            alert.setHeaderText("Error updating message");
            alert.setContentText(resp.message);

            alert.showAndWait();
        }
    }
}
