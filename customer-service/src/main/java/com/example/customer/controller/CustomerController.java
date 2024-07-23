package com.example.customer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.customer.entity.Customer;
import com.example.customer.exception.ResourceNotFoundException;
import com.example.customer.service.CustomerService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")

public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/all")
	List<Customer> getAllCustomers(){
		return customerService.getAllCustomerList();
	}
	
	@GetMapping("/{id}")
	Optional<Customer> getCustomerById(@PathVariable Long id) throws ResourceNotFoundException{
		Optional<Customer> customer = customerService.getCustomerById(id);
		customer.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id: "+id));
		return customer;
		
	}
	
	@PostMapping("/create")
	public Customer createCustomer( @RequestBody Customer newCustomer) {
		return customerService.createCustomer(newCustomer);
		
	}
	
	@PutMapping("/{customerId}")
	ResponseEntity<Customer> updateCustomer(@PathVariable(value="customerId") Long customerId, @RequestBody Customer newCustomer ){
		return customerService.updateCustomer(customerId, newCustomer);
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
	}

}
