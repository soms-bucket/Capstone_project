package com.example.demo.controler;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Manufacturer;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.ManufacturerService;

import jakarta.validation.Valid; // Optional for validation
 
@RestController  // http://localhost:9192/api/
@RequestMapping("/api/manufacturers")
public class ManufacturerController {


  @Autowired
  ManufacturerService manufacturerService;
//http://localhost:9192/api/manufacturer
  // GET - Get all manufacturers
  @GetMapping
  public List<Manufacturer> getAllManufacturers() {
    return manufacturerService.getAllManufacturers();
  }

//http://localhost:9192/api/manufacturer
  // POST - Create a new manufacturer
  @PostMapping
  public ResponseEntity<Manufacturer> createManufacturer(@Valid @RequestBody Manufacturer manufacturer) {
    Manufacturer createdManufacturer = manufacturerService.createManufacturer(manufacturer);
    return ResponseEntity.ok(createdManufacturer);
  }
  
//http://localhost:9192/api/manufacturer/(id)
  // PUT - Update an existing manufacturer
  @PutMapping("/{id}")
  public ResponseEntity<Manufacturer> updateManufacturer(@PathVariable Long id, @Valid @RequestBody Manufacturer manufacturer) {
    Manufacturer existingManufacturer = manufacturerService.getManufacturerById(id).get();
    if (existingManufacturer == null) {
      return ResponseEntity.notFound().build();
    }
    manufacturer.setId(id); // Ensure the provided manufacturer object has the correct ID
    Manufacturer savedManufacturer = manufacturerService.updateManufacturer(manufacturer);
    return ResponseEntity.ok(savedManufacturer);
  }

//http://localhost:9192/api/manufacturer/(id)
  // DELETE - Delete a manufacturer
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteManufacturer(@PathVariable Long id) {
    manufacturerService.deleteManufacturer(id);
    return ResponseEntity.noContent().build();
  }
}