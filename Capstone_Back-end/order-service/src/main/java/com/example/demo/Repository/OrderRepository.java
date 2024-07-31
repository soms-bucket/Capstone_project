package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	Optional<List<Order>> findByCustomerId(Long customerId);
	Optional<List<Order>> findByAutomobileId(Long automobileId);

}
