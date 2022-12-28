package com.example.demo.config;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

// 讀取檔案資料
@Configuration // 確保系統再啟動時就會先行配置
public class MyFiles {
	
	public FlatFileItemReader<Customer> fileCustomerReader() {
		FlatFileItemReader<Customer> reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource("input/customers.txt"));
		//reader.setLinesToSkip(1); // 跳過第一行
		// 設定欄位分隔符
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer(",");
		tokenizer.setNames("id", "cname", "birthday");
		// 設定 mapper 對應
		DefaultLineMapper<Customer> mapper = new  DefaultLineMapper<>();
		mapper.setLineTokenizer(tokenizer); // 配置分隔符號
		mapper.setFieldSetMapper(new FieldSetMapper<Customer>() {
			@Override
			public Customer mapFieldSet(FieldSet fieldSet) throws BindException {
				Customer customer = new Customer();
				customer.setId(fieldSet.readInt("id"));
				customer.setCname(fieldSet.readString("cname"));
				customer.setBirthday(fieldSet.readString("birthday"));
				return customer;
			}
		});
		mapper.afterPropertiesSet(); // 將 bean 的屬性內容配置好後調用
		reader.setLineMapper(mapper);
		return reader;
	} 
}
