package com.example.customer.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.*;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Name is mandatory")
	private String name;

	@Email(message = "Email should be valid")
	@NotBlank(message = "Email is mandatory")
	private String email;

	@Pattern(regexp = "^[0-9]{10}$", message = "Phone number is invalid,Phone number must be exactly 10 digits")
	@NotBlank(message = "Phone number is mandatory")
	private String phone;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customerId", referencedColumnName = "id")
	private List<Order> orders = new ArrayList<>();

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String name, String email, String phone, List<Order> orders) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.orders = orders;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
