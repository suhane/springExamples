package com.acme.webservice.service.impl;

import com.acme.webservice.rest.types.Coordinates;
import com.acme.webservice.utils.JsonUtils;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.HashMap;

@Service(value = "GoogleMapsWebService")
public class GoogleMapsWebService {

    private static final String DIRECTIONS_URL = "https://maps.googleapis.com/maps/api/directions/json";

    private JSONObject route=null;

    private JSONObject getDirections(String origin, String dest){

        if(route==null){
            HashMap<String, String> points = new HashMap<>();
            String url=DIRECTIONS_URL;
            points.put("origin", origin);
            points.put("destination", dest);
            points.put("key", "AIzaSyDtgAj7YkHhf62XWK80U4djxIzJBuJD5rs");
            final String params= JsonUtils.toEncodedParams(points);
            if (params.length() != 0) {
                url += "?" + params;
            }
            try {
                route = get(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return route;

        }
        return route;


    }

    public String getPolyLine(String origin, String dest){

       JSONArray routeArray = getDirections(origin,dest).getJSONArray("routes");
       JSONObject routes = routeArray.getJSONObject(0);
       JSONObject overviewPolylines = routes.getJSONObject("overview_polyline");
       String encodedString = overviewPolylines.getString("points");
       return encodedString;
    }

    public Coordinates getOriginCoordinates(String origin, String dest){

        JSONArray routeArray = getDirections(origin,dest).getJSONArray("routes");
        JSONObject routes = routeArray.getJSONObject(0);
        JSONArray legsArray = routes.getJSONArray("legs");
        JSONObject legs=legsArray.getJSONObject(0);
        JSONObject start_location = legs.getJSONObject("start_location");
        return JsonUtils.fromJson(start_location.toString(),Coordinates.class,new Coordinates());
    }

    public Coordinates getDestCoordinates(String origin, String dest){

        JSONArray routeArray = getDirections(origin,dest).getJSONArray("routes");
        JSONObject routes = routeArray.getJSONObject(0);
        JSONArray legsArray = routes.getJSONArray("legs");
        JSONObject legs=legsArray.getJSONObject(0);
        JSONObject start_location = legs.getJSONObject("end_location");
        return JsonUtils.fromJson(start_location.toString(),Coordinates.class,new Coordinates());
    }


    private  JSONObject get(String url) throws IOException {

        JSONObject jObj=null;
        final Content response = Request.Get(url).execute()
                .returnContent();
        final String responseString = new String(response.asBytes());
        try {
            jObj = new JSONObject(responseString);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // return JSON Object
        return jObj;
    }
}
