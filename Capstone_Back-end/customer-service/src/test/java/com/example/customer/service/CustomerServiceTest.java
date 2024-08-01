package com.example.customer.service;

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

import com.example.customer.entity.Customer;
import com.example.customer.repository.CustomerRepository;
import com.example.customer.service.impl.CustomerServiceImpl;


public class CustomerServiceTest {

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerServiceImpl customerService;

	private Customer customer1;
	private Customer customer2;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		customer1 = new Customer();
		customer1.setId(1L);
		customer1.setName("John Doe");
		customer1.setEmail("john.doe@example.com");
		customer1.setPhone("123456789");

		customer2 = new Customer();
		customer2.setId(2L);
		customer2.setName("Jane Doe");
		customer2.setEmail("jane.doe@example.com");
		customer2.setPhone("987654321");
	}

	@Test
	void testGetAllCustomerList() {
		List<Customer> customers = Arrays.asList(customer1, customer2);
		when(customerRepository.findAll()).thenReturn(customers);

		List<Customer> result = customerService.getAllCustomerList();

		assertEquals(2, result.size());
		assertEquals(customer1, result.get(0));
		assertEquals(customer2, result.get(1));
	}

	@Test
	void testGetCustomerById() {
		when(customerRepository.findById(1L)).thenReturn(Optional.of(customer1));

		Optional<Customer> result = customerService.getCustomerById(1L);

		assertTrue(result.isPresent());
		assertEquals(customer1, result.get());
	}

	@Test
	void testCreateCustomer() {
		when(customerRepository.save(any(Customer.class))).thenReturn(customer1);

		Customer result = customerService.createCustomer(customer1);

		assertNotNull(result);
		assertEquals(customer1, result);
	}

	@Test
	void testUpdateCustomer() {
		when(customerRepository.findById(1L)).thenReturn(Optional.of(customer1));
		when(customerRepository.save(any(Customer.class))).thenReturn(customer1);

		Customer updatedCustomer = new Customer();
		updatedCustomer.setName("Updated Name");
		updatedCustomer.setEmail("updated.email@example.com");
		updatedCustomer.setPhone("111111111");

		ResponseEntity<Customer> result = customerService.updateCustomer(1L, updatedCustomer);

		assertNotNull(result.getBody());
		assertEquals("Updated Name", result.getBody().getName());
		assertEquals("updated.email@example.com", result.getBody().getEmail());
		assertEquals("111111111", result.getBody().getPhone());
	}
	 @Test
	    void testDeleteCustomer() {
		 Long customerId = 1L;
		 when(customerRepository.existsById(customerId)).thenReturn(true);
		 doNothing().when(customerRepository).deleteById(customerId);
		 assertDoesNotThrow(() -> customerService.deleteCustomer(customerId));
		 }

}
