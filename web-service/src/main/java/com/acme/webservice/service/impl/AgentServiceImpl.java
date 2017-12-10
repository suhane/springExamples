package com.acme.webservice.service.impl;

import com.acme.webservice.dao.AgentDAO;
import com.acme.webservice.dao.RouteDAO;
import com.acme.webservice.model.Agent;
import com.acme.webservice.model.Route;
import com.acme.webservice.rest.types.Coordinates;
import com.acme.webservice.rest.types.RouteImpl;
import com.acme.webservice.service.AgentService;
import com.acme.webservice.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "AgentService")
public class AgentServiceImpl implements AgentService{

    @Autowired
    private AgentDAO agentDAO;

    @Override
    public Agent create(Agent agent) {

        final long id=  agentDAO.create(agent);
        return agentDAO.get(id);
    }



    @Override
    public Agent get(long id) {
        return agentDAO.get(id);
    }
}
