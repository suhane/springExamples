package com.acme.webservice.service;

import com.acme.webservice.model.ShipmentDetail;

public interface NotificationsService {

    void notifyAgent(ShipmentDetail shipmentDetail);
}
