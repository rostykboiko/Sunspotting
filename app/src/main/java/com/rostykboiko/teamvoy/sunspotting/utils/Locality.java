package com.rostykboiko.teamvoy.sunspotting.utils;

public class Locality {
    private String title;
    private String sunRise;
    private String sunSet;
    private String solarNoon;
    private String dayLength;
    private String civilTwilightBegin;
    private String civilTwilightEnd;
    private String nauticalTwilightBegin;
    private String nauticalTwilightEnd;
    private String astronomicalTwilightBegin;
    private String astronomicalTwilightEnd;
    private double lat;
    private double lng;

    public Locality(){}

    public Locality(String title,
                    String sunRise,
                    String sunSet,
                    String solarNoon,
                    String dayLength,
                    String civilTwilightBegin,
                    String civilTwilightEnd,
                    String nauticalTwilightBegin,
                    String nauticalTwilightEnd,
                    String astronomicalTwilightBegin,
                    String astronomicalTwilightEnd,
                    double lat,
                    double lng) {
        this.title = title;
        this.sunRise = sunRise;
        this.sunSet = sunSet;
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

    public String getSunRise() {
        return sunRise;
    }

    public void setSunRise(String sunRise) {
        this.sunRise = sunRise;
    }

    public String getSunSet() {
        return sunSet;
    }

    public void setSunSet(String sunSet) {
        this.sunSet = sunSet;
    }

    public String getSolarNoon() {
        return solarNoon;
    }

    public void setSolarNoon(String solarNoon) {
        this.solarNoon = solarNoon;
    }

    public String getDayLength() {
        return dayLength;
    }

    public void setDayLength(String dayLength) {
        this.dayLength = dayLength;
    }

    public String getCivilTwilightBegin() {
        return civilTwilightBegin;
    }

    public void setCivilTwilightBegin(String civilTwilightBegin) {
        this.civilTwilightBegin = civilTwilightBegin;
    }

    public String getCivilTwilightEnd() {
        return civilTwilightEnd;
    }

    public void setCivilTwilightEnd(String civilTwilightEnd) {
        this.civilTwilightEnd = civilTwilightEnd;
    }

    public String getNauticalTwilightBegin() {
        return nauticalTwilightBegin;
    }

    public void setNauticalTwilightBegin(String nauticalTwilightBegin) {
        this.nauticalTwilightBegin = nauticalTwilightBegin;
    }

    public String getNauticalTwilightEnd() {
        return nauticalTwilightEnd;
    }

    public void setNauticalTwilightEnd(String nauticalTwilightEnd) {
        this.nauticalTwilightEnd = nauticalTwilightEnd;
    }

    public String getAstronomicalTwilightBegin() {
        return astronomicalTwilightBegin;
    }

    public void setAstronomicalTwilightBegin(String astronomicalTwilightBegin) {
        this.astronomicalTwilightBegin = astronomicalTwilightBegin;
    }

    public String getAstronomicalTwilightEnd() {
        return astronomicalTwilightEnd;
    }

    public void setAstronomicalTwilightEnd(String astronomicalTwilightEnd) {
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
