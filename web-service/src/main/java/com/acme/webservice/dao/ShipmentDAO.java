package com.acme.webservice.dao;

import com.acme.webservice.model.ShipmentDetail;

public interface ShipmentDAO {

    long create(ShipmentDetail shipment);
    ShipmentDetail get(long id);
    ShipmentDetail getShipmentFromTracker(long trackerId);
}
