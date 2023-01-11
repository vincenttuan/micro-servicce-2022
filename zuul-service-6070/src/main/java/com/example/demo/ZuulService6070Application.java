package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.example.demo.filter.ErrorFilter;
import com.example.demo.filter.PostFilter;
import com.example.demo.filter.PreFilter;
import com.example.demo.filter.RouteFilter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration
@EnableZuulProxy
public class ZuulService6070Application {

	public static void main(String[] args) {
		SpringApplication.run(ZuulService6070Application.class, args);
	}
	
	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
	
	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}
	
	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}
	
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	
}
