package com.example.inventory.controller;

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

import com.example.inventory.entity.Inventory;
import com.example.inventory.exception.ResourceNotFoundException;
import com.example.inventory.service.InventoryService;


@RestController
@RequestMapping("/api/inventories")
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@GetMapping
	public List<Inventory> getAllInventory(){
		return inventoryService.getAllInventory();
	}
	
	@GetMapping("/{automobileId}")
    public List<Inventory> getInventoryByAutomobileId(@PathVariable Long automobileId)throws ResourceNotFoundException {
		return inventoryService.getInventoryByAutomobileId(automobileId);
		
	}
	
	@PostMapping
	public Inventory createInventory(@RequestBody Inventory newInventory) {
		return inventoryService.createInventory(newInventory);
	}
	
	@PutMapping("/{id}")
	ResponseEntity<Inventory> updateInventory(@PathVariable(value="id") Long id, @RequestBody Inventory newInventory ) throws ResourceNotFoundException{
		return inventoryService.updateInventory(id, newInventory);
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteInventory(@PathVariable Long id) throws ResourceNotFoundException {
		inventoryService.deleteInventory(id);
	}

}
