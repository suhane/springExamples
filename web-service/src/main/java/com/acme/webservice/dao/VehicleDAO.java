package com.acme.webservice.dao;

import com.acme.webservice.model.Vehicle;

public interface VehicleDAO {

    long create(Vehicle vehicle);
    Vehicle get(long id);
}
