package com.acme.webservice.rest.types;

import com.acme.webservice.model.Route;

import java.io.Serializable;

public class RouteImpl implements Route,Serializable {

    private static final long serialVersionUID = -4945179863602803560L;
    private String pickupAddress;
    private  Coordinates pickupCoordinates;
    private String deliveryAddress;
    private Coordinates deliveryCoordinates;
    private long id;
    private String polyline;


    public RouteImpl() {

    }

    public long getId() {
        return id;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public Coordinates getPickupCoordinates() {
        return pickupCoordinates;
    }

    public void setPickupCoordinates(Coordinates pickupCoordinates) {
        this.pickupCoordinates = pickupCoordinates;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Coordinates getDeliveryCoordinates() {
        return deliveryCoordinates;
    }

    public void setDeliveryCoordinates(Coordinates deliveryCoordinates) {
        this.deliveryCoordinates = deliveryCoordinates;
    }

    @Override
    public String getPolyline() {
        return polyline;
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }

}
