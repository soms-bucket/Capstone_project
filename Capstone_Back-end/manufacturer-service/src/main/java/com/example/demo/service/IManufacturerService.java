package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Manufacturer;

import jakarta.validation.Valid;

public interface IManufacturerService {

	  List<Manufacturer> getAllManufacturers();

	  Optional<Manufacturer> getManufacturerById(Long id);

	  Manufacturer createManufacturer(Manufacturer manufacturer);

	  Manufacturer updateManufacturer(Manufacturer manufacturer);

	  void deleteManufacturer(Long id);
	}