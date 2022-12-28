package com.example.demo.config;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

/*
 * 透過 JdbcBatchItemWriter 來批次寫入數據
 * 將 resources/input/customers.txt 的檔案資料
 * 匯入到 mysql customer 資料表中
 * 要先建立 customer 資料表
 * */
@Configuration
public class Chunk_5_ItemWriterToDB {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory  stepBuilderFactory;
	
	@Autowired
	@Qualifier("fileCustomerReader")
	private FlatFileItemReader<Customer> fileCustomerReader;
	
}
