package com.acme.webservice.dao.impl;

import com.acme.webservice.dao.AgentDAO;
import com.acme.webservice.model.Agent;
import com.acme.webservice.model.impl.AgentDTO;
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
public class AgentDAOImpl implements AgentDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    @Transactional
    public long create(Agent agent) {
        final Session session=sessionFactory.getCurrentSession();
        AgentDTO agentdto=new AgentDTO(agent);
        return  (long) session.save(agentdto);
    }

    @Override
    @Transactional
    public Agent get(long id) {
        final Session session=sessionFactory.getCurrentSession();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        return get(session, map);
    }

    protected Agent get(Session session, Map<String, Object> filters) {
        final Criteria criteria = session.createCriteria(AgentDTO.class);
        for (Map.Entry<String, Object> filter : filters.entrySet()) {
            criteria.add(Restrictions.eq(filter.getKey(), filter.getValue()));
        }

        return (AgentDTO) criteria.uniqueResult();
    }
}
