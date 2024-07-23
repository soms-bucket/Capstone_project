package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "manufacturer")
public class Manufacturer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Manufacturer name is required")
	private String name;
	
	@NotBlank(message = "Manufacturer country is required")
	private String country;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "manufacturerId", referencedColumnName = "id")
	List<Automobile> automobileList = new ArrayList<>();
	
	public Manufacturer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Manufacturer(String name, String country, List<Automobile> automobileList) {
		super();
		this.name = name;
		this.country = country;
		this.automobileList = automobileList;
	}



	public List<Automobile> getAutomobileList() {
		return automobileList;
	}

	public void setAutomobileList(List<Automobile> automobileList) {
		this.automobileList = automobileList;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
