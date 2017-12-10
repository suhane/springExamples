package com.acme.webservice.dao;

import com.acme.webservice.model.Agent;

public interface AgentDAO {

    long create(Agent agent);
    Agent get(long id);
}
