package com.example.demo.controller;

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

import com.example.demo.Service.OrderService;
import com.example.demo.entity.Order;
import com.example.demo.exception.ResourceNotFoundException;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/ordapi/order") // http://localhost:9200/ordapi/order
public class OrderController {

	@Autowired
	OrderService orderSrv;

	// http://localhost:9200/ordapi/order/all
	@GetMapping("/all")
	public ResponseEntity<List<Order>> getAllOrders() {
		List<Order> ordList = orderSrv.getAllOrders();
		return ResponseEntity.ok().body(ordList);
	}

	// http://localhost:9200/ordapi/order/customer/12
	@GetMapping("/customer/{cusId}")
	public ResponseEntity<List<Order>> getOrderByCustomerId(@PathVariable Long cusId) throws ResourceNotFoundException {
		List<Order> ordList = orderSrv.getOrderByCustomerId(cusId);
		return ResponseEntity.ok().body(ordList);
	}

	// http://localhost:9200/ordapi/order/automobile/12
	@GetMapping("/automobile/{autoId}")
	public ResponseEntity<List<Order>> getOrderByAutomobileId(@PathVariable Long autoId)
			throws ResourceNotFoundException {
		List<Order> ordList = orderSrv.getOrderByAutomobileId(autoId);
		return ResponseEntity.ok().body(ordList);
	}

	// http://localhost:9200/ordapi/order/1
	@GetMapping("/{ordId}")
	public ResponseEntity<Order> getById(@PathVariable Long ordId) throws ResourceNotFoundException {
		Order ord = orderSrv.getById(ordId);
		return ResponseEntity.ok().body(ord);
	}

	// http://localhost:9200/ordapi/order
	@PostMapping
	public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
		Order ord = orderSrv.createOrder(order);
		return ResponseEntity.ok().body(ord);
	}

	// http://localhost:9200/ordapi/order/1
	@PutMapping("/{ordId}")
	public ResponseEntity<Order> UpdateOrder(@PathVariable Long ordId, @Valid @RequestBody Order order)
			throws ResourceNotFoundException {
		Order ord = orderSrv.UpdateOrder(ordId, order);
		return ResponseEntity.ok().body(ord);
	}

	// http://localhost:9200/ordapi/order/1
	@DeleteMapping("/{ordId}")
	public ResponseEntity<Boolean> deleteOrder(@PathVariable Long ordId) throws ResourceNotFoundException {
		boolean flag = orderSrv.deleteOrder(ordId);
		return ResponseEntity.ok().body(flag);
	}

}
