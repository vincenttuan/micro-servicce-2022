package com.example.demo.config;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	
}
