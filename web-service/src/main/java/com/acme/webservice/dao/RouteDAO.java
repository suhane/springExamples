package com.acme.webservice.dao;

import com.acme.webservice.model.Route;

public interface RouteDAO {

    long create(Route route);
    Route get(long id);
}
