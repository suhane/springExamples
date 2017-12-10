package com.acme.webservice.service;

import com.acme.webservice.model.Tracker;

public interface TrackerService {

    long create(Tracker tracker);
    Tracker get(long id);
}
