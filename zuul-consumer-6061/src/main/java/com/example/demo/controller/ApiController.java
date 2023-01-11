package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/*
 * 直連 producer:  http://192.168.30.252:6062/employee
 * 透過 zuul-service: http://192.168.30.252:6070/producer/employee
 * 透過 consumer: http://192.168.30.252:6061/employee
 *               路徑：consumer -> zuul-service -> producer
 * */

@RestController
public class ApiController {
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@RequestMapping(value = "/employee")
	public String employee() {
		List<ServiceInstance> instances = discoveryClient.getInstances("ZUUL-SERVICE");
		ServiceInstance serviceInstance = instances.get(0);
		String baseUrl = serviceInstance.getUri().toString();
		
		baseUrl = baseUrl + "/producer/employee";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response.getBody();
		
	}
	
	private HttpEntity<String> getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return  new HttpEntity<>(headers);
	}
	
}
