package com.example.demo.Service;

import java.util.List;

import com.example.demo.entity.Order;

public interface OrderService {
	
	List<Order> getAllOrders();
	List<Order> getOrderByCustomerId(Long cusId);
	Order getById(Long oId);
	Order createOrder(Order order);
	Order UpdateOrder(Long oId, Order nwOrder);
	boolean deleteOrder(Long oId);  
	

}
