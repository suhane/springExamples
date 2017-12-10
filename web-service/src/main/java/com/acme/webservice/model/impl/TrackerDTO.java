package com.acme.webservice.model.impl;

import com.acme.webservice.model.Tracker;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tracker")
public class TrackerDTO implements Tracker {

    public TrackerDTO(){}

    public TrackerDTO(Tracker tracker){
        setMake(tracker.getMake());
        setStatus(tracker.getStatus());
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="make")
    private String make;

    @Column(name="status")
    private String status;

    @Column(name = "create_timestamp")
    private Date createTimestamp = new Date();

    @Column(name = "update_timestamp")
    private Date updateTimestamp;

    @Override
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
