package org.gcs.cassandra.dao;

import java.util.List;

import org.gcs.cassandra.service.ScanLocation;

public interface ScanLocationDao {
   public List<ScanLocation> getAllScanLocations();
   public ScanLocation getScanLocationById(String scanId);
   public void addScanLocation(ScanLocation ScanLocation);
   public void deleteScanLocation(ScanLocation ScanLocation);
   public ScanLocation getScanLocationByLocation(String location);
}