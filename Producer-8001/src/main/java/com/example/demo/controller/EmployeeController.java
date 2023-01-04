package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;

@RestController
public class EmployeeController {
	
	@GetMapping("/employee")
	public Employee employee() {
		Employee emp = new Employee();
		emp.setEmpId("1");
		emp.setName("John");
		emp.setDescription("Manager");
		emp.setSalary(80000);
		return emp;
	}
	
}
