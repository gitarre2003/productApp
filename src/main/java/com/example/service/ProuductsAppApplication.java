package com.example.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ProuductsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProuductsAppApplication.class, args);
	}

}
