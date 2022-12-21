package com.example.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Flow 的創建與使用
// Flow 是多個 Step 的集合，可以被多個 Job 重複使用
@Configuration
public class JobConfig_2_flow {
	
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
	
	// 建立 Floe（包含：step1()、step2() ...）
	@Bean
	public Flow flowDemoFlow() {
		return new FlowBuilder<Flow>("FlowDemoFlow")
				.start(step1())
				.next(step2())
				.build();
	}
	
	// 建立 Job 用來執行 flow 與 step
	@Bean
	public Job flowDemoJob() {
		return jobBuilderFactory.get("FlowDemoJob")
				.start(flowDemoFlow()) // 先執行 flow 裡面的 step ...
				.next(step3()) // 再執行 step3()
				.end()
				.build();
	}
	
	
}
