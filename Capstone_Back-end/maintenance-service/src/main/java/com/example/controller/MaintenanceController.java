package com.example.controller;

import com.example.entity.Maintenance;
import com.example.exception.ResourceNotFoundException;
import com.example.service.MaintenanceService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/maintenance")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    // http://localhost:9300/api/maintenance/automobile/{automobileId}
    @GetMapping("/automobile/{automobileId}")
    public ResponseEntity<List<Maintenance>> getMaintenanceByAutomobileId(@PathVariable("automobileId") Long automobileId) {
        List<Maintenance> maintenances = maintenanceService.getMaintenanceByAutomobileId(automobileId);
        return new ResponseEntity<>(maintenances, HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<List<Maintenance>>  getAll(){
    	List<Maintenance> mList = maintenanceService.getMaintenance();
    	return new ResponseEntity<>(mList, HttpStatus.OK);
    }

    // http://localhost:9300/api/maintenance/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> getMaintenanceById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Maintenance maintenance = maintenanceService.getMaintenanceById(id);
        return new ResponseEntity<>(maintenance, HttpStatus.OK);
    }
    
    // http://localhost:9300/api/maintenance
    @PostMapping
    public ResponseEntity<Maintenance> createMaintenance(@Valid @RequestBody Maintenance maintenance) {
        Maintenance createdMaintenance = maintenanceService.createMaintenance(maintenance);
        return new ResponseEntity<>(createdMaintenance, HttpStatus.CREATED);
    }

   // http://localhost:9300/api/maintenance/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> 
    updateMaintenance(@PathVariable("id") Long id,@Valid @RequestBody Maintenance maintenance)
    		throws ResourceNotFoundException{
        Maintenance updatedMaintenance = maintenanceService.updateMaintenance(id, maintenance);
        return new ResponseEntity<>(updatedMaintenance, HttpStatus.OK);
    }
 
    //http://localhost:9300/api/maintenance/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable("id") Long id) {
        maintenanceService.deleteMaintenance(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}




//package com.example.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import com.example.entity.Maintenance;
//import com.example.service.MaintenanceService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/maintenance")
//public class MaintenanceController {
//
//    @Autowired
//    private MaintenanceService maintenanceService;
//
//    @GetMapping("/automobile/{automobileId}")
//    public List<Maintenance> getMaintenanceByAutomobileId(@PathVariable Long automobileId) {
//        return maintenanceService.getMaintenanceByAutomobileId(automobileId);
//    }
//
//    @GetMapping("/{id}")
//    public Maintenance getMaintenanceById(@PathVariable Long id) {
//        return maintenanceService.getMaintenanceById(id);
//    }
//
//    @PostMapping
//    public Maintenance createMaintenance(@RequestBody Maintenance maintenance) {
//        return maintenanceService.createMaintenance(maintenance);
//    }
//
//    @PutMapping("/{id}")
//    public Maintenance updateMaintenance(@PathVariable Long id, @RequestBody Maintenance maintenance) {
//        return maintenanceService.updateMaintenance(id, maintenance);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteMaintenance(@PathVariable Long id) {
//        maintenanceService.deleteMaintenance(id);
//    }
//}
