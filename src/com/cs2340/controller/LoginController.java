package com.cs2340.controller;

import com.cs2340.api.UserHandler;
import com.cs2340.app.MainApp;
import com.cs2340.model.Response;
import com.cs2340.model.User;
import javafx.event.ActionEvent;
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

    @FXML
    private void initialize() {
    }
    public void setMainApplication(MainApp m){
        mainApplication = m;
    }

    public void handleSubmitPressed(ActionEvent actionEvent) {
        Response r = UserHandler.postLogin(new User(username.getText(),password.getText()));
        if (r.sucess == 1){
            mainApplication.setCookie((String) r.data);
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

    public void handleRegisterPressed(ActionEvent actionEvent) {
        mainApplication.showRegisterScreen();
    }
}
