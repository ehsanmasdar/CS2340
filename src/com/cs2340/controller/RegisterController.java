package com.cs2340.controller;

import com.cs2340.api.UserHandler;
import com.cs2340.app.MainApp;
import com.cs2340.model.AccessLevel;
import com.cs2340.model.Response;
import com.cs2340.model.User;
import javafx.collections.FXCollections;
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
        //needed to make program not crash on certain machines
        levelField.setOnMousePressed(event -> levelField.requestFocus());
    }

    /**
     * Inject Main App dependency
     * @param m Main Application
     */
    public void setMainApplication(MainApp m){
        mainApplication = m;
    }

    /**
     * Cancel user registration
     */
    public void handleCancel() {
        mainApplication.showLoginScreen();
    }

    /**
     * Validate password length and verify the same password was entered twice
     * @param password Password Entered
     * @param passwordConfirmation Password Confirmation Entered
     * @return Error message, or null for sucessful validation
     */
    public static String validatePassword(String password, String passwordConfirmation){
        if (password.equals(passwordConfirmation)){
            if (password.length() > 3){
                return null;
            }
            else {
                return "Password must be 4 characters or longer";
            }
        }
        else{
            return "Passwords entered must match";
        }
    }
    /**
     * Validate and process user registration
     */
    public void handleRegisterSubmit() {
        String validation = validatePassword(password.getText(), passwordConfirm.getText());
        if (validation == null) {
            User u = new User(username.getText(), password.getText(), levelField.getSelectionModel().getSelectedItem());
            Response r = UserHandler.postRegister(u);
            if (r.getSuccess() == 1){
                mainApplication.showLoginScreen();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Register Error");
                alert.setHeaderText("Error Registering User");
                alert.setContentText(r.getMessage());

                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Register Error");
            alert.setHeaderText("Error Registering User");
            alert.setContentText(validation);
            alert.showAndWait();
        }
    }
}
