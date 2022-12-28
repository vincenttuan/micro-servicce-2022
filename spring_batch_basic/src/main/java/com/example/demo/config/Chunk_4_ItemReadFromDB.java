package com.example.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// JdbcPagingItemReader 分頁讀取
@Configuration
public class Chunk_4_ItemReadFromDB {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory  stepBuilderFactory;
	
	@Autowired
	@Qualifier("jdbcUserReader")
	private JdbcPagingItemReader<User> jdbcUserReader;
	
	@Bean
	public Job itemReaderDbJob() {
		return jobBuilderFactory.get("ItemReaderDbJob")
				.start(itemReaderDbStep())
				.build();
	}
	
	@Bean
	public Step itemReaderDbStep() {
		return stepBuilderFactory.get("ItemReaderDbStep")
				.<User, User>chunk(2)
				.reader(jdbcUserReader)
				.writer(users -> {
					for(User user : users) {
						System.out.println(user);
					}
					System.out.println("--------------------------------------");
				})
				.allowStartIfComplete(true)
				.build();
	}
	
}
