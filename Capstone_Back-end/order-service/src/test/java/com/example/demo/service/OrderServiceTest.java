package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.Repository.OrderRepository;
import com.example.demo.Service.Impl.OrderServiceImpl;
import com.example.demo.entity.Order;
import com.example.demo.exception.ResourceNotFoundException;

public class OrderServiceTest {

	@Mock
	private OrderRepository orderRepository;

	@InjectMocks
	private OrderServiceImpl orderService;

	private Order order1;
	private Order order2;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		order1 = new Order();
		order1.setId(1L);
		order1.setDate(LocalDate.of(2023, 7, 27));
		order1.setAutomobileId(101L);
		order1.setCustomerId(201L);

		order2 = new Order();
		order2.setId(2L);
		order2.setDate(LocalDate.of(2023, 7, 28));
		order2.setAutomobileId(102L);
		order2.setCustomerId(202L);
	}

	@Test
	void testGetAllOrders() {
		List<Order> orders = Arrays.asList(order1, order2);
		when(orderRepository.findAll()).thenReturn(orders);

		List<Order> result = orderService.getAllOrders();

		assertEquals(2, result.size());
		assertEquals(order1, result.get(0));
		assertEquals(order2, result.get(1));
	}

	@Test
	void testGetOrderByCustomerId() throws ResourceNotFoundException {
		List<Order> orders = Arrays.asList(order1);
		when(orderRepository.findByCustomerId(201L)).thenReturn(Optional.of(orders));

		List<Order> result = orderService.getOrderByCustomerId(201L);

		assertEquals(1, result.size());
		assertEquals(order1, result.get(0));
	}

	@Test
	void testGetById() throws ResourceNotFoundException {
		when(orderRepository.findById(1L)).thenReturn(Optional.of(order1));

		Order result = orderService.getById(1L);

		assertNotNull(result);
		assertEquals(order1, result);
	}

	@Test
	void testCreateOrder() {
		when(orderRepository.save(any(Order.class))).thenReturn(order1);

		Order result = orderService.createOrder(order1);

		assertNotNull(result);
		assertEquals(order1, result);
	}

	@Test
	void testUpdateOrder() throws ResourceNotFoundException {
		when(orderRepository.findById(1L)).thenReturn(Optional.of(order1));
		when(orderRepository.save(any(Order.class))).thenReturn(order1);

		Order updatedOrder = new Order();
		updatedOrder.setDate(LocalDate.of(2023, 8, 1));
		updatedOrder.setAutomobileId(103L);
		updatedOrder.setCustomerId(203L);

		Order result = orderService.UpdateOrder(1L, updatedOrder);

		assertNotNull(result);
		assertEquals(LocalDate.of(2023, 8, 1), result.getDate());
		assertEquals(103L, result.getAutomobileId());
		assertEquals(203L, result.getCustomerId());
	}

	@Test
	void testDeleteOrder() throws ResourceNotFoundException {
		Long orderId = 1L;
		doNothing().when(orderRepository).deleteById(orderId);

		//assertDoesNotThrow(() -> orderService.deleteOrder(orderId));
	}
}
