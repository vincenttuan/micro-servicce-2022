package com.example.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * 批次寫入
 * 透過 CompositeItemWriter 寫入到多組文件
 * 透過 ClassifierCompositeItemWriter 分類並寫入到多組文件
 * */
@Configuration
public class Chunk_9_MultiResourceItemWriter {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory  stepBuilderFactory;
	
	@Autowired
	@Qualifier("jdbcCustomerReader")
	private JdbcPagingItemReader<Customer> jdbcCustomerReader;
	
	@Autowired
	@Qualifier("writeToMultiFiles")
	private CompositeItemWriter<Customer> writeToMultiFiles;
	
	@Bean
	public Job writeToMultiFilesJob() {
		return jobBuilderFactory.get("WriteToMultiFilesJob")
				.start(writeToMultiFilesStep())
				.build();
	}
	
	@Bean
	public Step writeToMultiFilesStep() {
		return stepBuilderFactory.get("WriteToMultiFilesStep")
				.<Customer, Customer>chunk(3)
				.reader(jdbcCustomerReader)
				.writer(writeToMultiFiles)
				.allowStartIfComplete(true)
				.build();
	}
}
