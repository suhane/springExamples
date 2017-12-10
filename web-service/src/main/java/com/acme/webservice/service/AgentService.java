package com.acme.webservice.service;

import com.acme.webservice.model.Agent;

public interface AgentService {

    Agent create(Agent route);
    Agent get(long id);
}
