package org.gcs.cassandra.dao;

import java.util.List;
import java.util.UUID;

import org.gcs.cassandra.ScanResult;

public interface ScanResultDao {
   public List<ScanResult> getAllScanResults();
   public ScanResult getScanResultById(UUID scanId);
   public void addScanResult(ScanResult ScanResult);
   public void deleteScanResult(ScanResult ScanResult);
   public ScanResult getScanResultByLocation(String location);
   public ScanResult getScanResultByDay(String day);
   public void Clear();
}