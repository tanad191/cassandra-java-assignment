package org.gcs.cassandra.dao;

import java.util.ArrayList;
import java.util.List;

import org.gcs.cassandra.service.ScanResult;

public class ScanResultDaoImpl implements ScanResultDao {
	
   //list is working as a database
   List<ScanResult> ScanResults;

   public ScanResultDaoImpl(ScanResult scanResult){
      ScanResults = new ArrayList<ScanResult>();
      ScanResults.add(scanResult);
   }
   @Override
   public void deleteScanResult(ScanResult ScanResult) {
      int scanIndex = ScanResults.indexOf(ScanResult);
      ScanResults.remove(scanIndex);
      System.out.println("ScanResult: Scan ID " + ScanResult.getResultId() + " deleted from database");
   }

   //retrive list of ScanResults from the database
   @Override
   public List<ScanResult> getAllScanResults() {
      return ScanResults;
   }

   @Override
   public ScanResult getScanResultById(String scanId) {
      ScanResult returnresult = new ScanResult();
      for (ScanResult result : ScanResults) {
         if (result.getResultId() == scanId) {
            returnresult = result;
         }
      }
      return returnresult; 
   }

   @Override
   public ScanResult getScanResultByLocation(String location) {
      ScanResult returnresult = new ScanResult();
      for (ScanResult result : ScanResults) {
         if (result.getLocation() == location) {
            returnresult = result;
         }
      }
      return returnresult; 
   }
   @Override
   public ScanResult getScanResultByDay(String day) {
      ScanResult returnresult = new ScanResult();
      for (ScanResult result : ScanResults) {
         if (result.getScanDay() == day) {
            returnresult = result;
         }
      }
      return returnresult; 
   }
   
   @Override
   public void addScanResult(ScanResult scanResult) {
      ScanResults.add(scanResult);
   }
}