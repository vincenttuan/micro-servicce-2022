package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/")
	public String index() {
		return "<h1>Welcome !</h1>";
	}
	
	@GetMapping("/user")
	public String user() {
		return "<h1>Welcome User !</h1>";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "<h1>Welcome Admin !</h1>";
	}
	
	@GetMapping("/report")
	public String report() {
		return "<h1>Welcome Report !</h1>";
	}
	
	@GetMapping("/report/finance")
	public String reportFinance() {
		return "<h1>Welcome Report Finance !</h1>";
	}
	
	@GetMapping("/report/employee")
	public String reportEmployee() {
		return "<h1>Welcome Report Employee !</h1>";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			// 登出作業
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return  "redirect:/";
	}
	
}
