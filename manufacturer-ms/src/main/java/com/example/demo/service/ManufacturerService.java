package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.Manufacturer;
import com.example.demo.repository.ManufacturerRepository;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerService implements IManufacturerService {

	// private final ManufacturerRepository manufacturerRepository;

	@Autowired
	ManufacturerRepository manufacturerRepository;

	@Override
	public List<Manufacturer> getAllManufacturers() {
		return manufacturerRepository.findAll();
	}

	@Override
	public Optional<Manufacturer> getManufacturerById(Long id) {
		return manufacturerRepository.findById(id);
	}

	@Override
	public Manufacturer createManufacturer(Manufacturer manufacturer) {
		return manufacturerRepository.save(manufacturer);
	}

	@Override
	public Manufacturer updateManufacturer(Manufacturer manufacturer) {
		return manufacturerRepository.save(manufacturer);
	}

	@Override
	public void deleteManufacturer(Long id) {
		manufacturerRepository.deleteById(id);
	}
}