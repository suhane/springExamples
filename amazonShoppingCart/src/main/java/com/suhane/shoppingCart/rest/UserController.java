package com.suhane.shoppingCart.rest;

import com.google.gson.JsonObject;
import com.suhane.shoppingCart.model.User;
import com.suhane.shoppingCart.rest.pojo.UserPojo;
import com.suhane.shoppingCart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/v1/api/admin/users/{id}", method = GET)
    public ResponseEntity<?> getUser(
             @PathVariable int id) {


        User user= userService.getUser(id);
        if(user==null){
            JsonObject error= new JsonObject();
            error.addProperty("error", "No User found with username "+ id);
            return  new ResponseEntity<>(error.toString(), HttpStatus.NOT_FOUND );
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/api/admin/users", method = POST)
    public ResponseEntity<?> createUser(
            @RequestBody UserPojo user) {


        User userResponse= userService.createUser(user);
        if(userResponse==null){
            JsonObject error= new JsonObject();
            error.addProperty("error", "Unable to create user ");
            return  new ResponseEntity<>(error.toString(), HttpStatus.INTERNAL_SERVER_ERROR );
        }
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }



}
