package com.acme.webservice.dao.impl;

import com.acme.webservice.dao.VehicleDAO;
import com.acme.webservice.model.Vehicle;
import com.acme.webservice.model.impl.VehicleDTO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Repository
public class VehicleDAOImpl implements VehicleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public long create(Vehicle vehicle) {
        final Session session=sessionFactory.getCurrentSession();
        Vehicle vehicleDTO= new VehicleDTO(vehicle);
        return (long) (Long) session.save(vehicleDTO);
    }

    @Override
    @Transactional
    public Vehicle get(long id) {
        final Session session=sessionFactory.getCurrentSession();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        return get(session, map);
    }

    protected Vehicle get(Session session, Map<String, Object> filters) {
        final Criteria criteria = session.createCriteria(VehicleDTO.class);
        for (Map.Entry<String, Object> filter : filters.entrySet()) {
            criteria.add(Restrictions.eq(filter.getKey(), filter.getValue()));
        }

        return (VehicleDTO) criteria.uniqueResult();
    }
}
