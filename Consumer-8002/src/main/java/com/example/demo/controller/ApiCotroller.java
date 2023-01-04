package com.example.demo.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ApiCotroller {
	
	@GetMapping("/employee")
	public String getEmployee() {
		String baseURL = "http://localhost:8001";
		String servicePath = "/employee";
		String fullURL = baseURL + servicePath;
		
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
	
}
