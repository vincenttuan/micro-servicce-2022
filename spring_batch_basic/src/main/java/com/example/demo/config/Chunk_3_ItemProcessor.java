package com.example.demo.config;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/*
 * ItemProcessor 實現在讀寫之間進行業務、驗證與過濾資料等邏輯
 * ItemProcessor 若返回 null 表示不繼續處理該項目
 * 可以透過實現 CompositeItemProcessor 來進行多組的邏輯校驗
 * */
@Configuration
public class Chunk_3_ItemProcessor {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
}
