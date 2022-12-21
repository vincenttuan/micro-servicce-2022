package com.example.demo.config;

import java.util.Random;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 透過from(...).on(xxx).to(...) 來進行簡單決策
// on("COMPLETED")、on("FAILED")
// 另外可以透過決策器 JobExecutionDecider 來進行較為複雜的決策
// docs: https://docs.spring.io/spring-batch/docs/current/reference/html/step.html#programmaticFlowDecisions
@Configuration
public class JobConfig_4_JobExecutionDecider {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step initStep() {
		return stepBuilderFactory.get("InitStep")
				.tasklet((contribution, chunkContext) -> {
					System.out.println("成績判斷...");
					return  RepeatStatus.FINISHED;
				})
				.allowStartIfComplete(true)
				.build();
	}
	
	@Bean
	public Step passStep() {
		return stepBuilderFactory.get("PassStep")
				.tasklet((contribution, chunkContext) -> {
					System.out.println("成績及格");
					return  RepeatStatus.FINISHED;
				})
				.allowStartIfComplete(true)
				.build();
	}
	
	@Bean
	public Step failStep() {
		return stepBuilderFactory.get("FailStep")
				.tasklet((contribution, chunkContext) -> {
					System.out.println("成績不及格");
					return  RepeatStatus.FINISHED;
				})
				.allowStartIfComplete(true)
				.build();
	}
	
	// 定義一個分數決策器
	class ScoreDecider implements JobExecutionDecider {
		@Override
		public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
			int score = new Random().nextInt(101);
			if(score >= 60) {
				return  new FlowExecutionStatus("pass: " + score);
			} else {
				return  new FlowExecutionStatus("fail: " + score);
			}
		}
	}
	
	// 取得決策器
	@Bean
	public JobExecutionDecider scoreDecider() {
		return new ScoreDecider();
	}
	
	//建立任務
	@Bean
	public Job scoreDeciderJob() {
		return jobBuilderFactory.get("ScoreDeciderJob")
				.start(initStep())
				.next(scoreDecider())
				.from(scoreDecider()).on("pass").to(passStep())
				.from(scoreDecider()).on("fail").to(failStep())
				.end()
				.build();
	}
}
