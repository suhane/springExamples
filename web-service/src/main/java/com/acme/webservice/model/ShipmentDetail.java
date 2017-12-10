package com.acme.webservice.model;

public interface ShipmentDetail {

    public static final String SHIPMENT_STATUS_INTRANSIT = "transit";
    public static final String SHIPMENT_STATUS_COMPLETED = "completed";

    long getId();
    long getVehicleId();
    long getRouteId();
    long getTrackerId();
    long getAgentId();
    String getStatus();

}
