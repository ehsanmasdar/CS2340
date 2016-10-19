package com.cs2340.controller;

import com.cs2340.app.MainApp;

public class MainController {
    private MainApp mainApplication;

    /**
     * Inject Main App depencency
     * @param m Main Application
     */
    public void setMainApplication(MainApp m) {
        mainApplication = m;
    }

    /**
     * Clear session information on user logout
     */
    public void handleLogout() {
        mainApplication.setCookie(null);
        mainApplication.setUsername(null);
        mainApplication.showLoginScreen();
    }

    /**
     * Open profile
     */
    public void handleProfile() {
        mainApplication.showProfileScreen();
    }

    /**
     * Open Source Report creation
     */
    public void handleSourceReportSubmit(){
        mainApplication.showSourceReportScreen();
    }

    /**
     * Open Source Report list
     */
    public void handleSourceReportView (){
        mainApplication.showSourceReportViewScreen();
    }
}
