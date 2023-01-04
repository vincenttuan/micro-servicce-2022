package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "EMPLOYEE-PRODUCER")
public interface RemoteCallService {
	// 調用 EMPLOYEE-PRODUCER 的 /employee 服務
	@GetMapping(value = "/employee")
	public String employee();
}
