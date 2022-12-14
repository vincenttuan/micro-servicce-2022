package com.example.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * 了解 Job 與 Step 的操作
 * */
@Configuration
public class JobConfig_1_basic {
	// 透過 Job 任務來執行批次作業
	// 要注入創建一個 JobBuilderFactory 物件用來產生 Job
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	// Job 任務的執行必須由 Step 決定
	// 要注入創建一個 StepBuilderFactory 物件用來產生 Step
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job helloJob() {
		return jobBuilderFactory.get("HelloJob")
				.start(step1())
				.build();
	}
	
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("Step1")
				.tasklet((contribution, chunkContext) -> {
					System.out.println("Hello batch step1");
					return RepeatStatus.FINISHED;
				})
				.build();
	}
	
}
