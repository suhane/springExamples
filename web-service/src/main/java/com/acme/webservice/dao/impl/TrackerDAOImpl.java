package com.acme.webservice.dao.impl;

import com.acme.webservice.dao.TrackerDAO;
import com.acme.webservice.model.Tracker;
import com.acme.webservice.model.impl.TrackerDTO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TrackerDAOImpl implements TrackerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public long create(Tracker tracker) {
        final Session session= sessionFactory.getCurrentSession();
        Tracker trackerDTO= new TrackerDTO(tracker);
        return (long) (Long) session.save(trackerDTO);
    }

    @Override
    @Transactional
    public Tracker get(long id) {
        final Session session=sessionFactory.getCurrentSession();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        return get(session, map);
    }

    protected Tracker get(Session session, Map<String, Object> filters) {
        final Criteria criteria = session.createCriteria(TrackerDTO.class);
        for (Map.Entry<String, Object> filter : filters.entrySet()) {
            criteria.add(Restrictions.eq(filter.getKey(), filter.getValue()));
        }
        return (TrackerDTO) criteria.uniqueResult();
    }

    @Override
    @Transactional
    public Tracker update(long id, String status) {
        final Session session= sessionFactory.getCurrentSession();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        final TrackerDTO trackerDTO= (TrackerDTO) get(session, map);
        trackerDTO.setStatus(status);
        trackerDTO.setUpdateTimestamp(new Date());
        session.save(trackerDTO);
        return trackerDTO;
    }
}
