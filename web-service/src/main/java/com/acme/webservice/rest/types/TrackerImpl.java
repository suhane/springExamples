package com.acme.webservice.rest.types;

import com.acme.webservice.model.Tracker;

import java.io.Serializable;

public class TrackerImpl implements Serializable, Tracker {

    private long id;
    private String make;
    private String status;

    public TrackerImpl(){

    }

    public long getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
