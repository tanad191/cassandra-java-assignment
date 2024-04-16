package org.gcs.cassandra;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

// Creating an entity ScanLocation
@Table
public class ScanLocation {
    public ScanLocation() {}
    // Parameterized Constructor to assign the values to the properties of the entity
    public ScanLocation( UUID locationId, String location, String name) 
    { 
        super();
        this.locationId = locationId;
        this.location = location;
        this.name = name;
    }

    @PrimaryKey
    private UUID locationId;
    
    private String location;
    private String name;

    // Overriding the toString method 
    // to find all the values 
    @Override
    public String toString() 
    { 
        return "ScanLocation [locationId=" + locationId.toString() + ", location=" + location + ", name=" + name + "]";
    } 
    
    // Getters and setters of 
    // the properties 
    public UUID getLocationId() 
    { 
        return locationId;
    }
    
    public void setLocationId(UUID locationId) 
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