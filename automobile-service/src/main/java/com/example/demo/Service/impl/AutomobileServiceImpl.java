package com.example.demo.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.AutomobileRepository;
import com.example.demo.Service.AutomobileService;
import com.example.demo.entity.Automobile;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class AutomobileServiceImpl implements AutomobileService {

	@Autowired
	AutomobileRepository automobileRepo;

	@Override
	public List<Automobile> getAllAutomobile() {
		return automobileRepo.findAll();
	}

	@Override
	public List<Automobile> getAutomobileByYear(int year) {
		return automobileRepo.findByYear(year);
	}

	@Override
	public Automobile getAutomobileById(Long aId) throws ResourceNotFoundException {
		Automobile automobile = automobileRepo.findById(aId)
				.orElseThrow(() -> new ResourceNotFoundException("Automobile not found for this id " + aId));
		return automobile;
	}

	@Override
	public Automobile createAutomobile(Automobile automobile) {
		Automobile automobileC = automobileRepo.save(automobile);
		return automobileC;
	}

	@Override
	public Automobile updateAutomobile(Long aId, Automobile nwautomobile) 
												throws ResourceNotFoundException {
		if (!automobileRepo.existsById(aId)) {
			throw new ResourceNotFoundException("Automobile not found for this id " + aId);
		}
		nwautomobile.setId(aId); // Ensure the ID is set correctly for the update
		return automobileRepo.save(nwautomobile);
	}

	@Override
	public boolean deleteById(Long aId) throws ResourceNotFoundException {
		automobileRepo.findById(aId)
				.orElseThrow(() -> new ResourceNotFoundException("Automobile not found for this id " + aId));
		automobileRepo.deleteById(aId);
		return true;
	}

}
