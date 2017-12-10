package com.acme.webservice.service.impl;

import com.acme.webservice.dao.TrackerDAO;
import com.acme.webservice.model.Tracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.acme.webservice.service.TrackerService;

@Service(value = "TrackerService")
public class TrackerServiceImpl implements TrackerService {

    @Autowired
    private TrackerDAO trackerDAO;

    @Override
    public long create(Tracker tracker) {
        return trackerDAO.create(tracker);
    }

    @Override
    public Tracker get(long id) {
        return trackerDAO.get(id);
    }
}
