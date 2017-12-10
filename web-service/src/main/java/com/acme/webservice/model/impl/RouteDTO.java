package com.acme.webservice.model.impl;

import com.acme.webservice.model.Route;
import com.acme.webservice.rest.types.Coordinates;
import com.acme.webservice.utils.JsonUtils;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="route")
public class RouteDTO implements Route {

    public RouteDTO(){}

    public RouteDTO(Route route){
        setPickupAddress(route.getPickupAddress());
        setPickupCoordinates(route.getPickupCoordinates());
        setDeliveryAddress(route.getDeliveryAddress());
        setDeliveryCoordinates(route.getDeliveryCoordinates());
        setPolyline(route.getPolyline());
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="pickupAddress")
    private String pickupAddress;

    @Column(name="pickupCoordinates")
    private String pickupCoordinates;

    @Column(name="deliveryAddress")
    private String deliveryAddress;

    @Column(name="deliveryCoordinates")
    private String deliveryCoordinates;



    @Column(name="polyline")
    private String polyline;

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

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public Coordinates getPickupCoordinates() {
        return JsonUtils.fromJson(this.pickupCoordinates, Coordinates.class,
                new Coordinates());
    }

    public void setPickupCoordinates(Coordinates pickupCoordinates) {
        this.pickupCoordinates = pickupCoordinates ==null?null:JsonUtils.toJson(pickupCoordinates);
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Coordinates getDeliveryCoordinates() {
        return JsonUtils.fromJson(this.deliveryCoordinates, Coordinates.class,
                new Coordinates());
    }

    public void setDeliveryCoordinates(Coordinates deliveryCoordinates) {
        this.deliveryCoordinates = deliveryCoordinates==null?null:JsonUtils.toJson(deliveryCoordinates);
    }

    @Override
    public String getPolyline() {
        return polyline;
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
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
