package com.acme.webservice.dao.impl;

import com.acme.webservice.dao.ShipmentTrackDAO;
import com.acme.webservice.model.ShipmentTrack;
import com.acme.webservice.model.impl.ShipmentTrackDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ShipmentTrackDAOImpl implements ShipmentTrackDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public long create(ShipmentTrack shipmentTrack) {
        final Session session = sessionFactory.getCurrentSession();
        ShipmentTrack trackerdto = new ShipmentTrackDTO(shipmentTrack);
        return (long) (Long) session.save(trackerdto);
    }


}
