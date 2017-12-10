Prerequisite Setup:

1. Install mysqlDB

2. Open mysql shell and create database

    create database acme;
    
    use acme;
    

3. Create tables in the database

    Create Table IF NOT EXISTS vehicle (id bigint(50) AUTO_INCREMENT,type varchar(50),make varchar(50),size varchar(50),registration varchar(50),create_timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,update_timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,PRIMARY KEY (id));
    
    Create Table IF NOT EXISTS tracker (id bigint(50) AUTO_INCREMENT,make varchar(50),status varchar(10),create_timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,update_timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,PRIMARY KEY (id));
    
    Create Table IF NOT EXISTS route (id bigint(50) AUTO_INCREMENT,pickupAddress varchar(1000),pickupCoordinates varchar(500),deliveryAddress varchar(1000),deliveryCoordinates varchar(500),polyline varchar(1000),create_timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,update_timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,PRIMARY KEY (id));
    
    Create Table IF NOT EXISTS agent (id bigint(50) AUTO_INCREMENT,name varchar(50),emailid varchar(50),mobilenumber varchar(15),create_timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,update_timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,PRIMARY KEY (id));
    
    Create Table IF NOT EXISTS shipmentDetail (id bigint(50) AUTO_INCREMENT,vehicle_id bigint(50),route_id bigint(50),tracker_id bigint(50),agent_id bigint(50),status varchar(50),create_timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,update_timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,PRIMARY KEY (id),FOREIGN KEY (vehicle_id)REFERENCES vehicle(id) ON DELETE CASCADE ON UPDATE CASCADE,FOREIGN KEY (tracker_id)REFERENCES tracker(id) ON DELETE CASCADE ON UPDATE CASCADE,FOREIGN KEY (route_id)REFERENCES route(id) ON DELETE CASCADE ON UPDATE CASCADE);
    
    Create Table IF NOT EXISTS shipmentTrack (id bigint(50) AUTO_INCREMENT,shipment_id bigint(50),currentCoordinates varchar(500),create_timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,update_timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,PRIMARY KEY (id),FOREIGN KEY (shipment_id)REFERENCES shipmentDetail(id) ON DELETE CASCADE ON UPDATE CASCADE);
    
    
Steps to run the service

1. Clone this repository and run the following command on the root

    mvn clean install

2. Run service
    
    java -jar target/web-server.jar
    
The serice is running on http://localhost:8085

Refer the runbook to play with APIs
