package com.acme.webservice.model;


public interface Tracker {

    public static final String TRACKER_STATUS_ATTACHED = "attached";
    public static final String TRACKER_STATUS_FREE = "free";
    long getId();
    String getMake();
    String getStatus();
}
