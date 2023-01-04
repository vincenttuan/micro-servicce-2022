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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.service.RemoteCallService;

@RestController
public class ApiCotroller {
	
	@Autowired
	private RemoteCallService remoteCallService;
	
	@GetMapping("/employee")
	public String getEmployee() {
		String employee = remoteCallService.employee();
		System.out.println(employee);
		return employee;
	}
	
	/*
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@GetMapping("/employee")
	public String getEmployee() {
		List<ServiceInstance> instances = discoveryClient.getInstances("EMPLOYEE-PRODUCER");
		ServiceInstance serviceInstance = instances.get(0);
		String baseURL = serviceInstance.getUri().toString();
		String fullURL = baseURL + "/employee";
		System.out.println("baseURL = " + baseURL);
		System.out.println("fullURL = " + fullURL);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		response = restTemplate.exchange(fullURL, HttpMethod.GET, getHeaders(), String.class);
		return response == null ? null : response.getBody();
	}
	
	private HttpEntity<String> getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return  new HttpEntity<>(headers);
	}
	*/
	
}
