package org.gcs.cassandra.controller;

import org.gcs.cassandra.dao.ScanResultDaoImpl;
import org.gcs.cassandra.service.ScanResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ScanResults")
public class ScanResultController {

    private final ScanResultDaoImpl scanResults;

    @Autowired
    public ScanResultController(ScanResult ScanResult) {
        this.scanResults = new ScanResultDaoImpl(ScanResult);
    }

    @RequestMapping(
        value="/{id}",
        method= {RequestMethod.GET},
        headers= {"content-type=application/json"}
        )
    public ScanResult getScanResultById(@PathVariable String id) {
        return scanResults.getScanResultById(id);
    }

    @RequestMapping(
        method= {RequestMethod.GET},
        headers= {"content-type=application/json"}
        )
    public List<ScanResult> getAllScanResults() {
        return scanResults.getAllScanResults();
    }

    @RequestMapping(
        method= {RequestMethod.POST},
        headers= {"content-type=application/json"}
        )
    public void addScanResult(@RequestBody ScanResult ScanResult) {
        scanResults.addScanResult(ScanResult);
    }

    @RequestMapping(
        value="/{id}",
        method= {RequestMethod.POST},
        headers= {"content-type=application/json"}
        )
    public void deleteScanResult(@PathVariable String ScanResultId) {
        ScanResult OldScanResult = scanResults.getScanResultById(ScanResultId);
        scanResults.deleteScanResult(OldScanResult);
    }
    
    @RequestMapping(
        value="/location/{location}",
        method= {RequestMethod.GET},
        headers= {"content-type=application/json"}
        )
    public ScanResult getScanResultByLocation(@PathVariable String location) {
        return scanResults.getScanResultByLocation(location);
    }
    
    @RequestMapping(
        value="/day/{day}",
        method= {RequestMethod.GET},
        headers= {"content-type=application/json"}
        )
    public ScanResult getScanResultByDay(@PathVariable String day) {
        return scanResults.getScanResultByDay(day);
    }
}