package com.acme.webservice.rest;

import com.google.gson.JsonObject;
import com.acme.webservice.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.acme.webservice.rest.types.VehicleImpl;
import com.acme.webservice.service.VehicleService;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(value = "/v1/api/vehicle/register", method = POST)
    public ResponseEntity<?> addVehicle(@RequestBody VehicleImpl vehicle) {


        long response= vehicleService.create(vehicle);
        if(response==0){
            JsonObject error= new JsonObject();
            error.addProperty("error", "Unable to register vehicle ");
            return  new ResponseEntity<>(error.toString(), HttpStatus.INTERNAL_SERVER_ERROR );
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/api/vehicle/{vehicleId}", method = GET)
    public ResponseEntity<?> getVehicle(@PathVariable long vehicleId) {


        Vehicle response= vehicleService.get(vehicleId);
        if(response==null){
            JsonObject error= new JsonObject();
            error.addProperty("error", "Could not find vehicle with "+vehicleId);
            return  new ResponseEntity<>(error.toString(), HttpStatus.INTERNAL_SERVER_ERROR );
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
