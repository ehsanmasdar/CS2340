package com.cs2340.controller;

import com.cs2340.api.UserHandler;
import com.cs2340.app.MainApp;
import com.cs2340.model.AccessLevel;
import com.cs2340.model.Response;
import com.cs2340.model.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class RegisterController {
    private MainApp mainApplication;
    @FXML
    private ComboBox<AccessLevel> levelField;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField passwordConfirm;

    @FXML
    private void initialize() {
        levelField.getItems().clear();
        levelField.setItems(FXCollections.observableArrayList(AccessLevel.values()));
        levelField.getSelectionModel().selectFirst();
    }

    public void setMainApplication(MainApp m){
        mainApplication = m;
    }

    public void handleCancel(ActionEvent actionEvent) {
        mainApplication.showLoginScreen();
    }

    public void handleRegisterSubmit(ActionEvent actionEvent) {
        if (password.getText().equals(passwordConfirm.getText())) {
            User u = new User(username.getText(), password.getText(), levelField.getSelectionModel().getSelectedItem());
            Response r = UserHandler.postRegister(u);
            if (r.sucess == 1){
                mainApplication.showLoginScreen();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Register Error");
                alert.setHeaderText("Error Registering User");
                alert.setContentText(r.message);

                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Register Error");
            alert.setHeaderText("Error Registering User");
            alert.setContentText("Passwords do not match");
            alert.showAndWait();
        }
    }
}
