package com.acme.webservice.service;

import com.acme.webservice.rest.types.Coordinates;

public interface ShipmentTrackerService {

    long create(long trackerId,Coordinates coordinates);
}
