package com.acme.webservice.rest.types;

import com.acme.webservice.model.ShipmentDetail;

import java.io.Serializable;

public class ShipmentImpl implements ShipmentDetail,Serializable {

    private static final long serialVersionUID = -1416864152964579310L;
    private long id;
    private long vehicleId;
    private long routeId;
    private long trackerId;
    private long agentId;
    private String status;

    public ShipmentImpl(){

    }

    public long getId() {
        return id;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    public long getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(long trackerId) {
        this.trackerId = trackerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }
}
