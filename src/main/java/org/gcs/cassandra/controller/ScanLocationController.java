package org.gcs.cassandra.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.gcs.cassandra.entity.ScanLocation;
import org.gcs.cassandra.repository.ScanLocationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ScanLocationController {

    @Autowired
    ScanLocationRepository scanLocationRepository;

    @GetMapping("/ScanLocations")
    public ResponseEntity<List<ScanLocation>> getAllScanLocations() {
        try {
            List<ScanLocation> scanLocations = new ArrayList<ScanLocation>();
        
            scanLocationRepository.findAll().forEach(scanLocations::add);
        
            if (scanLocations.isEmpty()) {
              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        
            return new ResponseEntity<>(scanLocations, HttpStatus.OK);
          } catch (Exception e) {
          	e.printStackTrace();
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ScanLocations/get/{id}")
    public ResponseEntity<ScanLocation> getScanLocationById(@PathVariable("id") UUID id) {
        Optional<ScanLocation> scanLocationData = scanLocationRepository.findById(id);
        
        if (scanLocationData.isPresent()) {
            return new ResponseEntity<>(scanLocationData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/ScanLocations")
    public ResponseEntity<ScanLocation> createScanLocation(@RequestBody ScanLocation scanLocation) {
        UUID newUUID = UUID.randomUUID();
        ScanLocation newScanLocation = new ScanLocation(newUUID.toString(), scanLocation.getCoordinates(), scanLocation.getName());
        try {
            ScanLocation _scanLocation = scanLocationRepository.save(newScanLocation);
            return new ResponseEntity<>(_scanLocation, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("ERROR: Unable to save " + newUUID.toString() + ", " + newScanLocation.toString());
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ScanLocations/update/{id}")
    public ResponseEntity<ScanLocation> updateScanLocation(@PathVariable("id") UUID id, @RequestBody ScanLocation scanLocation) {
        Optional<ScanLocation> scanLocationData = scanLocationRepository.findById(id);

        if (scanLocationData.isPresent()) {
          ScanLocation _scanLocation = scanLocationData.get();
          _scanLocation.setCoordinates(scanLocation.getCoordinates());
          _scanLocation.setName(scanLocation.getName());
          return new ResponseEntity<>(scanLocationRepository.save(_scanLocation), HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/ScanLocations/update/{coordinates}")
    public ResponseEntity<List<ScanLocation>> updateScanLocationsByCoordinates(@PathVariable("coordinates") String coordinates, @RequestBody ScanLocation scanLocation) {
        List<ScanLocation> scanLocations = new ArrayList<ScanLocation>();
        List<ScanLocation> scanLocationMatches = new ArrayList<ScanLocation>();
        scanLocationRepository.findAll().forEach(scanLocations::add);
        for (ScanLocation currentScan : scanLocations) {
        	if (currentScan.getCoordinates().equals(coordinates)) {
        		scanLocationMatches.add(currentScan);
        	}
        }

        if (scanLocations.size() > 0) {
            for (ScanLocation _scanLocation : scanLocationMatches) {
                _scanLocation.setCoordinates(scanLocation.getCoordinates());
                _scanLocation.setName(scanLocation.getName());
            }          
          return new ResponseEntity<>(scanLocationRepository.saveAll(scanLocations), HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/ScanLocations/delete/{id}")
    public ResponseEntity<HttpStatus> deleteScanLocation(@PathVariable("id") UUID id) {
        try {
	        scanLocationRepository.deleteById(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          } catch (Exception e) {
        	e.printStackTrace();
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }

    @DeleteMapping("/ScanLocations/delete")
    public ResponseEntity<HttpStatus> deleteAllScanLocations() {
        try {
            scanLocationRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          } catch (Exception e) {
        	e.printStackTrace();
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }

    @GetMapping("/ScanLocations/Coordinates/{coordinates}")
    public ResponseEntity<List<ScanLocation>> findByCoordinates(@PathVariable("coordinates") String coordinates) {
        List<ScanLocation> scanLocations = new ArrayList<ScanLocation>();
        List<ScanLocation> scanLocationMatches = new ArrayList<ScanLocation>();
        scanLocationRepository.findAll().forEach(scanLocations::add);
        for (ScanLocation currentScan : scanLocations) {
        	if (currentScan.getCoordinates().equals(coordinates)) {
        		scanLocationMatches.add(currentScan);
        	}
        }

        if (scanLocationMatches.size() > 0) {
            return new ResponseEntity<>(scanLocationMatches, HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}