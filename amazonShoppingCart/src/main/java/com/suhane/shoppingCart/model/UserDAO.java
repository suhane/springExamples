package com.suhane.shoppingCart.model;

public interface UserDAO {

    long create(User user);
    User get(long id);


}
