package com.suhane.shoppingCart.service.impl;

import com.suhane.shoppingCart.model.User;
import com.suhane.shoppingCart.model.UserDAO;
import com.suhane.shoppingCart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User getUser(int id) {
        return userDAO.get(id);
    }

    @Override
    public User createUser(User user) {
        int id= (int) userDAO.create(user);
        return userDAO.get(id);

    }

}
