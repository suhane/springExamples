package com.acme.webservice.service;

import com.acme.webservice.model.ShipmentDetail;

public interface ShipmentService {

    long create(ShipmentDetail shipmentDetail);
    ShipmentDetail get(long id);
}
