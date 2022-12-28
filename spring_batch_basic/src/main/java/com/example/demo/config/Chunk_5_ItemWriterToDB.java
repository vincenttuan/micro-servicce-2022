package com.example.demo.config;

import org.springframework.context.annotation.Configuration;

/*
 * 透過 JdbcBatchItemWriter 來批次寫入數據
 * 將 resources/input/customers.txt 的檔案資料
 * 匯入到 mysql customer 資料表中
 * 要先建立 customer 資料表
 * */
@Configuration
public class Chunk_5_ItemWriterToDB {
	
}
