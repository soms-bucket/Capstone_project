package com.example.demo.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Automobile;

import feign.Headers;

@Headers("Content-Type: application/json")

@FeignClient(name = "automobile-service", url = "${AUTOMOBILE_SERVICE:http://localhost:9100}"/*, 
													fallback = PatientFeignFallback.class*/)
public interface AutomobileFeignClient {
	
	@GetMapping("/autoapi/automobile/manufacture/{mId}")
	List<Automobile> getOrderByCustomerId(@PathVariable("mId") Long mId);
	
}
