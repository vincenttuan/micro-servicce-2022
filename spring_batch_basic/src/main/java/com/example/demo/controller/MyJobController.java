package com.example.demo.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyJobController {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job jobLaunchJob;
	
	@GetMapping("/job/{msg}")
	public String jobRun1(@PathVariable String msg) throws Exception {
		// 將接收到的 msg 傳給任務當參數
		JobParameters params = new JobParametersBuilder()
				.addString("msg", msg)
				.toJobParameters();
		// 啟動任務
		jobLauncher.run(jobLaunchJob, params);
		
		return "jobLaunch succcess";
	}
	
}
