package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	
	@GetMapping("/")
	public String index() {
		return "Welcome";
	}
	
	@GetMapping("/report")
	public String report() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return "Logged in: " + 
				authentication.getName() + ", " + 
				authentication.getDetails() + ", " + 
				authentication.getPrincipal();
	}
	
	@GetMapping("/logout_success")
	public  String logoutSuccess() {
		return "You logout success";
	}
	
}
