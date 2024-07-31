package com.example.customer.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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

import com.example.customer.entity.Customer;
import com.example.customer.entity.Order;
import com.example.customer.exception.ResourceNotFoundException;
import com.example.customer.feign.OrderFeignClient;
import com.example.customer.service.CustomerService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/customers")

public class CustomerController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderFeignClient orderClient;

	private static final String USER_SERVICE = "cusService";

	@GetMapping("/{customerId}/order")
	@CircuitBreaker(name = USER_SERVICE, fallbackMethod = "getOrderFallback")
	@Retry(name = USER_SERVICE, fallbackMethod = "getOrderFallback")
	public List<Order> getOrderByCustomer(@PathVariable Long customerId) {
		return orderClient.getOrderByCustomerId(customerId);
	}

	// fallback response
	public List<Order> getOrderFallback(Exception throwable) {
		System.out.println("Fallback method called due to: " + throwable.getMessage());
		// throwable.printStackTrace();
		return Stream.of(new Order(LocalDate.now(),45L,89L),
				new Order(LocalDate.now(), 78L, 56L),
				new Order(LocalDate.now(), 88L, 98L), 
				new Order(LocalDate.now(), 44L, 23L))
				.collect(Collectors.toList());

	}

	// http://localhost:9090/api/customers
	@GetMapping("/all")
	List<Customer> getAllCustomers() {
		return customerService.getAllCustomerList();
	}

	// http://localhost:9090/api/customers/id
	@GetMapping("/{id}")
	Optional<Customer> getCustomerById(@PathVariable Long id) throws ResourceNotFoundException {
		Optional<Customer> customer = customerService.getCustomerById(id);
		customer.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id: " + id));
		return customer;

	}

	// http://localhost:9090/api/customers/
	@PostMapping("/")
	public Customer createCustomer(@Valid @RequestBody Customer newCustomer) {
		return customerService.createCustomer(newCustomer);

	}

	// http://localhost:9090/api/customers/id
	@PutMapping("/{customerId}")
	ResponseEntity<Customer> updateCustomer(@PathVariable(value = "customerId") Long customerId,
			@Valid @RequestBody Customer newCustomer) {
		
		return customerService.updateCustomer(customerId, newCustomer);

	}

	// http://localhost:9090/api/customers/id
	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
	}

}
