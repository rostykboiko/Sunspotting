package com.rostykboiko.teamvoy.sunspotting.utils;

import java.util.Date;

public class Locality {
    private Date sunrise;
    private Date sunset;
    private Date solarNoon;
    private long dayLength;
    private Date civilTwilightBegin;
    private Date civilTwilightEnd;
    private Date nauticalTwilightBegin;
    private Date nauticalTwilightEnd;
    private Date astronomicalTwilightBegin;
    private Date astronomicalTwilightEnd;

    private String title;
    private double lat;
    private double lng;

    public Locality(){}

    public Locality(String title,
                    Date sunRise,
                    Date sunSet,
                    Date solarNoon,
                    long dayLength,
                    Date civilTwilightBegin,
                    Date civilTwilightEnd,
                    Date nauticalTwilightBegin,
                    Date nauticalTwilightEnd,
                    Date astronomicalTwilightBegin,
                    Date astronomicalTwilightEnd,
                    double lat,
                    double lng) {
        this.title = title;
        this.sunrise = sunRise;
        this.sunset = sunSet;
        this.solarNoon = solarNoon;
        this.dayLength = dayLength;
        this.civilTwilightBegin = civilTwilightBegin;
        this.civilTwilightEnd = civilTwilightEnd;
        this.nauticalTwilightBegin = nauticalTwilightBegin;
        this.nauticalTwilightEnd = nauticalTwilightEnd;
        this.astronomicalTwilightBegin = astronomicalTwilightBegin;
        this.astronomicalTwilightEnd = astronomicalTwilightEnd;
        this.lat = lat;
        this.lng = lng;
    }

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

    public Date getSolarNoon() {
        return solarNoon;
    }

    public void setSolarNoon(Date solarNoon) {
        this.solarNoon = solarNoon;
    }

    public long getDayLength() {
        return dayLength;
    }

    public void setDayLength(long dayLength) {
        this.dayLength = dayLength;
    }

    public Date getCivilTwilightBegin() {
        return civilTwilightBegin;
    }

    public void setCivilTwilightBegin(Date civilTwilightBegin) {
        this.civilTwilightBegin = civilTwilightBegin;
    }

    public Date getCivilTwilightEnd() {
        return civilTwilightEnd;
    }

    public void setCivilTwilightEnd(Date civilTwilightEnd) {
        this.civilTwilightEnd = civilTwilightEnd;
    }

    public Date getNauticalTwilightBegin() {
        return nauticalTwilightBegin;
    }

    public void setNauticalTwilightBegin(Date nauticalTwilightBegin) {
        this.nauticalTwilightBegin = nauticalTwilightBegin;
    }

    public Date getNauticalTwilightEnd() {
        return nauticalTwilightEnd;
    }

    public void setNauticalTwilightEnd(Date nauticalTwilightEnd) {
        this.nauticalTwilightEnd = nauticalTwilightEnd;
    }

    public Date getAstronomicalTwilightBegin() {
        return astronomicalTwilightBegin;
    }

    public void setAstronomicalTwilightBegin(Date astronomicalTwilightBegin) {
        this.astronomicalTwilightBegin = astronomicalTwilightBegin;
    }

    public Date getAstronomicalTwilightEnd() {
        return astronomicalTwilightEnd;
    }

    public void setAstronomicalTwilightEnd(Date astronomicalTwilightEnd) {
        this.astronomicalTwilightEnd = astronomicalTwilightEnd;
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
