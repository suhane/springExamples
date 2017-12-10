package com.suhane.shoppingCart.model.impl;

import com.suhane.shoppingCart.model.User;
import com.suhane.shoppingCart.model.UserDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDAOImpl implements UserDAO {


    @Autowired
    private SessionFactory sessionFactory;




    @Override
    @Transactional
    public long create(User user) {
        final Session session=sessionFactory.getCurrentSession();
        final User userDTO= new UserDTO(user);
        long ans= (long) session.save(userDTO);
      //  session.persist(userDTO);
        return ans;
    }

    @Override
    @Transactional
    public User get(long id) {
        final Session session=sessionFactory.getCurrentSession();
        return (User) session.get(UserDTO.class,id);
    }
}
