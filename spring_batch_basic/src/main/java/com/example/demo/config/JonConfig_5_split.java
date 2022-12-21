package com.example.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

// split 拆分任務來實現多執行緒併發執行
// 在任務 Job 中可以讓多個 Step、Flow 併發執行
@Configuration
public class JonConfig_5_split {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("Step1")
				.tasklet((contribution, chunkContext) -> {
					System.out.println("Hello Step1");
					return RepeatStatus.FINISHED;
				})
				.allowStartIfComplete(true)
				.build();
	}
	
	@Bean
	public Step step2() {
		return stepBuilderFactory.get("Step2")
				.tasklet((contribution, chunkContext) -> {
					System.out.println("Hello Step2");
					return RepeatStatus.FINISHED;
				})
				.allowStartIfComplete(true)
				.build();
	}
	
	@Bean
	public Step step3() {
		return stepBuilderFactory.get("Step3")
				.tasklet((contribution, chunkContext) -> {
					System.out.println("Hello Step3");
					return RepeatStatus.FINISHED;
				})
				.allowStartIfComplete(true)
				.build();
	}
	
	// 建立 2 個 flow
	@Bean
	public Flow flowDemo1() {
		return new FlowBuilder<Flow>("FlowDemo1")
				.start(step1())
				.build();
	}
	
	@Bean
	public Flow flowDemo2() {
		return new FlowBuilder<Flow>("FlowDemo2")
				.start(step2())
				.next(step3())
				.build();
	}
	
	// 建立 Job
	@Bean
	public Job splitJob() {
		return jobBuilderFactory.get("SplitJob")
				.start(flowDemo1())
				.split(new SimpleAsyncTaskExecutor())
				.add(flowDemo2())
				.end()
				.build();
	}
	
}
