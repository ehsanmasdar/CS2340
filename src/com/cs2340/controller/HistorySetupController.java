package com.cs2340.controller;

import com.cs2340.api.ReportHandler;
import com.cs2340.app.MainApp;
import com.cs2340.model.PurityReport;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * History Setup Controller
 */
public class HistorySetupController {
    private MainApp mainApplication;
    @FXML
    private TextField histSetupMinLon;
    @FXML
    private TextField histSetupMaxLon;
    @FXML
    private TextField histSetupMinLat;
    @FXML
    private TextField histSetupMaxLat;
    @FXML
    private TextField year;
    @FXML
    private ComboBox<String> type;
    private static final String[] types = {"Virus", "Contaminant"};


    /**
     * Inject Main App dependency
     *
     * @param m Main Application
     */
    public void setMainApplication(MainApp m) {
        mainApplication = m;
        type.setItems(FXCollections.observableArrayList(types));
        type.getSelectionModel().selectFirst();
    }

    /**
     * Validate and generate historical report
     */
    public void handleSubmitPressed() {
        if (histSetupMinLat.getText().length() > 0 && histSetupMaxLat.getText().length() > 0 &&
                histSetupMinLon.getText().length() > 0 && histSetupMaxLon.getText().length() > 0
                && year.getText().length() > 0) {
            try {
                double minLat = Double.parseDouble(histSetupMinLat.getText());
                double maxLat = Double.parseDouble(histSetupMaxLat.getText());
                double minLon = Double.parseDouble(histSetupMinLon.getText());
                double maxLon = Double.parseDouble(histSetupMaxLon.getText());
                int yearNum = Integer.parseInt(year.getText());
                if ( minLat > 90 || minLat < -90 || maxLat > 90 || maxLat < -90 || minLon > 180 || minLon < -180 || maxLon > 180 ||
                        maxLon < -180) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Report Generation Error");
                    alert.setHeaderText("Error Creating Report");
                    alert.setContentText("Lat/Long not valid");
                    alert.showAndWait();
                }
                else {
                    //DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
                    //defining the axes
                    final NumberAxis xAxis = new NumberAxis();
                    final NumberAxis yAxis = new NumberAxis();
                    xAxis.setLabel("Number of Month");
                    yAxis.setLabel(type.getSelectionModel().getSelectedItem() + " PPM");
                    //creating the chart
                    final ScatterChart<Number,Number> scatterChart =
                            new ScatterChart<Number,Number>(xAxis,yAxis);
                    XYChart.Series series = new XYChart.Series();
                    series.setName("Data");

                    // Loop through reports
                    for (PurityReport report : ReportHandler.getPurityReports(mainApplication.getCookie()).data){
                        LocalDate dateTime = LocalDate.parse(report.date, DateTimeFormatter.ISO_DATE_TIME);
                        if (report.lat  > minLat && report.lat < maxLat  && report.lon > minLon && report.lon < maxLon && dateTime.getYear() == yearNum){
                            if (type.getSelectionModel().getSelectedItem().equals("Virus")){
                                System.out.println(report);
                                series.getData().add(new XYChart.Data<>(dateTime.getMonthValue(),report.virus));
                            }
                            else {
                                series.getData().add(new XYChart.Data<>(dateTime.getMonthValue(),report.contaminant));
                            }
                        }
                    }
                    Stage stage = new Stage();
                    Scene scene  = new Scene(scatterChart,800,600);
                    scatterChart.getData().add(series);
                    stage.setScene(scene);
                    stage.show();
                    mainApplication.showMainScreen();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Report Generation Error");
                alert.setHeaderText("Error Creating Report");
                alert.setContentText("Lat/Long and Year must be numbers");

                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Report Generation Error");
            alert.setHeaderText("Error Creating Report");
            alert.setContentText("Please fill out all fields");

            alert.showAndWait();
        }
    }

    /**
     * Cancel generating report
     */
    public void handleCancelPressed() {
        mainApplication.showMainScreen();
    }
}
