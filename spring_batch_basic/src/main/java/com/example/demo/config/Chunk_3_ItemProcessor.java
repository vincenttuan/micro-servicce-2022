package com.example.demo.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
	
	@Bean
	public Job itemProcessorJob() {
		return jobBuilderFactory.get("ItemProcessorJob")
				.start(itemProcessorStep())
				.build();
	}
	
	@Bean
	public Step itemProcessorStep() {
		return stepBuilderFactory.get("ItemProcessorStep")
				.<String, String>chunk(2)
				.reader(extractedReader())
				.processor(extractedProcessor())
				.writer(extractedWriter())
				.build();
	}

	private ItemProcessor<String, String> extractedProcessor() {
		return new ItemProcessor<String, String>() {
			@Override
			public String process(String item) throws Exception {
				return item.toLowerCase();
			}
		};
	}
	
	private ItemReader<String> extractedReader() {
		List<String> items = Arrays.asList("A", "BB", "CCC", "DDDD", "EEEEE");
		return new ListItemReader<>(items);
	}
	
	private ItemWriter<String> extractedWriter() {
		return new ItemWriter<String>() {

			@Override
			public void write(List<? extends String> items) throws Exception {
				System.out.println(items.size());
				for(String item : items) {
					System.out.println(item);
				}
			}
		};
	}
	
}
