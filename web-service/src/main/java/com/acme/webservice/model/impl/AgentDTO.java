package com.acme.webservice.model.impl;

import com.acme.webservice.model.Agent;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "agent")
public class AgentDTO implements Agent {

    public AgentDTO(){

    }

    public AgentDTO(Agent agent){
        setName(agent.getName());
        setEmailId(agent.getEmailId());
        setMobilenumber(agent.getMobileNumber());
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="emailid")
    private String emailId;

    @Column(name="mobilenumber")
    private String mobileNumber;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobileNumber = mobilenumber;
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
