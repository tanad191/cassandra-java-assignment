package org.gcs.cassandra.service;

import java.util.List;

// Creating an entity ScanResult
public class ScanResult {
    public ScanResult() {}
    // Parameterized Constructor to assign the values to the properties of the entity
    public ScanResult( String resultId, String location, String scanDay, String birdId, String birdSpecies, List<String> birdTraits )
    { 
        super();
        this.resultId = resultId;
        this.location = location;
        this.scanDay = scanDay;
        this.birdId = birdId;
        this.birdSpecies = birdSpecies;
        this.birdTraits = birdTraits;
    }

    private String resultId;
    private String location;
    private String scanDay;
    private String birdId;
    private String birdSpecies;
    private List<String> birdTraits;

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
        return "ScanResult [resultId=" + resultId + ", location=" + location + ", scanDay=" + scanDay + ", birdId=" + birdId + ", birdSpecies=" + birdSpecies + ", birdTraits= " + birdTraitList + "]";
    } 
    
    // Getters and setters of 
    // the properties 
    public String getResultId() 
    { 
        return resultId;
    }
    
    public void setResultId(String resultId) 
    { 
        this.resultId = resultId;
    } 
    
    public String getLocation() 
    { 
        return location;
    } 
    
    public void setLocation(String location) 
    { 
        this.location = location;
    }

    public String getScanDay() 
    { 
        return scanDay;
    } 
    
    public void getScanDay(String scanDay) 
    { 
        this.scanDay = scanDay;
    }

    public String getBirdId() 
    { 
        return birdId;
    } 
    
    public void setBirdId(String birdId) 
    { 
        this.birdId = birdId;
    }
    public Object getName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
    }


}