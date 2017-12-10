package com.acme.webservice.rest;

import com.acme.webservice.model.Tracker;
import com.acme.webservice.rest.types.TrackerImpl;
import com.acme.webservice.service.TrackerService;
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
public class DeviceController {

    @Autowired
    private TrackerService trackerService;

    @RequestMapping(value = "/v1/api/tracker/register", method = POST)
    public ResponseEntity<?> addTracker(@RequestBody TrackerImpl tracker) {


        long response= trackerService.create(tracker);
        if(response==0){
            JsonObject error= new JsonObject();
            error.addProperty("error", "Unable to register tracker ");
            return  new ResponseEntity<>(error.toString(), HttpStatus.INTERNAL_SERVER_ERROR );
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/api/tracker/{trackerId}", method = GET)
    public ResponseEntity<?> getTracker(@PathVariable long trackerId) {


        Tracker response= trackerService.get(trackerId);
        if(response==null){
            JsonObject error= new JsonObject();
            error.addProperty("error", "Could not find tracker with "+trackerId);
            return  new ResponseEntity<>(error.toString(), HttpStatus.INTERNAL_SERVER_ERROR );
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
