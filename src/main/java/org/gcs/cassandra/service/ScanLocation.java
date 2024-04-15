package org.gcs.cassandra.service;

// Creating an entity ScanLocation
public class ScanLocation {
    public ScanLocation() {}
    // Parameterized Constructor to assign the values to the properties of the entity
    public ScanLocation( String locationId, String location, String name) 
    { 
        super();
        this.locationId = locationId;
        this.location = location;
        this.name = name;
    }

    private String locationId;
    private String location;
    private String name;

    // Overriding the toString method 
    // to find all the values 
    @Override
    public String toString() 
    { 
        return "ScanLocation [locationId=" + locationId + ", location=" + location + ", name=" + name + "]";
    } 
    
    // Getters and setters of 
    // the properties 
    public String getLocationId() 
    { 
        return locationId;
    }
    
    public void setLocationId(String locationId) 
    { 
        this.locationId = locationId;
    } 
    
    public String getLocation() 
    { 
        return location;
    } 
    
    public void setLocation(String location) 
    { 
        this.location = location;
    }

    public String getName() 
    { 
        return name;
    } 
    
    public void setName(String name) 
    { 
        this.name = name;
    }
}