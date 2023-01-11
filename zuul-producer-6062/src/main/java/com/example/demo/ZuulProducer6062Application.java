package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ZuulProducer6062Application {

	public static void main(String[] args) {
		SpringApplication.run(ZuulProducer6062Application.class, args);
	}

}
