package com.example.demo.config;

import java.util.Map;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
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
public class WebJobLaucherConfig implements StepExecutionListener {
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
				.listener(this) // 監聽參數, 要加入監聽，否則得不到參數 param
				.tasklet((contribution, chunkcontext) -> {
					System.out.println("Run Step jobLaunchStep");
					System.out.println("Run Step param = " + param);
					System.out.println("Run Step param's msg = " + param.get("msg"));
					return RepeatStatus.FINISHED;
				})
				.allowStartIfComplete(true)
				.build();
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("獲取參數");
		param = stepExecution.getJobParameters().getParameters();
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
