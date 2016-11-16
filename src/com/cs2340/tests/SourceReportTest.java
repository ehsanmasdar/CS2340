package com.cs2340.tests;

import com.cs2340.api.ReportHandler;
import com.cs2340.api.UserHandler;
import com.cs2340.model.Response;
import com.cs2340.model.SourceReport;
import com.cs2340.model.User;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import org.junit.FixMethodOrder;

import javax.xml.transform.Source;

import static org.junit.Assert.*;


/**
 * Created by William on 11/10/2016.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SourceReportTest {
    private String cookie;
    private static String username = "test";
    private static String password = "test";


    public void getCookie() {
        cookie = UserHandler.postLogin(new User(username, password)).getData();

    }

    @Test
    public void test1FakeCookie(){
        cookie = null;
        cookie = UserHandler.postLogin(new User("blocked", "pass")).getData();
        SourceReport report = new SourceReport("null cookie", -90, -90, "Bottled", "Potable");
        Response response = ReportHandler.postSourceReport(report, cookie);
        assertEquals(response.getSuccess(), 0);
        assertEquals(response.getMessage(), "Access Denied based on user level");
        assertEquals(response.getData(), null);
    }

    @Test
    public void test2PostEqualsSubmit(){
        getCookie();
        SourceReport report = new SourceReport(username, 23, 40, "Bottled", "Potable");
        Response response = ReportHandler.postSourceReport(report, cookie);
        assertEquals(response.getSuccess(), 1);
        assertEquals(response.getMessage(), "");
        assertEquals(response.getData(), null);
        /*SourceReport[] reports = ReportHandler.getSourceReports(cookie).getData();
        SourceReport postedReport = new SourceReport(reports[reports.length-1].getName(),
                reports[reports.length-1].getLat(),reports[reports.length-1].getLon(),
                reports[reports.length-1].getType(),reports[reports.length-1].getCondition());
        assertEquals("Report posted should equal report submitted", report.toString(),postedReport.toString());*/
    }

    @Test
    public void test3NoInternet(){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("INTERNET MUST BE OFF FOR THIS TEST TO WORK\n" +
                           "---------PLEASE TURN INTERNET OFF---------");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        try {
            Thread.sleep(4000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        SourceReport report = new SourceReport("no internet", -40, -40, "Bottled", "Potable");
        Response response = ReportHandler.postSourceReport(report, cookie);
        assertEquals(response.getSuccess(), 0);
        assertEquals(response.getMessage(), "");
        assertEquals(response.getData(), null);
    }

}
