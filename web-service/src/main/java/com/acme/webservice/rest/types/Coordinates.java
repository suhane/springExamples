package com.acme.webservice.rest.types;

import java.io.Serializable;

public class Coordinates implements Serializable {


    private static final long serialVersionUID = 7226370758112700465L;
    private double lat;
    private double lng;

    public Coordinates() {
    }

    public Coordinates(double lat,double lng) {
        this.lat=lat;
        this.lng=lng;
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
