package com.acme.webservice.model.impl;

import com.acme.webservice.model.ShipmentTrack;
import com.acme.webservice.rest.types.Coordinates;
import com.acme.webservice.utils.JsonUtils;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="shipmentTrack")
public class ShipmentTrackDTO implements ShipmentTrack {

    public ShipmentTrackDTO(){}

    public ShipmentTrackDTO(ShipmentTrack shipmenttrack){
        setShipmentId(shipmenttrack.getShipmentId());
        setCurrentCoordinates(shipmenttrack.getCurrentCoordinates());
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="shipment_id")
    private long shipmentId;

    @Column(name="currentCoordinates")
    private String currentCoordinates;

    @Column(name = "create_timestamp")
    private Date createTimestamp = new Date();

    @Column(name = "update_timestamp")
    private Date updateTimestamp;

    @Override
    public long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(long shipmentId) {
        this.shipmentId = shipmentId;
    }

    @Override
    public Coordinates getCurrentCoordinates() {
        return JsonUtils.fromJson(this.currentCoordinates, Coordinates.class,
                new Coordinates());
    }

    public void setCurrentCoordinates(Coordinates currentCoordinates) {
        this.currentCoordinates = currentCoordinates==null?null:JsonUtils.toJson(currentCoordinates);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
