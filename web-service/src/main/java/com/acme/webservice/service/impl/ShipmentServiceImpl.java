package com.acme.webservice.service.impl;

import com.acme.webservice.dao.ShipmentDAO;
import com.acme.webservice.dao.TrackerDAO;
import com.acme.webservice.model.ShipmentDetail;
import com.acme.webservice.model.Tracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.acme.webservice.service.ShipmentService;

@Service(value = "shipmentService")
public class ShipmentServiceImpl implements ShipmentService{

    @Autowired
    private ShipmentDAO shipmentDAO;

    @Autowired
    private TrackerDAO trackerDAO;

    @Override
    public long create(ShipmentDetail shipmentDetail) {

        if(!validateTracker(shipmentDetail.getTrackerId()))
            throw  new RuntimeException("The tracker provided with this shipment is already in use");
        trackerDAO.update(shipmentDetail.getTrackerId(),Tracker.TRACKER_STATUS_ATTACHED);
        return shipmentDAO.create(shipmentDetail);
    }

    private boolean validateTracker(long trackerId) {
        String status=trackerDAO.get(trackerId).getStatus();

        return status==null|| status.equals(Tracker.TRACKER_STATUS_FREE);
    }

    @Override
    public ShipmentDetail get(long id) {
        return shipmentDAO.get(id);
    }
}
