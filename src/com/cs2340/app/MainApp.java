package com.cs2340.app;

import com.cs2340.controller.*;
import com.cs2340.model.User;
import com.lynden.gmapsfx.javascript.object.LatLong;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class MainApp extends Application {
    private Stage mainScreen;

    public String getCookie() {
        return cookie;
    }

    private String cookie;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainScreen = primaryStage;
        showLoginScreen();
    }


    public static void main(String[] args) {
        launch(args);
    }
    public void showLoginScreen() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/login.fxml"));
            VBox v = loader.load();

            // Give the com.cs2340.controller access to the main com.cs2340.controller.app.
            LoginController controller = loader.getController();
            controller.setMainApplication(this);

            // Set the com.cs2340.controller.app.MainApp App title
            mainScreen.setTitle("Login");

            // Show the scene containing the root layout.
            Scene scene = new Scene(v);
            mainScreen.setScene(scene);
            mainScreen.show();


        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for MainScreen!!");
            e.printStackTrace();
        }
    }
    public void showRegisterScreen() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/register.fxml"));
            AnchorPane a = loader.load();

            // Give the com.cs2340.controller access to the main com.cs2340.controller.app.
            RegisterController controller = loader.getController();
            controller.setMainApplication(this);

            // Set the com.cs2340.controller.app.MainApp App title
            mainScreen.setTitle("Register");

            // Show the scene containing the root layout.
            Scene scene = new Scene(a);
            mainScreen.setScene(scene);
            mainScreen.show();


        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for MainScreen!!");
            e.printStackTrace();
        }
    }
    public void showProfileScreen() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/profile.fxml"));
            VBox a = loader.load();

            // Give the com.cs2340.controller access to the main com.cs2340.controller.app.
            ProfileController controller = loader.getController();
            controller.setMainApplication(this);

            // Set the com.cs2340.controller.app.MainApp App title
            mainScreen.setTitle("Profile");

            // Show the scene containing the root layout.
            /*Stage altStage = new Stage();
            Scene scene = new Scene(a);
            //opens in new window
            altStage.setScene(scene);
            altStage.show();*/
            Scene scene = new Scene(a);
            mainScreen.setScene(scene);
            mainScreen.show();


        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for MainScreen!!");
            e.printStackTrace();
        }
    }
    public void showMainScreen() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/main.fxml"));
            VBox a = loader.load();

            // Give the com.cs2340.controller access to the main com.cs2340.controller.app.
            MainController controller = loader.getController();
            controller.setMainApplication(this);


            // Set the com.cs2340.controller.app.MainApp App title
            mainScreen.setTitle("Main");

            // Show the scene containing the root layout.
            Scene scene = new Scene(a);
            mainScreen.setScene(scene);
            mainScreen.setResizable(false);
            mainScreen.show();
            controller.setMap();


        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for MainScreen!!");
            e.printStackTrace();
        }
    }

    public void showSourceReportScreen() {
        showSourceReportScreen(new LatLong(0,0));
    }

    public void showSourceReportScreen(LatLong location) {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/sourceReport.fxml"));
            VBox a = loader.load();

            // Give the com.cs2340.controller access to the main com.cs2340.controller.app.
            SourceReportController controller = loader.getController();
            controller.setSourceLatLon(location);
            controller.setMainApplication(this);

            // Set the com.cs2340.controller.app.MainApp App title
            mainScreen.setTitle("Enter a new Source Report");

            // Show the scene containing the root layout.
            /*Stage altStage = new Stage();
            Scene scene = new Scene(a);
            //opens in new window
            altStage.setScene(scene);
            altStage.show();*/
            Scene scene = new Scene(a);
            mainScreen.setScene(scene);
            mainScreen.show();


        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for MainScreen!!");
            e.printStackTrace();
        }
    }
    public void showSourceReportViewScreen() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/sourceReportView.fxml"));
            VBox a = loader.load();

            // Give the com.cs2340.controller access to the main com.cs2340.controller.app.
            SourceReportViewController controller = loader.getController();
            controller.setMainApplication(this);

            // Set the com.cs2340.controller.app.MainApp App title
            mainScreen.setTitle("Source Reports");

            // Show the scene containing the root layout.
            Stage altStage = new Stage();
            Scene scene = new Scene(a);
            //opens in new window
            altStage.setScene(scene);
            altStage.show();


        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for MainScreen!!");
            e.printStackTrace();
        }
    }

    public void showQualityReportScreen(){
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/qualityReport.fxml"));
            VBox a = loader.load();

            // Give the com.cs2340.controller access to the main com.cs2340.controller.app.
            PurityReportController controller = loader.getController();
            controller.setMainApplication(this);

            // Set the com.cs2340.controller.app.MainApp App title
            mainScreen.setTitle("Enter a new Source Report");

            // Show the scene containing the root layout.
            /*Stage altStage = new Stage();
            Scene scene = new Scene(a);
            //opens in new window
            altStage.setScene(scene);
            altStage.show();*/
            Scene scene = new Scene(a);
            mainScreen.setScene(scene);
            mainScreen.show();


        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for MainScreen!!");
            e.printStackTrace();
        }
    }
    public void showQualityReportViewScreen(){
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/qualityReportView.fxml"));
            VBox a = loader.load();

            // Give the com.cs2340.controller access to the main com.cs2340.controller.app.
            PurityReportViewController controller = loader.getController();
            controller.setMainApplication(this);

            // Set the com.cs2340.controller.app.MainApp App title
            mainScreen.setTitle("Source Reports");

            // Show the scene containing the root layout.
            Stage altStage = new Stage();
            Scene scene = new Scene(a);
            //opens in new window
            altStage.setScene(scene);
            altStage.show();


        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for MainScreen!!");
            e.printStackTrace();
        }
    }
    public void showHistorySetup(){
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/historySetup.fxml"));
            VBox a = loader.load();

            // Give the com.cs2340.controller access to the main com.cs2340.controller.app.
            HistorySetupController controller = loader.getController();
            controller.setMainApplication(this);

            // Set the com.cs2340.controller.app.MainApp App title
            mainScreen.setTitle("History Setup");

            Scene scene = new Scene(a);
            mainScreen.setScene(scene);
            mainScreen.show();


        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for MainScreen!!");
            e.printStackTrace();
        }
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}