package com.acme.webservice.dao;

import com.acme.webservice.model.Tracker;

public interface TrackerDAO {

    long create(Tracker tracker);
    Tracker get(long id);
    Tracker update(long id, String newStatus);
}
