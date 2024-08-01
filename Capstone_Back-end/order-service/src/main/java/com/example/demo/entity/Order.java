package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private LocalDate date = LocalDate.now();
	
	@NotNull
	private Long customerId; // foreign key
	@NotNull
	private Long automobileId; // foreign key

	public Order() {
		super();
	}

	public Order(LocalDate date, Long customerId, Long automobileId) {
		super();
		this.date = date;
		this.customerId = customerId;
		this.automobileId = automobileId;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getAutomobileId() {
		return automobileId;
	}

	public void setAutomobileId(Long automobileId) {
		this.automobileId = automobileId;
	}

}
