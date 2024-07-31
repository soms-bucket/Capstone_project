package com.example.demo.Service;

import java.util.List;

import com.example.demo.entity.Automobile;
import com.example.demo.exception.ResourceNotFoundException;

public interface AutomobileService {
	List<Automobile> getAllAutomobile();
	List<Automobile> getAutomobileByYear(int year);
	List<Automobile> getByManufacturerId(Long mID) throws ResourceNotFoundException;
	Automobile getAutomobileById(Long aid) throws ResourceNotFoundException;
	Automobile createAutomobile(Automobile automobile);
	Automobile updateAutomobile(Long aId,Automobile nwautomobile) throws ResourceNotFoundException;
	boolean deleteById(Long aid) throws ResourceNotFoundException ;
	
}
