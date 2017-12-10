package com.acme.webservice.service.impl;

import com.acme.webservice.dao.RouteDAO;
import com.acme.webservice.model.Route;
import com.acme.webservice.rest.types.Coordinates;
import com.acme.webservice.rest.types.RouteImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.acme.webservice.service.RouteService;

@Service(value = "RouteService")
public class RouteServiceImpl implements RouteService{

    @Autowired
    private RouteDAO routeDAO;

    @Autowired
    private GoogleMapsWebService googleMapsWebService;

    @Override
    public Route create(Route routeDef) {
        final Route route = buildRoute(routeDef);
        final long id= routeDAO.create(route);
        return routeDAO.get(id);
    }

    private Route buildRoute(Route routeDef) {
        final RouteImpl route= new RouteImpl();
        Coordinates pickup=googleMapsWebService.getOriginCoordinates(routeDef.getPickupAddress(),routeDef.getDeliveryAddress());
        Coordinates delivery=googleMapsWebService.getDestCoordinates(routeDef.getPickupAddress(),routeDef.getDeliveryAddress());
        route.setPickupAddress(routeDef.getPickupAddress());
        route.setPickupCoordinates(pickup);
        route.setDeliveryAddress(routeDef.getDeliveryAddress());
        route.setDeliveryCoordinates(delivery);
        String polyline= googleMapsWebService.getPolyLine(routeDef.getPickupAddress(),routeDef.getDeliveryAddress());
        route.setPolyline(polyline);
        return route;

    }

    @Override
    public Route get(long id) {
        return routeDAO.get(id);
    }
}
