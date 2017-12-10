package com.acme.webservice.rest.types;

import com.acme.webservice.model.Agent;

import java.io.Serializable;

public class AgentImpl implements Serializable,Agent{

    private static final long serialVersionUID = -4945179863602803560L;
    private String name;
    private  String mobilenumber;
    private String emailid;

    @Override
    public long getId() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getEmailId() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }
}
