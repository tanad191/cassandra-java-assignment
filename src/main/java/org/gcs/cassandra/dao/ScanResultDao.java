package org.gcs.cassandra.dao;

import java.util.List;

import org.gcs.cassandra.service.ScanResult;

public interface ScanResultDao {
   public List<ScanResult> getAllScanResults();
   public ScanResult getScanResultById(String scanId);
   public void addScanResult(ScanResult ScanResult);
   public void deleteScanResult(ScanResult ScanResult);
   public ScanResult getScanResultByLocation(String location);
   public ScanResult getScanResultByDay(String day);
}