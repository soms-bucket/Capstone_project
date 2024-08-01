package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Order;
import com.example.demo.Service.AutomobileService;
import com.example.demo.entity.Automobile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.feign.OrderFeignClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/autoapi/automobile")
public class AutomobileController {
	
	@Autowired
	AutomobileService AutoServ;
	@Autowired
	private OrderFeignClient feignClient;
	
	// http://localhost:9100/autoapi/automobile/4/order
	@GetMapping("/{automobileId}/order")
	@CircuitBreaker(name = "", fallbackMethod = "getOrderFallback")
	@Retry(name = "", fallbackMethod = "getOrderFallback")
	public List<Order> getOrderByCustomer(@PathVariable Long automobileId) {
		return feignClient.getOrderByCustomerId(automobileId);
	}

	// fallback response
	public List<Order> getOrderFallback(Exception throwable) {
		System.out.println("Fallback method called due to: " + throwable.getMessage());
		// throwable.printStackTrace();
		return Stream.of(new Order(LocalDate.now(),4L,22L),
				new Order(LocalDate.now(), 154L, 63L),
				new Order(LocalDate.now(), 77L, 115L), 
				new Order(LocalDate.now(), 34L, 12L))
				.collect(Collectors.toList());
	}
	
	// http://localhost:9100/autoapi/automobile/all
	@GetMapping("/all")
	public ResponseEntity<List<Automobile>> getAllAutomobile(){
		List<Automobile> autoList =  AutoServ.getAllAutomobile();
		return ResponseEntity.ok().body(autoList);
	}
	
	@GetMapping("/year/{year}")
	public ResponseEntity<List<Automobile>> getAutomobileByYear(@PathVariable int year){
		List<Automobile> autoList =  AutoServ.getAutomobileByYear(year);
		return ResponseEntity.ok().body(autoList);
	}
	
	// http://localhost:9100/autoapi/automobile/manufacture/
	@GetMapping("/manufacture/{mId}")
	public ResponseEntity<List<Automobile>> getByManufacturerId(@PathVariable Long mId) throws ResourceNotFoundException {
		List<Automobile> automobileList = AutoServ.getByManufacturerId(mId);
		return ResponseEntity.ok().body(automobileList);
	}
	
	@GetMapping("/{aId}")
	public ResponseEntity<Automobile> getAutomobileById(@PathVariable Long aId) throws ResourceNotFoundException {
		Automobile automobile = AutoServ.getAutomobileById(aId);
		return ResponseEntity.ok().body(automobile);
	}
	
	@PostMapping
	public ResponseEntity<Automobile> createAutomobile(@Valid @RequestBody Automobile automobile){
		Automobile automobileNw = AutoServ.createAutomobile(automobile);
		return ResponseEntity.ok().body(automobileNw);
	}

	@PutMapping("/{aId}")
	public ResponseEntity<Automobile> 
		updateAutomobile(@PathVariable Long aId, @Valid @RequestBody Automobile automobile)
				 throws ResourceNotFoundException{
		Automobile automobileNw = AutoServ.updateAutomobile(aId, automobile);
		return ResponseEntity.ok().body(automobileNw);
		
	}
	@DeleteMapping("/{aId}")
	public ResponseEntity<Boolean> deleteAutomobileById(@PathVariable Long aId) throws ResourceNotFoundException{
		boolean flag = AutoServ.deleteById(aId);
		return ResponseEntity.ok().body(flag);
	}
	
}
