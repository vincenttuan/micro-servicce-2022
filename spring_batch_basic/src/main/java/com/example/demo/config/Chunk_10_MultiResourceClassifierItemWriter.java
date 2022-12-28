package com.example.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
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
//@Configuration
public class Chunk_10_MultiResourceClassifierItemWriter {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory  stepBuilderFactory;
	
	@Autowired
	@Qualifier("jdbcCustomerReader")
	private JdbcPagingItemReader<Customer> jdbcCustomerReader;
	
	@Autowired
	@Qualifier("writeToClassifierMultiFiles")
	private ClassifierCompositeItemWriter<Customer> writeToClassifierMultiFiles;
	
	@Autowired
	@Qualifier("flatFileCustomerWriter")
	public ItemStreamWriter<Customer> flatFileCustomerWriter;
	
	@Autowired
	@Qualifier("jsonFileCustomerWriter")
	public ItemStreamWriter<Customer> jsonFileCustomerWriter;
	
	@Autowired
	@Qualifier("xmlFileCustomerWriter")
	public ItemStreamWriter<Customer> xmlFileCustomerWriter;
	
	@Bean
	public Job writeToClassifierMultiFilesJob() {
		return jobBuilderFactory.get("WriteToClassifierMultiFilesJob")
				.start(writeToClassifierMultiFilesStep())
				.build();
	}
	
	@Bean
	public Step writeToClassifierMultiFilesStep() {
		return stepBuilderFactory.get("WriteToClassifierMultiFilesStep")
				.<Customer, Customer>chunk(3)
				.reader(jdbcCustomerReader)
				.writer(writeToClassifierMultiFiles)
				.stream(flatFileCustomerWriter)
				.stream(jsonFileCustomerWriter)
				.stream(xmlFileCustomerWriter)
				.allowStartIfComplete(true)
				.build();
	}
}
