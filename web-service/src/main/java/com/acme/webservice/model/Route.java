package com.acme.webservice.model;

import com.acme.webservice.rest.types.Coordinates;

public interface Route {

    long getId();
    String getPickupAddress();
    Coordinates getPickupCoordinates();
    String getDeliveryAddress();
    Coordinates getDeliveryCoordinates();
    String getPolyline();

}
