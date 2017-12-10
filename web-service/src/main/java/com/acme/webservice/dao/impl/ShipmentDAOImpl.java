package com.acme.webservice.dao.impl;

import com.acme.webservice.dao.ShipmentDAO;
import com.acme.webservice.model.ShipmentDetail;
import com.acme.webservice.model.impl.ShipmentDTO;
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
public class ShipmentDAOImpl implements ShipmentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public long create(ShipmentDetail shipment) {
        final Session session=sessionFactory.getCurrentSession();
        ShipmentDetail shipmentdto= new ShipmentDTO(shipment);
        return (long) (Long) session.save(shipmentdto);
    }

    @Override
    @Transactional
    public ShipmentDetail get(long id) {
        final Session session= sessionFactory.getCurrentSession();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        return get(session, map);
    }

    @Override
    @Transactional
    public ShipmentDetail getShipmentFromTracker(long trackerId) {

        final Session session = sessionFactory.getCurrentSession();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("trackerId", trackerId);
        return get(session, map);

    }

    protected ShipmentDetail get(Session session, Map<String, Object> filters) {
        final Criteria criteria = session.createCriteria(ShipmentDTO.class);
        for (Map.Entry<String, Object> filter : filters.entrySet()) {
            criteria.add(Restrictions.eq(filter.getKey(), filter.getValue()));
        }

        return (ShipmentDTO) criteria.uniqueResult();
    }
}
