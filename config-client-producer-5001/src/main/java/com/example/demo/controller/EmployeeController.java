package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;

@RestController
public class EmployeeController {
	
	@Value("${message: None}")
	private String message;
	
	@GetMapping("/employee")
	public Employee employee() {
		Employee emp = new Employee();
		emp.setEmpId("1");
		emp.setName("John");
		emp.setDescription("message: " + message);
		emp.setSalary(80000);
		return emp;
	}
	
}
