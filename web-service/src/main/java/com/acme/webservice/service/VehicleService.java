package com.acme.webservice.service;

import com.acme.webservice.model.Vehicle;

public interface VehicleService {

    long create(Vehicle vehicle);
    Vehicle get(long id);

}
