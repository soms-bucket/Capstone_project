package com.example.inventory.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.inventory.entity.Inventory;
import com.example.inventory.exception.ResourceNotFoundException;
import com.example.inventory.repository.InventoryRepository;
import com.example.inventory.service.impl.InventoryServiceImpl;

public class InventoryServiceTest {

	@Mock
	private InventoryRepository inventoryRepository;

	@InjectMocks
	private InventoryServiceImpl inventoryService;

	private Inventory inventory1;
	private Inventory inventory2;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		inventory1 = new Inventory();
		inventory1.setId(1L);
		inventory1.setAutomobileId(101L);
		inventory1.setQuantity(50);

		inventory2 = new Inventory();
		inventory2.setId(2L);
		inventory2.setAutomobileId(102L);
		inventory2.setQuantity(75);
	}

	@Test
	void testGetAllInventory() {
		List<Inventory> inventories = Arrays.asList(inventory1, inventory2);
		when(inventoryRepository.findAll()).thenReturn(inventories);

		List<Inventory> result = inventoryService.getAllInventory();

		assertEquals(2, result.size());
		assertEquals(inventory1, result.get(0));
		assertEquals(inventory2, result.get(1));
	}

	@Test
	void testGetInventoryByAutomobileId() throws ResourceNotFoundException {
		when(inventoryRepository.findByAutomobileId(101L)).thenReturn(Optional.of(Arrays.asList(inventory1)));

		List<Inventory> result = inventoryService.getInventoryByAutomobileId(101L);

		assertEquals(1, result.size());
		assertEquals(inventory1, result.get(0));
	}

	@Test
	void testCreateInventory() {
		when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory1);

		Inventory result = inventoryService.createInventory(inventory1);

		assertNotNull(result);
		assertEquals(inventory1, result);
	}

	@Test
	void testUpdateInventory() throws ResourceNotFoundException {
		when(inventoryRepository.findById(1L)).thenReturn(Optional.of(inventory1));
		when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory1);

		Inventory updatedInventory = new Inventory();
		updatedInventory.setAutomobileId(103L);
		updatedInventory.setQuantity(100);

		ResponseEntity<Inventory> result = inventoryService.updateInventory(1L, updatedInventory);

		assertNotNull(result.getBody());
		assertEquals(103L, result.getBody().getAutomobileId());
		assertEquals(100, result.getBody().getQuantity());
	}

	@Test
	void testDeleteInventory() throws ResourceNotFoundException {
		Long inventoryId = 1L;
		when(inventoryRepository.findById(inventoryId)).thenReturn(Optional.of(inventory1));
		doNothing().when(inventoryRepository).deleteById(inventoryId);

		assertDoesNotThrow(() -> inventoryService.deleteInventory(inventoryId));
	}

	@Test
	void testGetTotalQuantityByAutomobileId() throws ResourceNotFoundException {
		when(inventoryRepository.findByAutomobileId(101L)).thenReturn(Optional.of(Arrays.asList(inventory1)));

		List<Inventory> result = inventoryService.getTotalQuantityByAutomobileId(101L);

		assertEquals(1, result.size());
		assertEquals(inventory1, result.get(0));
	}

	@Test
	void testGetInventoryById() throws ResourceNotFoundException {
		when(inventoryRepository.findById(1L)).thenReturn(Optional.of(inventory1));

		Inventory result = inventoryService.getInventoryById(1L);

		assertNotNull(result);
		assertEquals(inventory1, result);
	}
}
