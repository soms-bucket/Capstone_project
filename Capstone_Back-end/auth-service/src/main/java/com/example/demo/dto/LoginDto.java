package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoginDto {
	@NotNull
	@NotBlank
	private String usernameOrEmail;
	@NotNull
	@NotBlank
	private String password;

	public LoginDto() {
		super();
	}

	public LoginDto(String usernameOrEmail, String password) {
		super();
		this.usernameOrEmail = usernameOrEmail;
		this.password = password;
	}

	public String getUsernameOrEmail() {
		return usernameOrEmail;
	}

	public void setUsernameOrEmail(String usernameOrEmail) {
		this.usernameOrEmail = usernameOrEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
