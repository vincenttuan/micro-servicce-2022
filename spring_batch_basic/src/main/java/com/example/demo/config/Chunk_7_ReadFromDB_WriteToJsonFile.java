package com.example.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * 讀取 mysql customer 資料表中的紀錄並寫入到 .json 文件中
 * 利用 FlatFileItemWriter 輸出文件
 * */
@Configuration
public class Chunk_7_ReadFromDB_WriteToJsonFile {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory  stepBuilderFactory;
	
	@Autowired
	@Qualifier("jdbcCustomerReader")
	private JdbcPagingItemReader<Customer> jdbcCustomerReader;
	
	@Autowired
	@Qualifier("jsonFileCustomerWriter")
	private FlatFileItemWriter<Customer> jsonFileCustomerWriter;
	
	@Bean
	public Job writeToJsonFileJob() {
		return jobBuilderFactory.get("WriteToJsonFileJob")
				.start(writeToJsonFileStep())
				.build();
	}
	
	@Bean
	public Step writeToJsonFileStep() {
		return stepBuilderFactory.get("WriteToJsonFileStep")
				.<Customer, Customer>chunk(3)
				.reader(jdbcCustomerReader)
				.writer(jsonFileCustomerWriter)
				.allowStartIfComplete(true)
				.build();
	}
	
}



