package com.example.demo.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@GetMapping("/")
	public String index() {
		return  "Welcome Api";
	}
	
	@GetMapping("/time")
	public String time() {
		return  "Time: " + new Date();
	}
	
}
