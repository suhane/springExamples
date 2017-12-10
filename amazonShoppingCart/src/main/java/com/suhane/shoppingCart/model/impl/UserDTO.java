package com.suhane.shoppingCart.model.impl;


import com.suhane.shoppingCart.model.User;

import javax.persistence.*;

@Entity
@Table(name="user")
public class UserDTO implements User {


    @Column(name = "name")
    private String name;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public UserDTO(){}

    public UserDTO(User user) {
        this.name= user.getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
