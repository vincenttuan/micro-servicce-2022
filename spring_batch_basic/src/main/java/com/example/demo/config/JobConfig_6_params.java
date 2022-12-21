package com.example.demo.config;

import java.util.Map;

import org.springframework.batch.core.ExitStatus;
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


// Job 參數傳遞
// 參數格式: key = value
// 參數是誰可以得到 Step
// 如何得到: 實作 StepExecutionListener
@Configuration
public class JobConfig_6_params implements StepExecutionListener {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	private Map<String, JobParameter> params;
	
	@Bean
	public Step paramStep() {
		return stepBuilderFactory.get("ParamStep")
				.tasklet((contribution, chunkContext) -> {
					// 輸出接收到的參數內容
					System.out.println(params);
					System.out.println(params.get("info"));
					return RepeatStatus.FINISHED;
				})
				.allowStartIfComplete(true)
				.build();
	}
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		params = stepExecution.getJobParameters().getParameters();
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
