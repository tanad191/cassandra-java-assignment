package org.gcs.cassandra.entity;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class ScanLocation {
  
  @PrimaryKey
  private UUID locationId;

  private String coordinates;
  
  private String name;
  
  public ScanLocation() {
    
  }
  
  public ScanLocation(UUID locationId, String location, String name) {
    this.locationId = locationId;
    this.coordinates = location;
    this.name = name;
  }

  public UUID getId() {
    return locationId;
  }

  public void setId(UUID id) {
    this.locationId = id;
  }

  public String getLocation() {
    return coordinates;
  }

  public void setLocation(String Location) {
    this.coordinates = Location;
  }

  public String getName() {
    return name;
  }

  public void setName(String Name) {
    this.name = Name;
  }
  
  @Override
  public String toString() {
    return "ScanLocation [id=" + locationId + ", coordinates=" + coordinates + ", name=" + name + "]";
  }
}