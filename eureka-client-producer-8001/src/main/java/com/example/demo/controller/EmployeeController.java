package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;

@RestController
public class EmployeeController {
	
	@GetMapping("/employee")
	public Employee employee(HttpServletRequest request) {
		Employee emp = new Employee();
		emp.setEmpId("1");
		emp.setName("John");
		emp.setDescription("Manager, port:" + request.getLocalPort());
		emp.setSalary(80000);
		return emp;
	}
	
}
