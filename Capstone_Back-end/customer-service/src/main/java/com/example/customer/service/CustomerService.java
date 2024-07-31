package com.example.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.customer.entity.Customer;

public interface CustomerService {

	List<Customer> getAllCustomerList();

	Optional<Customer> getCustomerById(Long id);

	Customer createCustomer(Customer newCustomer);

	ResponseEntity<Customer> updateCustomer(Long customerId, Customer newCustomer);

	void deleteCustomer(Long id);

}
