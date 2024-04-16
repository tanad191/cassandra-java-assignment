package org.gcs.cassandra.dao;

import java.util.List;
import java.util.UUID;

import org.gcs.cassandra.ScanLocation;

public interface ScanLocationDao {
   public List<ScanLocation> getAllScanLocations();
   public ScanLocation getScanLocationById(UUID scanId);
   public void addScanLocation(ScanLocation ScanLocation);
   public void deleteScanLocation(ScanLocation ScanLocation);
   public ScanLocation getScanLocationByLocation(String location);
   public void Clear();
}