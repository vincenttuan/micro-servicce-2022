package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;

@RestController
public class EmployeeController {
	
	@Value("${message: None}") // 透過公有配置資訊取得
	private String message;
	
	@Value("${secure_message: None}") // 透過專屬（私有）配置資訊取得
	private String secureMessage;
	
	@GetMapping("/employee")
	public Employee employee() {
		Employee emp = new Employee();
		emp.setEmpId("1");
		emp.setName("John");
		emp.setDescription("message: " + message + ", secureMessage: " + secureMessage);
		emp.setSalary(80000);
		return emp;
	}
	
}
