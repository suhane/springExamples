package com.acme.webservice.rest;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.acme.webservice.rest.types.Coordinates;
import com.acme.webservice.service.ShipmentTrackerService;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ShipmentTrackerController {

    @Autowired
    private ShipmentTrackerService shipmentTrackerService;

    @RequestMapping(value = "/v1/api/tracker/{trackerId}/location", method = POST)
    public ResponseEntity<?> createTrack(@PathVariable long trackerId,
            @RequestBody  Coordinates coordinates) {


        long response= shipmentTrackerService.create(trackerId,coordinates);
        if(response==0){
            JsonObject error= new JsonObject();
            error.addProperty("error", "Unable to create track ");
            return  new ResponseEntity<>(error.toString(), HttpStatus.INTERNAL_SERVER_ERROR );
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
