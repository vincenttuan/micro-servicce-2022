package com.example.demo.config;

import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//JobLauncher 手動佈局調度
//讓 client 端可以自由手動調動 Job
//配置文件：spring.batch.job.enabled=false（停止 SpringBatch 自動調度）
@Configuration
public class WebJobLaucherConfig {
	@Autowired
	private JobBuilderFactory  jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	// 存放參數
	private Map<String, JobParameter> param;
	
	@Bean
	public Job jobLaunchJob() {
		return jobBuilderFactory.get("JobLaunchJob")
				.start(jobLaunchStep())
				.build();
	}
	
	@Bean
	public Step jobLaunchStep() {
		return stepBuilderFactory.get("jobLaunchStep")
				.tasklet((contribution, chunkcontext) -> {
					System.out.println("Run Step jobLaunchStep");
					return RepeatStatus.FINISHED;
				})
				.allowStartIfComplete(true)
				.build();
	}
	
}
