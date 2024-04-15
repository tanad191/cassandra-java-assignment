package org.gcs.cassandra.controller;

import org.gcs.cassandra.dao.ScanLocationDaoImpl;
import org.gcs.cassandra.service.ScanLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ScanLocations")
public class ScanLocationController {

    private final ScanLocationDaoImpl scanLocations;

    @Autowired
    public ScanLocationController(ScanLocation ScanLocation) {
        this.scanLocations = new ScanLocationDaoImpl(ScanLocation);
    }

    @RequestMapping(
        value="/{id}",
        method= {RequestMethod.GET},
        headers= {"content-type=application/json"}
        )
    public ScanLocation getScanLocationById(@PathVariable String id) {
        return scanLocations.getScanLocationById(id);
    }

    @RequestMapping(
        method= {RequestMethod.GET},
        headers= {"content-type=application/json"}
        )
    public List<ScanLocation> getAllScanLocations() {
        return scanLocations.getAllScanLocations();
    }

    @RequestMapping(
        method= {RequestMethod.POST},
        headers= {"content-type=application/json"}
        )
    public void addScanLocation(@RequestBody ScanLocation NewScanLocation) {
        scanLocations.addScanLocation(NewScanLocation);
    }

    @RequestMapping(
        value="/{id}",
        method= {RequestMethod.POST},
        headers= {"content-type=application/json"}
        )
    public void deleteScanLocation(@PathVariable String ScanLocationId) {
        ScanLocation OldScanLocation = scanLocations.getScanLocationById(ScanLocationId);
        scanLocations.deleteScanLocation(OldScanLocation);
    }

    @RequestMapping(
        value="/ScanLocation/{ScanLocation}",
        method= {RequestMethod.GET},
        headers= {"content-type=application/json"}
        )
    public ScanLocation getScanLocationByScanLocation(@PathVariable String TargetScanLocation) {
        return scanLocations.getScanLocationByLocation(TargetScanLocation);
    }
}