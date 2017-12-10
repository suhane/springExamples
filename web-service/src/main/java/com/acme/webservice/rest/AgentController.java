package com.acme.webservice.rest;

import com.acme.webservice.model.Agent;
import com.acme.webservice.rest.types.AgentImpl;
import com.acme.webservice.service.AgentService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class AgentController {

    @Autowired
    private AgentService agentService;

    @RequestMapping(value = "/v1/api/agent/register", method = POST)
    public ResponseEntity<?> addAgent(@RequestBody AgentImpl agent) {


        Agent response= agentService.create(agent);
        if(response==null){
            JsonObject error= new JsonObject();
            error.addProperty("error", "Unable to register vehicle ");
            return  new ResponseEntity<>(error.toString(), HttpStatus.INTERNAL_SERVER_ERROR );
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/api/agent/{agentId}", method = GET)
    public ResponseEntity<?> getAgent(@PathVariable long agentId) {


        Agent response= agentService.get(agentId);
        if(response==null){
            JsonObject error= new JsonObject();
            error.addProperty("error", "Could not find agent with "+agentId);
            return  new ResponseEntity<>(error.toString(), HttpStatus.INTERNAL_SERVER_ERROR );
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
