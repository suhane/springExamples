package com.acme.webservice.model;

import com.acme.webservice.rest.types.Coordinates;


public interface ShipmentTrack {

    long getId();

    long getShipmentId();

    Coordinates getCurrentCoordinates();
}
