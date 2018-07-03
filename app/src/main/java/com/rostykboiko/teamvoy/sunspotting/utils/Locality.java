package com.rostykboiko.teamvoy.sunspotting.utils;

import java.util.Date;

public class Locality {
    private Date sunrise;
    private Date sunset;

    private String title;
    private double lat;
    private double lng;

    public Locality(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getSunrise() {
        return sunrise;
    }

    public void setSunrise(Date sunrise) {
        this.sunrise = sunrise;
    }

    public Date getSunset() {
        return sunset;
    }

    public void setSunset(Date sunset) {
        this.sunset = sunset;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
