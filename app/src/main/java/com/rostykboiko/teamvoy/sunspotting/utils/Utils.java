package com.rostykboiko.teamvoy.sunspotting.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Calendar;
import java.util.Date;


public class Utils {

    static public Locality timeZoneConverter(Locality locality, TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance();

        Date date = locality.getSunrise();
        date.setTime(locality.getSunrise().getTime()
                + ((timeZone.getRawOffset() * 1000) - calendar.getTimeZone().getRawOffset()));
        locality.setSunrise(date);

        date = locality.getSunset();
        date.setTime(locality.getSunset().getTime()
                + ((timeZone.getRawOffset() * 1000) - calendar.getTimeZone().getRawOffset()));
        locality.setSunset(date);

        return locality;
    }

    public static String convertSunResult(String json) {
        int indexStart = 11;
        int indexEnd = json.indexOf("}") + 1;
        json = json.substring(indexStart, indexEnd);
        System.out.println("response " + json);

        return json;
    }

    public static String parseResponse(HttpURLConnection connection) {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
