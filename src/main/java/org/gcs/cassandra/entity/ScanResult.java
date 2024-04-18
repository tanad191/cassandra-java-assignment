package org.gcs.cassandra.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class ScanResult {
  
  @PrimaryKey
  private String resultId;

  private String coordinates;
  private LocalDate scanDay;
  private String birdId;
  private String birdSpecies;
  private List<String> birdTraits;
  
  public ScanResult() {
    
  }
  
  public ScanResult(String resultId, String location, LocalDate scanDay, String birdId, String birdSpecies, List<String> birdTraits) {
    this.resultId = resultId;
    this.coordinates = location;
    this.scanDay = scanDay;
    this.birdId = birdId;
    this.birdSpecies = birdSpecies;
    this.birdTraits = birdTraits;
  }

  public String getId() {
    return resultId;
  }

  public void setId(String id) {
    this.resultId = id;
  }

  public String getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(String Location) {
    this.coordinates = Location;
  }

  public LocalDate getScanDay() {
    return scanDay;
  }

  public void setScanDay (LocalDate scanDay) {
    this.scanDay = scanDay;
  }

  public String getBirdId() {
    return birdId;
  }

  public void setBirdId(String birdId) {
    this.birdId = birdId;
  }

  public String getBirdSpecies() {
    return birdSpecies;
  }

  public void setBirdSpecies (String birdSpecies) {
    this.birdSpecies = birdSpecies;
  }

  public List<String> getBirdTraits() {
    return birdTraits;
  }
	
	public void setBirdTraits(List<String> newBirdTraits) {
	    this.birdTraits = newBirdTraits;
	}
	
	// Overriding the toString method 
	// to find all the values 
	@Override
	public String toString() 
	{ 
	    String birdTraitList = "[";
	    int i = 0;
	    for (String trait : birdTraits) {
	        birdTraitList = birdTraitList + "\"" + trait + "\"";
	        if (i < birdTraits.size()-1) {
	            birdTraitList = birdTraitList + ",";
	            i++;
	        }
	    }
	    birdTraitList = birdTraitList + "]";
	    return "ScanResult [resultId=" + resultId.toString() + ", coordinates=" + coordinates + ", scanDay=" + scanDay.toString() + ", birdId=" + birdId + ", birdSpecies=" + birdSpecies + ", birdTraits= " + birdTraitList + "]";
	}
}