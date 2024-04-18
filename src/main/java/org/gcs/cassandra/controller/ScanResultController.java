package org.gcs.cassandra.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.gcs.cassandra.entity.ScanResult;
import org.gcs.cassandra.repository.ScanResultRepository;

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
public class ScanResultController {

    @Autowired
    ScanResultRepository scanResultRepository;

    @GetMapping("/ScanResults")
    public ResponseEntity<List<ScanResult>> getAllScanResults() {
        try {
            List<ScanResult> scanResults = new ArrayList<ScanResult>();
        
            scanResultRepository.findAll().forEach(scanResults::add);
        
            if (scanResults.isEmpty()) {
              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        
            return new ResponseEntity<>(scanResults, HttpStatus.OK);
          } catch (Exception e) {
          	e.printStackTrace();
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ScanResults/get/{id}")
    public ResponseEntity<ScanResult> getScanResultById(@PathVariable("id") UUID id) {
        Optional<ScanResult> scanResultData = scanResultRepository.findById(id);
        
        if (scanResultData.isPresent()) {
            return new ResponseEntity<>(scanResultData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/ScanResults")
    public ResponseEntity<ScanResult> createScanResult(@RequestBody ScanResult scanResult) {
        UUID newUUID = UUID.randomUUID();
        ScanResult newScanResult = new ScanResult(newUUID.toString(), scanResult.getCoordinates(), scanResult.getScanDay(), scanResult.getBirdId(), scanResult.getBirdSpecies(), scanResult.getBirdTraits());
        try {
        	ScanResult _scanResult = scanResultRepository.save(newScanResult);
            return new ResponseEntity<>(_scanResult, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("ERROR: Unable to save " + newUUID.toString() + ", " + newScanResult.toString());
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ScanResults/update/{id}")
    public ResponseEntity<ScanResult> updateScanResult(@PathVariable("id") UUID id, @RequestBody ScanResult scanResult) {
        Optional<ScanResult> scanResultData = scanResultRepository.findById(id);

        if (scanResultData.isPresent()) {
          ScanResult _scanResult = scanResultData.get();
          _scanResult.setCoordinates(scanResult.getCoordinates());
          _scanResult.setScanDay(scanResult.getScanDay());
          _scanResult.setBirdId(scanResult.getBirdId());
          _scanResult.setBirdSpecies(scanResult.getBirdSpecies());
          _scanResult.setBirdTraits(scanResult.getBirdTraits());
          return new ResponseEntity<>(scanResultRepository.save(_scanResult), HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/ScanResults/update/{coordinates}")
    public ResponseEntity<List<ScanResult>> updateScanResultsByCoordinates(@PathVariable("coordinates") String coordinates, @RequestBody ScanResult scanResult) {
    	List<ScanResult> scanResults = new ArrayList<ScanResult>();
        List<ScanResult> scanResultMatches = new ArrayList<ScanResult>();
        scanResultRepository.findAll().forEach(scanResults::add);
        for (ScanResult currentScan : scanResults) {
        	if (currentScan.getCoordinates().equals(coordinates)) {
        		scanResultMatches.add(currentScan);
        	}
        }

        if (scanResults.size() > 0) {
            for (ScanResult _scanResult : scanResultMatches) {
                _scanResult.setCoordinates(scanResult.getCoordinates());
                _scanResult.setScanDay(scanResult.getScanDay());
                _scanResult.setBirdId(scanResult.getBirdId());
                _scanResult.setBirdSpecies(scanResult.getBirdSpecies());
                _scanResult.setBirdTraits(scanResult.getBirdTraits());
            }          
          return new ResponseEntity<>(scanResultRepository.saveAll(scanResults), HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/ScanResults/delete/{id}")
    public ResponseEntity<HttpStatus> deleteScanResult(@PathVariable("id") UUID id) {
        try {
	        scanResultRepository.deleteById(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          } catch (Exception e) {
        	e.printStackTrace();
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }

    @DeleteMapping("/ScanResults/delete")
    public ResponseEntity<HttpStatus> deleteAllScanResults() {
        try {
            scanResultRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          } catch (Exception e) {
        	e.printStackTrace();
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }

    @GetMapping("/ScanResults/Coordinates/{coordinates}")
    public ResponseEntity<List<ScanResult>> findByCoordinates(@PathVariable("coordinates") String coordinates) {
    	List<ScanResult> scanResults = new ArrayList<ScanResult>();
        List<ScanResult> scanResultMatches = new ArrayList<ScanResult>();
        scanResultRepository.findAll().forEach(scanResults::add);
        for (ScanResult currentScan : scanResults) {
        	if (currentScan.getCoordinates().equals(coordinates)) {
        		scanResultMatches.add(currentScan);
        	}
        }

        if (scanResultMatches.size() > 0) {
            return new ResponseEntity<>(scanResultMatches, HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/ScanResults/Coordinates/{coordinates}/ScanDay/{scanDay}")
    public ResponseEntity<List<ScanResult>> findByCoordinatesAndScanDay(@PathVariable("coordinates") String coordinates, @PathVariable("scanDay") String scanDay) {
    	List<ScanResult> scanResults = new ArrayList<ScanResult>();
        List<ScanResult> scanResultMatches = new ArrayList<ScanResult>();
        scanResultRepository.findAll().forEach(scanResults::add);
        for (ScanResult currentScan : scanResults) {
        	if (currentScan.getCoordinates().equals(coordinates) && currentScan.getScanDay().toString().equals(scanDay)) {
        		scanResultMatches.add(currentScan);
        	}
        }

        if (scanResultMatches.size() > 0) {
            return new ResponseEntity<>(scanResultMatches, HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}