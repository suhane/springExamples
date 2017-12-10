package com.acme.webservice.rest.types;

import com.acme.webservice.model.Vehicle;

import java.io.Serializable;

public class VehicleImpl implements Vehicle,Serializable{

    public VehicleImpl(){

    }

    private static final long serialVersionUID = -3651912764809314092L;
    private String make;
    private String type;
    private String registration;
    private long id;

    public void setMake(String make) {
        this.make = make;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getMake() {
        return make;
    }

    public String getRegistration() {
        return registration;
    }
}
