package com.example.demo.Service;

import java.util.List;

import com.example.demo.entity.Order;
import com.example.demo.exception.ResourceNotFoundException;

public interface OrderService {
	
	List<Order> getAllOrders();
	List<Order> getOrderByCustomerId(Long cusId) throws ResourceNotFoundException;
	List<Order> getOrderByAutomobileId(Long autoId) throws ResourceNotFoundException;
	Order getById(Long oId) throws ResourceNotFoundException;
	Order createOrder(Order order);
	Order UpdateOrder(Long oId, Order nwOrder) throws ResourceNotFoundException;
	boolean deleteOrder(Long oId) throws ResourceNotFoundException;  
	

}
