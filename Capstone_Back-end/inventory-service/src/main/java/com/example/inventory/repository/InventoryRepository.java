package com.example.inventory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.inventory.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	//Custom Query
	Optional<List<Inventory>> findByAutomobileId(Long automobileId);

}
