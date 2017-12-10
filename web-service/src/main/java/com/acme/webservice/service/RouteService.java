package com.acme.webservice.service;

import com.acme.webservice.model.Route;

public interface RouteService {

    Route create(Route route);
    Route get(long id);
}
