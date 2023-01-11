package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class SpringbootAdmin8080Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAdmin8080Application.class, args);
	}

}
