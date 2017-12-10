package com.acme.webservice.rest.types;

import com.acme.webservice.model.ShipmentTrack;

public class ShipmentTrackImpl implements ShipmentTrack{



    private long id;
    private long shipmentId;
    private Coordinates currentCoordinates;

    public ShipmentTrackImpl(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Coordinates getCurrentCoordinates() {
        return currentCoordinates;
    }

    public void setCurrentCoordinates(Coordinates currentCoordinates) {
        this.currentCoordinates = currentCoordinates;
    }
}
