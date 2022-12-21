package com.example.demo.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * ItemReader 單一來源數據讀取
 * MultiResourceItemReader 多方來源數據讀取
 * chunk(數據筆數)
 * Ex: chunk(100) 表示讀完 100 筆資料之後才去做後續的處理，
 *     最後一筆若不足 100，例如只剩下 20 筆 ，就以20筆進行處理
 * */
@Configuration
public class Chunk_1_ItemReader {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step itemReaderStep() {
		return stepBuilderFactory.get("ItemReaderStep")
				.<String, String>chunk(2) // <讀取的資料型別, 寫入的資料型別>
				.reader(extractedReader())
				//.writer((items) -> System.out.println(items))
				.writer(System.out::println)
				.allowStartIfComplete(true)
				.build();
		}

	private ItemReader<String> extractedReader() {
		List<String> items = Arrays.asList("A", "BB", "CCC", "DDDD", "EEEEE");
		return new ListItemReader<>(items);
	}
	
}
