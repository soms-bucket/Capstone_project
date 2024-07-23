package com.example.inventory.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.inventory.entity.Inventory;
import com.example.inventory.exception.ResourceNotFoundException;

public interface InventoryService {

	List<Inventory> getAllInventory();

	List<Inventory> getInventoryByAutomobileId(Long automobileId) throws ResourceNotFoundException;

	Inventory createInventory(Inventory newInventory);

	ResponseEntity<Inventory> updateInventory(Long id, Inventory newInventory) throws ResourceNotFoundException;

	void deleteInventory(Long id) throws ResourceNotFoundException;

}
