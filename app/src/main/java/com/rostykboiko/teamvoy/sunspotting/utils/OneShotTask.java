package com.rostykboiko.teamvoy.sunspotting.utils;

import com.google.gson.Gson;
import com.rostykboiko.teamvoy.sunspotting.main.SunDataCallback;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public class OneShotTask implements Runnable {
    private Locality locality;
    private SunDataCallback callback;

    public OneShotTask(Locality locality, SunDataCallback callback) {
        this.locality = locality;
        this.callback = callback;
    }

    @Override
    public void run() {
        callback.sunInfo(Objects.requireNonNull(getTimeZoneOff(sunInfoRequest(locality))));
    }

    private Locality getTimeZoneOff(Locality locality) {
        try {
            URL timeZoneUrl = new URL("https://maps.googleapis.com/maps/api/timezone/json?"
                    + "location="
                    + locality.getLat() + ","
                    + locality.getLng()
                    + "&timestamp=1458000000"
                    + "&key=" + "AIzaSyAysUE1lse4gt2BR9J7iUGjuKHu0zIm8-I");

            System.out.println("Final timeZoneUrl: " + timeZoneUrl);

            HttpURLConnection conZone = (HttpURLConnection) timeZoneUrl.openConnection();
            conZone.setDoOutput(true);

            conZone.setRequestMethod("GET");
            conZone.setDoOutput(true);
            conZone.connect();

            String json = Utils.parseResponse(conZone);
            System.out.println("Final json: " + json);

            TimeZone timeZone = new Gson().fromJson(json, TimeZone.class);
            return Utils.timeZoneConverter(locality, timeZone);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Locality sunInfoRequest(Locality locality) {
        try {
            double lat = locality.getLat();
            double lng = locality.getLng();
            String title;
            if (locality.getTitle() != null) {
                title = locality.getTitle();
            } else {
                title = "Current";
            }

            URL sunApiUrl = new URL("https://api.sunrise-sunset.org/json?"
                    + "lat=" + lat
                    + "&lng=" + lng
                    + "&formatted=0"
                    + "&date=tomorrow");

            HttpURLConnection conSun = (HttpURLConnection) sunApiUrl.openConnection();
            conSun.setDoOutput(true);

            conSun.setRequestMethod("GET");
            conSun.setDoOutput(true);
            conSun.connect();

            String json = Utils.parseResponse(conSun);
            System.out.println("Final parsed json: " + json);

            locality = new Gson().fromJson(Utils.convertSunResult(json), Locality.class);

            locality.setTitle(title);

            locality.setLat(lat);
            locality.setLng(lng);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return locality;
    }
}

