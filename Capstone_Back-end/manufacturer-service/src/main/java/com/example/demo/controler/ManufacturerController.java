package com.example.demo.controler;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.entity.Automobile;
import com.example.demo.entity.Manufacturer;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.feign.AutomobileFeignClient;
import com.example.demo.service.ManufacturerService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid; // Optional for validation
 
@CrossOrigin
@RestController  // http://localhost:9192/api/manufacturers
@RequestMapping("/api/manufacturers")
public class ManufacturerController {


  @Autowired
  ManufacturerService manufacturerService;
  @Autowired
  private AutomobileFeignClient feignClient;
  
  // http://localhost:9192/api/manufacturers/12/automobile
  @GetMapping("/{mId}/automobile")
	@CircuitBreaker(name = "", fallbackMethod = "getOrderFallback")
	@Retry(name = "", fallbackMethod = "getOrderFallback")
	public List<Automobile> getOrderByCustomer(@PathVariable Long mId) {
		return feignClient.getOrderByCustomerId(mId);
	}

	// fallback response
	public List<Automobile> getOrderFallback(Exception throwable) {
		System.out.println("Fallback method called due to: " + throwable.getMessage());
		// throwable.printStackTrace();
		return Stream.of(new Automobile("bmw", 8881.0, "M-1", 2008, 8L),
				new Automobile("Pro", 8841.0, "DZ2", 2000, 1L),
				new Automobile("bmw", 9866.0, "mod/sz", 1994, 91L))
				.collect(Collectors.toList());
	}
  
  
  
  
//http://localhost:9192/api/manufacturers
  // GET - Get all manufacturers
  @GetMapping
  public List<Manufacturer> getAllManufacturers() {
    return manufacturerService.getAllManufacturers();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Manufacturer> GetManufacturerbyID(@PathVariable Long id){
	  Manufacturer createdManufacturer = manufacturerService.getManufacturerById(id).get();
	  return ResponseEntity.ok(createdManufacturer);
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