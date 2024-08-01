package com.example.customer.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.customer.entity.Order;

import feign.Headers;

@Headers("Content-Type: application/json")

@FeignClient(name = "order-service", url = "${ORDER_SERVICE:http://localhost:9200}"/*, 
													fallback = PatientFeignFallback.class*/)
public interface OrderFeignClient {
	// /ordapi/order/customer/12
	
	@GetMapping("/ordapi/order/customer/{customerId}")
	List<Order> getOrderByCustomerId(@PathVariable("customerId") Long customerId);
	

}
