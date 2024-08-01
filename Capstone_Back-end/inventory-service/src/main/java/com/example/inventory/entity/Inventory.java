package com.example.inventory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "inventory")
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message="Automobile ID cannot be null")
	private Long automobileId;
	
	@NotNull
	@Min(value = 0, message = "Quantity must be zero or positive")
	private int quantity;

	public Inventory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Inventory(Long id, Long automobileId, int quantity) {
		super();
		this.id = id;
		this.automobileId = automobileId;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAutomobileId() {
		return automobileId;
	}

	public void setAutomobileId(Long automobileId) {
		this.automobileId = automobileId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
