package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.AutomobileService;
import com.example.demo.entity.Automobile;
import com.example.demo.exception.ResourceNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("alapi/automobile")
public class AutomobileController {
	
	@Autowired
	AutomobileService AutoServ;
	
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
