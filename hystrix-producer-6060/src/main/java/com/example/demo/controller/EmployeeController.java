package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;

@RestController
public class EmployeeController {
	
	@GetMapping("/employee/{id}")
	public Employee employee(HttpServletRequest request, @PathVariable("id") String id) {
		System.out.println("呼叫 employee() 方法");
		
		if(!id.equalsIgnoreCase("1")) {
			throw new RuntimeException("例外發生...");
		}
		
		Employee emp = new Employee();
		emp.setEmpId("1");
		emp.setName("John");
		emp.setDescription("Manager, port:" + request.getLocalPort());
		emp.setSalary(80000);
		return emp;
	}
	
}
