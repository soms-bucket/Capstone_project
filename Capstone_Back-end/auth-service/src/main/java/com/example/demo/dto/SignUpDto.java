package com.example.demo.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SignUpDto {
	@NotNull
    @NotBlank
    private String name;
    @NotNull
    private String username;
    @NotNull
    @Email
    private String email;
    @NotNull
    @NotBlank
    private String password;
    
    
	public SignUpDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SignUpDto(String name, String username, String email, String password) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
    
	
}
    