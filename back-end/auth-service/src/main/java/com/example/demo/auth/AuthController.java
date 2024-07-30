package com.example.demo.auth;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.UserRepository;

import jakarta.validation.Valid;

import com.example.demo.authentication.User;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.SignUpDto;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	
	// http://localhost:9400/api/auth/signin
	@PostMapping("/signin")
	public ResponseEntity<Boolean> authenticateUser(@Valid @RequestBody LoginDto loginDto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	// http://localhost:9400/api/auth/signup
	@PostMapping("/signup")
	public ResponseEntity<HashMap<String,String>> registerUser(@Valid @RequestBody SignUpDto signUpDto) {
		HashMap<String,String> map = new HashMap<>();
		// add check for username exists in a DB
		if (userRepository.existsByUsername(signUpDto.getUsername())) {
			map.put("state","Username is already taken!");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}

		// add check for email exists in DB
		if (userRepository.existsByEmail(signUpDto.getEmail())) {
			map.put("state","Email is already taken!");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}

		// create user object
		User user = new User();
		user.setName(signUpDto.getName());
		user.setUsername(signUpDto.getUsername());
		user.setEmail(signUpDto.getEmail());
		user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

		userRepository.save(user);
		map.put("state","User registered successfully");
		return new ResponseEntity<>(map, HttpStatus.OK);

	}

}