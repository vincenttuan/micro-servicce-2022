package com.example.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * 讀取 mysql customer 資料表中的紀錄並寫入到 .xml 文件中
 * 利用 StaxEventItemWriter 輸出 XML 文件
 * */
@Configuration
public class Chunk_8_ReadFromDB_WriteToXMLFile {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory  stepBuilderFactory;
	
	@Autowired
	@Qualifier("jdbcCustomerReader")
	private JdbcPagingItemReader<Customer> jdbcCustomerReader;
	
	@Autowired
	@Qualifier("xmlFileCustomerWriter")
	private StaxEventItemWriter<Customer> xmlFileCustomerWriter;
	
	@Bean
	public Job writeToXMLFileJob() {
		return jobBuilderFactory.get("WriteToXMLFileJob")
				.start(writeToXMLFileStep())
				.build();
	}
	
	@Bean
	public Step writeToXMLFileStep() {
		return stepBuilderFactory.get("WriteToXMLFileStep")
				.<Customer, Customer>chunk(3)
				.reader(jdbcCustomerReader)
				.writer(xmlFileCustomerWriter)
				.allowStartIfComplete(true)
				.build();
	}
	
}



