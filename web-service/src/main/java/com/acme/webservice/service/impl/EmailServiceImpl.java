package com.acme.webservice.service.impl;

import com.acme.webservice.model.ShipmentDetail;
import com.acme.webservice.service.AgentService;
import com.acme.webservice.service.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service(value = "EmailService")
public class EmailServiceImpl implements NotificationsService {

    @Autowired
    private AgentService agentService;


    @Override
    public void notifyAgent(ShipmentDetail shipmentDetail) {

        StringBuilder body = new StringBuilder();
        body.append("Route Deviation detected!!!!\n");
        body.append("Shipment id:"+shipmentDetail.getId() +"\n");
        body.append("Truck id:"+shipmentDetail.getVehicleId()+ "\n");
        body.append("Time of deviation:"+ new Date().toString()+"\n");

        String emailId=agentService.get(shipmentDetail.getAgentId()).getEmailId();
        System.out.println(emailId+" is notified via email with the following message \n"+body.toString());

    }
}
