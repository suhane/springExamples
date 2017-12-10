package com.suhane.shoppingCart.rest.pojo;

import com.suhane.shoppingCart.model.User;

public class UserPojo implements User {

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
