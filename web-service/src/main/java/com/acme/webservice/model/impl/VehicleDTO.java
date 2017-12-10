package com.acme.webservice.model.impl;

import com.acme.webservice.model.Vehicle;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="vehicle")
public class VehicleDTO implements Vehicle {

    public VehicleDTO(){}

    public VehicleDTO(Vehicle vehicle){
        setMake(vehicle.getMake());
        setRegistration(vehicle.getRegistration());
        setType(vehicle.getType());
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="make")
    private String make;

    @Column(name="type")
    private String type;

    @Column(name="registration")
    private String registration;

    @Column(name = "create_timestamp")
    private Date createTimestamp = new Date();

    @Column(name = "update_timestamp")
    private Date updateTimestamp=new Date();;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
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
