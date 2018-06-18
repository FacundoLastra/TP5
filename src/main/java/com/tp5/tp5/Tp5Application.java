package com.tp5.tp5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.models.Models")
public class Tp5Application {

	public static void main(String[] args) {
		SpringApplication.run(Tp5Application.class, args);
	}
}
