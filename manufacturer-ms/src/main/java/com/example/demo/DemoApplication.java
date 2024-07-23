package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	
	// http://localhost:9193/swagger-ui/index.html
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}