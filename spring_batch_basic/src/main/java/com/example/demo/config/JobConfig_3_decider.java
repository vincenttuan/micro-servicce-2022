package com.example.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 透過from(...).on(xxx).to(...) 來進行簡單決策
// on("COMPLETED")、on("FAILED")
// 另外可以透過決策器 JobExecutionDecider 來進行較為複雜的決策
// docs: https://docs.spring.io/spring-batch/docs/current/reference/html/step.html#programmaticFlowDecisions
@Configuration
public class JobConfig_3_decider {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("Step1")
				.tasklet((contribution, chunkContext) -> {
					System.out.println("Hello step1");
					return  RepeatStatus.FINISHED;
				})
				.allowStartIfComplete(true)
				.build();
	}
	
	@Bean
	public Step step2() {
		return stepBuilderFactory.get("Step2")
				.tasklet((contribution, chunkContext) -> {
					System.out.println("Hello step2");
					System.out.println(10/0);  // 模擬發生錯誤
					return  RepeatStatus.FINISHED;
				})
				.allowStartIfComplete(true)
				.build();
	}
	
	@Bean
	public Step step3() {
		return stepBuilderFactory.get("Step3")
				.tasklet((contribution, chunkContext) -> {
					System.out.println("Hello step3");
					return  RepeatStatus.FINISHED;
				})
				.allowStartIfComplete(true)
				.build();
	}
	
	@Bean
	public Job deciderJobDemo() {
		/*
		return jobBuilderFactory.get("DeciderJobDemo")
				.start(step1())
				.next(step2())
				.next(step3())
				.build();
		*/		
		// 透過 on(在什麼情況下).to(做什麼事)
		return jobBuilderFactory.get("DeciderJobDemo")
				.start(step1()).on("COMPLETED").to(step2())
				.from(step2()).on("FAILED").to(step3())
				.from(step3()).end()
				.build();
	}
	
	
}
