package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	
	@GetMapping("/")
	public String index() {
		return "Index page";
	}
	
	@GetMapping("/callback")
	public String callback()  {
		return "Callback page";
	}
	
}
