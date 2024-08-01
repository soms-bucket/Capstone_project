package com.example.inventory.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.inventory.entity.Inventory;
import com.example.inventory.exception.ResourceNotFoundException;
import com.example.inventory.repository.InventoryRepository;
import com.example.inventory.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	@Override
	public List<Inventory> getAllInventory() {
		return inventoryRepository.findAll();
	}

	@Override
	public List<Inventory> getInventoryByAutomobileId(Long automobileId) throws ResourceNotFoundException {
		return inventoryRepository.findByAutomobileId(automobileId)
				.orElseThrow(() -> new ResourceNotFoundException("Automobile id not found:" + automobileId));
	}

	@Override
	public Inventory createInventory(Inventory newInventory) {
		return inventoryRepository.save(newInventory);
	}

	@Override
	public ResponseEntity<Inventory> updateInventory(Long id, Inventory newInventory) throws ResourceNotFoundException {
		Inventory existingInventory = inventoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Inventory Id not found: " + id));
		existingInventory.setAutomobileId(newInventory.getAutomobileId());
		existingInventory.setQuantity(newInventory.getQuantity());
		inventoryRepository.save(existingInventory);
		return ResponseEntity.ok(existingInventory);
	}

	@Override
	public void deleteInventory(Long id) throws ResourceNotFoundException {
		Inventory inventory = inventoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Inventory Id not found: " + id));
		inventoryRepository.deleteById(id);

	}

	@Override
	public List<Inventory> getTotalQuantityByAutomobileId(Long automobileId) throws ResourceNotFoundException {
		return inventoryRepository.findByAutomobileId(automobileId).orElseThrow(() -> new ResourceNotFoundException("AutoMobileId Not Found: "+automobileId));
       
	}

	@Override
	public Inventory getInventoryById(Long id) throws ResourceNotFoundException {
		return inventoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Inventory Not Found: "+id));
	}

}
