package com.example.demo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing // 啟動批次處理
public class SpringBatchBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchBasicApplication.class, args);
	}

}
