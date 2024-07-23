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
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Automobile")
public class Automobile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "make is missing")
	@NotNull (message = "make must not null")
    private String make;
	
	@NotNull (message = "price must not null")
    private Double price;
	
	@NotBlank(message = "model is missing")
	@NotNull (message = "model must not null")
	private String model;

	@NotNull (message = "year must not null")
	private int year;
	
	@NotNull (message = "manufacturer Id must not null")
	private Long  manufacturerId;	//  Foreign key
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "automobileId", referencedColumnName = "id")
	List<Inventory> InventoryList = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "automobileId", referencedColumnName = "id")
	List<Maintenance> MaintenanceList = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "automobileId", referencedColumnName = "id")
	List<Order> OrderList = new ArrayList<>();

	public Automobile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Automobile(String make, Double price, String model, int year, Long manufacturerId,
			List<Inventory> inventoryList, List<Maintenance> maintenanceList, List<Order> orderList) {
		super();
		this.make = make;
		this.price = price;
		this.model = model;
		this.year = year;
		this.manufacturerId = manufacturerId;
		InventoryList = inventoryList;
		MaintenanceList = maintenanceList;
		OrderList = orderList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Long getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public List<Inventory> getInventoryList() {
		return InventoryList;
	}

	public void setInventoryList(List<Inventory> inventoryList) {
		InventoryList = inventoryList;
	}

	public List<Maintenance> getMaintenanceList() {
		return MaintenanceList;
	}

	public void setMaintenanceList(List<Maintenance> maintenanceList) {
		MaintenanceList = maintenanceList;
	}

	public List<Order> getOrderList() {
		return OrderList;
	}

	public void setOrderList(List<Order> orderList) {
		OrderList = orderList;
	}
	
	
	
	

}
