package com.example.demo.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.OrderRepository;
import com.example.demo.Service.OrderService;
import com.example.demo.entity.Order;
import com.example.demo.exception.ResourceNotFoundException;


@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderRepository orderRepo;

	@Override
	public List<Order> getAllOrders() {
		return orderRepo.findAll();
	}

	@Override
	public List<Order> getOrderByCustomerId(Long cusId) throws ResourceNotFoundException{
		List<Order> oList = orderRepo.findByCustomerId(cusId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id " + cusId));
		return oList;
	}
	
	@Override
	public List<Order> getOrderByAutomobileId(Long autoId) throws ResourceNotFoundException {
		List<Order> oList = orderRepo.findByAutomobileId(autoId)
				.orElseThrow(() -> new ResourceNotFoundException("Automobile not found for this id " + autoId));

		return oList;
	}

	@Override
	public Order getById(Long oId) throws ResourceNotFoundException {
		Order ord = orderRepo.findById(oId)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found for this id " + oId));

		return ord;
	}

	@Override
	public Order createOrder(Order order) {
		Order ord = orderRepo.save(order);
		return ord;
	}

	@Override
	public Order UpdateOrder(Long oId, Order nwOrder) throws ResourceNotFoundException {
		Order extOrder = orderRepo.findById(oId)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found for this id " + oId));

		
		extOrder.setDate(nwOrder.getDate());
		extOrder.setAutomobileId(nwOrder.getAutomobileId());
		extOrder.setCustomerId(nwOrder.getCustomerId());
		
		Order ord = orderRepo.save(extOrder);
		return ord;
	}

	@Override
	public boolean deleteOrder(Long oId) throws ResourceNotFoundException{
		orderRepo.findById(oId)
		.orElseThrow(() -> new ResourceNotFoundException("Order not found for this id " + oId));

		orderRepo.deleteById(oId);
		return true;
	}


}
