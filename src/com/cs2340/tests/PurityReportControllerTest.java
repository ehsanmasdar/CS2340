package com.cs2340.tests;

import org.junit.Test;
import com.cs2340.controller.PurityReportController;
import static org.junit.Assert.*;

/**
 * Created by Josiah Smith on 11/14/2016.
 */
public class PurityReportControllerTest {
    @Test
    public void testValLatLon() {
        //Valid Inputs
        String actual = PurityReportController.valLatLon(0.0, 0.0);
        String expected = null;
        assertEquals(
                "Valid Inputs returned invalid result",
                actual,
                expected);

        //Lat too large
        actual = PurityReportController.valLatLon(91.0, 0.0);
        expected = "latitude is too large";
        assertEquals(
                "Did not return correct result when Lat is too large",
                actual,
                expected);

        //Lat too small
        actual = PurityReportController.valLatLon(-91.0, 0.0);
        expected = "latitude is too small";
        assertEquals(
                "Did not return correct result when Lat is too small",
                actual,
                expected);

        //Lon too large
        actual = PurityReportController.valLatLon(0.0, 181.0);
        expected = "longitude is too large";
        assertEquals(
                "Did not return correct result when Lon is too large",
                actual,
                expected);

        //Lon too small
        actual = PurityReportController.valLatLon(0.0, -181.0);
        expected = "longitude is too small";
        assertEquals(
                "Did not return correct result when Lon is too small",
                actual,
                expected);
    }
}