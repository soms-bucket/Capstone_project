package com.example.customer.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.customer.entity.Customer;
import com.example.customer.repository.CustomerRepository;
import com.example.customer.service.CustomerService;

import jakarta.validation.Valid;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> getAllCustomerList() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public Optional<Customer> getCustomerById(Long id) {
		// TODO Auto-generated method stub
		return customerRepository.findById(id);
	}

	@Override
	public Customer createCustomer( Customer newCustomer) {
		return customerRepository.save(newCustomer);
	}

	@Override
	public ResponseEntity<Customer> updateCustomer(Long customerId,@Valid Customer newCustomer) {
		Optional<Customer> existingCustomer =customerRepository.findById(customerId);
		existingCustomer.get().setName(newCustomer.getName());
		existingCustomer.get().setEmail(newCustomer.getEmail());
		existingCustomer.get().setPhone(newCustomer.getPhone());
		customerRepository.save(existingCustomer.get());
		return ResponseEntity.ok(existingCustomer.get());
	}

	@Override
	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
		
	}

}
