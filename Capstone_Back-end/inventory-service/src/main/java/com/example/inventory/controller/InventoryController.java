package com.example.inventory.controller;

import java.util.List;

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

import com.example.inventory.entity.Inventory;
import com.example.inventory.exception.ResourceNotFoundException;
import com.example.inventory.repository.InventoryRepository;
import com.example.inventory.service.InventoryService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/inventories")
public class InventoryController {
	
	//ForSwagger
	// http://localhost:9091/swagger-ui/index.html
	
	@Autowired
	private InventoryService inventoryService;
	
	// http://localhost:9091/api/inventories
	@GetMapping
	public List<Inventory> getAllInventory(){
		return inventoryService.getAllInventory();
	}
	
	// http://localhost:9091/api/inventories/automobile/{automobileId}
	@GetMapping("/automobile/{automobileId}")
    public List<Inventory> getInventoryByAutomobileId(@PathVariable Long automobileId)throws ResourceNotFoundException {
		return inventoryService.getInventoryByAutomobileId(automobileId);
		
	}
	
	// http://localhost:9091/api/inventories/{id}
	@GetMapping("/{id}")
	public Inventory getInventoryById(@PathVariable Long id) throws ResourceNotFoundException {
		return inventoryService.getInventoryById(id);
	}
	
	// http://localhost:9091/api/inventories
	@PostMapping
	public Inventory createInventory(@Valid @RequestBody Inventory newInventory) {
		return inventoryService.createInventory(newInventory);
	}
	
	// http://localhost:9091/api/inventories/{id}
	@PutMapping("/{id}")
	ResponseEntity<Inventory> updateInventory(@PathVariable(value="id") Long id,@Valid @RequestBody Inventory newInventory ) throws ResourceNotFoundException{
		return inventoryService.updateInventory(id, newInventory);
		
	}
	
	// http://localhost:9091/api/inventories/{id}
	@DeleteMapping("/{id}")
	public void deleteInventory(@PathVariable Long id) throws ResourceNotFoundException {
		inventoryService.deleteInventory(id);
	}
	
	// http://localhost:9091/api/inventories/quantity/{automobileId}
	 @GetMapping("/quantity/{automobileId}")
	    public List<Inventory> getTotalQuantityByAutomobileId(@PathVariable Long automobileId) throws ResourceNotFoundException {
	        return inventoryService.getTotalQuantityByAutomobileId(automobileId);
	    }

}
