package com.cs2340.api;

import com.cs2340.model.Response;
import com.cs2340.model.SourceReport;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReportHandler {
    public static Response postSourceReport(SourceReport sourceReport, String cookie) {
        try {
            JSONObject jsonResponse = Unirest.post(Constants.URL_BASE + "/api/report/source").header("Cookie", cookie)
                    .field("lat", sourceReport.lat)
                    .field("long", sourceReport.lon)
                    .field("type", sourceReport.type)
                    .field("condition", sourceReport.condition)
                    .asJson().getBody().getObject();
            if (jsonResponse.has("message")) {
                return new Response(jsonResponse.getInt("success"), jsonResponse.getString("message"), null);
            } else {
                return new Response(jsonResponse.getInt("success"), "", null);
            }
        } catch (UnirestException e) {
            e.printStackTrace();
            return new Response(0, "", null);
        }
    }
    public static Response<SourceReport[]> getSourceReport(String cookie) {
        try {
            JSONArray jsonResponse = Unirest.get(Constants.URL_BASE + "/api/report/source").header("Cookie", cookie)
                    .asJson().getBody().getArray();
            System.out.println(jsonResponse.toString());
            SourceReport[] sourceReports = new SourceReport[jsonResponse.length()];
            for (int i = 0; i < jsonResponse.length(); i++){
                JSONObject obj = jsonResponse.getJSONObject(i);
                sourceReports[i] = new SourceReport(obj.getString("name"), obj.getBigDecimal("lat").doubleValue(),
                        obj.getBigDecimal("long").doubleValue(), obj.getString("type"), obj.getString("condition"), obj.getString("_id"), obj.getString("date"));
            }
            return new Response<>(1, "", sourceReports);
        } catch (UnirestException e) {
            e.printStackTrace();
            return new Response<>(0, "", null);
        }
        catch (Exception e){
            e.printStackTrace();
            return new Response<>(0, "", null);
        }
    }
}
