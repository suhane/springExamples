package com.acme.webservice.service.impl;

import com.acme.webservice.dao.*;
import com.acme.webservice.model.Route;
import com.acme.webservice.model.ShipmentDetail;
import com.acme.webservice.model.ShipmentTrack;
import com.acme.webservice.service.NotificationsService;
import com.acme.webservice.utils.PolyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.acme.webservice.rest.types.Coordinates;
import com.acme.webservice.rest.types.ShipmentTrackImpl;
import com.acme.webservice.service.ShipmentTrackerService;

import java.util.List;

@Service(value = "ShipmentTrackerService")
public class ShipmentTrackerServiceimpl implements ShipmentTrackerService {

    @Autowired
    private ShipmentDAO shipmentDAO;

    @Autowired
    private ShipmentTrackDAO shipmentTrackDAO;

    @Autowired
    private TrackerDAO trackerDAO;

    @Autowired
    private RouteDAO routeDAO;

    @Autowired
    private NotificationsService notificationsService;

    @Override
    public long create(long trackerId,Coordinates coordinates) {

        if(trackerDAO.get(trackerId)==null)
            throw new RuntimeException(
                    "Tracker id not found");
        ShipmentDetail shipment=shipmentDAO.getShipmentFromTracker(trackerId);
        if(shipment==null || shipment.getStatus().equals(ShipmentDetail.SHIPMENT_STATUS_COMPLETED))
            throw new RuntimeException(
                    "This tracker is not attached to any shipment.Its tracking will not be covered in our service ");
        long shipmentId=shipment.getId();
        final ShipmentTrack shipmentTrack= buildTrack(coordinates,shipmentId);
        computeRouteDeviation(shipmentTrack,coordinates);
        final long id=shipmentTrackDAO.create(shipmentTrack);
        return id;

    }

    private void computeRouteDeviation(ShipmentTrack shipmentTrack, Coordinates coordinates) {
        double tolerance=3500.0;
        ShipmentDetail shipment= shipmentDAO.get(shipmentTrack.getShipmentId());
        Route route= routeDAO.get(shipment.getRouteId());
        String encodedPolyline=route.getPolyline();
        List<Coordinates> poly=PolyUtil.decode(encodedPolyline);
        if(PolyUtil.locationIndexOnPath(coordinates,poly,true,tolerance)==-1){
            notificationsService.notifyAgent(shipment);
        }
    }

    private ShipmentTrack buildTrack(Coordinates coordinates, long shipmentId) {

        ShipmentTrackImpl track=new ShipmentTrackImpl();
        track.setShipmentId(shipmentId);
        track.setCurrentCoordinates(coordinates);
        return track;
    }
}
