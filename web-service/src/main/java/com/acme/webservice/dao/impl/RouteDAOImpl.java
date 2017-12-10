package com.acme.webservice.dao.impl;
import com.acme.webservice.dao.RouteDAO;
import com.acme.webservice.model.Route;
import com.acme.webservice.model.impl.RouteDTO;
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
public class RouteDAOImpl implements RouteDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public long create(Route route) {
        final Session session=sessionFactory.getCurrentSession();
        Route routedto= new RouteDTO(route);
        return (long) (Long) session.save(routedto);
    }

    @Override
    @Transactional
    public Route get(long id) {
        final Session session= sessionFactory.getCurrentSession();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        return get(session, map);
    }

    protected Route get(Session session, Map<String, Object> filters) {
        final Criteria criteria = session.createCriteria(RouteDTO.class);
        for (Map.Entry<String, Object> filter : filters.entrySet()) {
            criteria.add(Restrictions.eq(filter.getKey(), filter.getValue()));
        }

        return (RouteDTO) criteria.uniqueResult();
    }
}
