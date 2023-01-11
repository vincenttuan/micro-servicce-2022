package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix  // 啟動 Hystrix 機制
public class HystrixProducer6060Application {

	public static void main(String[] args) {
		SpringApplication.run(HystrixProducer6060Application.class, args);
	}

}
