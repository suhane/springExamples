package com.suhane.shoppingCart.service;

import com.suhane.shoppingCart.model.User;
import com.suhane.shoppingCart.rest.pojo.UserPojo;

public interface UserService {

    User getUser(int id);
    User createUser(User user);


}
