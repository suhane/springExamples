package com.acme.webservice.rest;

import com.acme.webservice.model.ShipmentDetail;
import com.acme.webservice.rest.types.ShipmentImpl;
import com.acme.webservice.service.ShipmentService;
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
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @RequestMapping(value = "/v1/api/shipment/register", method = POST)
    public ResponseEntity<?> addShipment(@RequestBody ShipmentImpl shipment) {

        long response= shipmentService.create(shipment);
        if(response==0){
            JsonObject error= new JsonObject();
            error.addProperty("error", "Unable to register shipment ");
            return  new ResponseEntity<>(error.toString(), HttpStatus.INTERNAL_SERVER_ERROR );
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/api/shipment/{shipmentId}", method = GET)
    public ResponseEntity<?> getShipment(@PathVariable long shipmentId) {

        ShipmentDetail response= shipmentService.get(shipmentId);
        if(response==null){
            JsonObject error= new JsonObject();
            error.addProperty("error", "Could not find shipment with "+shipmentId);
            return  new ResponseEntity<>(error.toString(), HttpStatus.INTERNAL_SERVER_ERROR );
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
