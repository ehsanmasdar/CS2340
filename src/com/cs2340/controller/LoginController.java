package com.cs2340.controller;

import com.cs2340.api.UserHandler;
import com.cs2340.app.MainApp;
import com.cs2340.model.Response;
import com.cs2340.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    private MainApp mainApplication;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button submit;
    @FXML
    private Button register;

    /**
     * Inject Main App depencency
     * @param m Main Application
     */
    public void setMainApplication(MainApp m){
        mainApplication = m;
    }

    /**
     * Attempt login with provided credentials
     */
    public void handleSubmitPressed() {
        Response r = UserHandler.postLogin(new User(username.getText(),password.getText()));
        if (r.success == 1){
            mainApplication.setCookie((String) r.data);
            mainApplication.setUsername(username.getText());
            mainApplication.showMainScreen();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("Error Logging in");
            alert.setContentText(r.message);

            alert.showAndWait();
        }
    }

    /**
     * Display registration screen
     */
    public void handleRegisterPressed() {
        mainApplication.showRegisterScreen();
    }
}
