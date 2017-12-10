package com.acme.webservice.service.impl;

import com.acme.webservice.dao.VehicleDAO;
import com.acme.webservice.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.acme.webservice.service.VehicleService;

@Service(value="VehicleService")
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleDAO vehicleDAO;


    @Override
    public long create(Vehicle vehicle) {
        return vehicleDAO.create(vehicle);
    }

    @Override
    public Vehicle get(long id) {
        return vehicleDAO.get(id);
    }
}
