package com.example.demo.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.OrderRepository;
import com.example.demo.Service.OrderService;
import com.example.demo.entity.Order;


@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderRepository orderRepo;

	@Override
	public List<Order> getAllOrders() {
		return orderRepo.findAll();
	}

	@Override
	public List<Order> getOrderByCustomerId(Long cusId) {
		List<Order> oList = orderRepo.findByCustomerId(cusId);
		return oList;
	}

	@Override
	public Order getById(Long oId) {
		Order ord = orderRepo.findById(oId).get();
		return ord;
	}

	@Override
	public Order createOrder(Order order) {
		Order ord = orderRepo.save(order);
		return ord;
	}

	@Override
	public Order UpdateOrder(Long oId, Order nwOrder) {
		Order extOrder = orderRepo.findById(oId).get();
		
		extOrder.setDate(nwOrder.getDate());
		extOrder.setAutomobileId(nwOrder.getAutomobileId());
		extOrder.setCustomerId(nwOrder.getCustomerId());
		
		Order ord = orderRepo.save(extOrder);
		return ord;
	}

	@Override
	public boolean deleteOrder(Long oId) {
		orderRepo.deleteById(oId);
		return true;
	}

}
