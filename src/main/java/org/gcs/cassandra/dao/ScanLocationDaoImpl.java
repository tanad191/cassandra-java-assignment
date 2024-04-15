package org.gcs.cassandra.dao;

import java.util.ArrayList;
import java.util.List;

import org.gcs.cassandra.service.ScanLocation;

public class ScanLocationDaoImpl implements ScanLocationDao {
	
   //list is working as a database
   List<ScanLocation> ScanLocations;

   public ScanLocationDaoImpl(ScanLocation scanLocation){
      ScanLocations = new ArrayList<ScanLocation>();
      ScanLocations.add(scanLocation);
   }

   @Override
   public void deleteScanLocation(ScanLocation scanLocation) {
      ScanLocations.remove(scanLocation);
      System.out.println("ScanLocation: Scan ID " + scanLocation.getLocationId() + " deleted from database");
   }

   //retrive list of ScanLocations from the database
   @Override
   public List<ScanLocation> getAllScanLocations() {
      return ScanLocations;
   }

   @Override
   public ScanLocation getScanLocationById(String scanId) {
      ScanLocation returnresult = new ScanLocation();
      for (ScanLocation result : ScanLocations) {
         if (result.getLocationId() == scanId) {
            returnresult = result;
         }
      }
      return returnresult; 
   }
   @Override
   public ScanLocation getScanLocationByLocation(String location) {
      ScanLocation returnresult = new ScanLocation();
      for (ScanLocation result : ScanLocations) {
         if (result.getLocation() == location) {
            returnresult = result;
         }
      }
      return returnresult; 
   }

   @Override
   public void addScanLocation(ScanLocation scanLocation) {
      ScanLocations.add(scanLocation);
   }
}