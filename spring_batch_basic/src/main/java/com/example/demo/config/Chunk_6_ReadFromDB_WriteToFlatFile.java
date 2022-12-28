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
 * 讀取 mysql customer 資料表中的紀錄並寫入到 .txt、.csv 文件中
 * 利用 FlatFileItemWriter 輸出文件
 * */
@Configuration
public class Chunk_6_ReadFromDB_WriteToFlatFile {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory  stepBuilderFactory;
	
	@Autowired
	@Qualifier("jdbcCustomerReader")
	private JdbcPagingItemReader<Customer> jdbcCustomerReader;
	
	@Autowired
	@Qualifier("flatFileCustomerWriter")
	private FlatFileItemWriter<Customer> flatFileCustomerWriter;
	
	@Bean
	public Job writeToFlatFileJob() {
		return jobBuilderFactory.get("WriteToFlatFileJob")
				.start(writeToFlatFileStep())
				.build();
	}
	
	@Bean
	public Step writeToFlatFileStep() {
		return stepBuilderFactory.get("WriteToFlatFileStep")
				.<Customer, Customer>chunk(3)
				.reader(jdbcCustomerReader)
				.writer(flatFileCustomerWriter)
				.allowStartIfComplete(true)
				.build();
	}
	
}



