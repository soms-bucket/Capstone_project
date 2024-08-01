package com.example.demo.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Order;

import feign.Headers;

@Headers("Content-Type: application/json")

@FeignClient(name = "order-service", url = "${ORDER_SERVICE:http://localhost:9200}"/*, 
													fallback = PatientFeignFallback.class*/)
public interface OrderFeignClient {
	
	@GetMapping("/ordapi/order/automobile/{autoId}")
	List<Order> getOrderByCustomerId(@PathVariable("autoId") Long autoId);
	

}
