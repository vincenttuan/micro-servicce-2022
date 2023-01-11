package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableAutoConfiguration
@EnableZuulProxy
public class ZuulService6070Application {

	public static void main(String[] args) {
		SpringApplication.run(ZuulService6070Application.class, args);
	}

}
