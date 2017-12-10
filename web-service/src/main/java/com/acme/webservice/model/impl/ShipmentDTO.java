package com.acme.webservice.model.impl;

import com.acme.webservice.model.ShipmentDetail;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="shipmentDetail")
public class ShipmentDTO implements ShipmentDetail {

    public ShipmentDTO(){}

    public ShipmentDTO(ShipmentDetail shipment){
        setRouteId(shipment.getRouteId());
        setTrackerId(shipment.getTrackerId());
        setVehicleId(shipment.getVehicleId());
        setAgentId(shipment.getAgentId());
        setStatus(shipment.getStatus());
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "vehicle_id")
    private long vehicleId;

    @Column(name = "route_id")
    private long routeId;

    @Column(name = "tracker_id")
    private long trackerId;

    @Column(name = "status")
    private String status;

    @Column(name = "agent_id")
    private long agentId;

    @Column(name = "create_timestamp")
    private Date createTimestamp = new Date();

    @Column(name = "update_timestamp")
    private Date updateTimestamp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Date getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Date updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }
}
