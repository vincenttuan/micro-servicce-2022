package com.example.demo.service;

import java.util.Random;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

	@Override
	@Scheduled(fixedRate = 3000) // 每隔 3 秒鐘檢查一次健康狀況, 記得要配置 @EnableScheduling
	public Health health() {
		int x = new Random().nextInt(10);
		System.out.print("x: " + x + ", health: ");
		if(x % 2 == 0) {
			System.out.println("UP");
			return Health.up().build();
		}
		System.out.println("DOWN");
		return Health.down().build();
	}

}
