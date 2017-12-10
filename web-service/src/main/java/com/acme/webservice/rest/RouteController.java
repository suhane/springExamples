package com.acme.webservice.rest;

import com.acme.webservice.model.Route;
import com.acme.webservice.rest.types.RouteImpl;
import com.acme.webservice.service.RouteService;
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
public class RouteController {

    @Autowired
    private RouteService routeService;

    @RequestMapping(value = "/v1/api/route/register", method = POST)
    public ResponseEntity<?> addRoute(@RequestBody RouteImpl route) {

        Route response= routeService.create(route);
        if(response==null){
            JsonObject error= new JsonObject();
            error.addProperty("error", "Unable to register route ");
            return  new ResponseEntity<>(error.toString(), HttpStatus.INTERNAL_SERVER_ERROR );
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/api/route/{routeId}", method = GET)
    public ResponseEntity<?> getRoute(@PathVariable long routeId) {

        Route response= routeService.get(routeId);
        if(response==null){
            JsonObject error= new JsonObject();
            error.addProperty("error", "Could not find route with "+routeId);
            return  new ResponseEntity<>(error.toString(), HttpStatus.INTERNAL_SERVER_ERROR );
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
