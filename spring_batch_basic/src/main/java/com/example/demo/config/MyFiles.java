package com.example.demo.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import com.fasterxml.jackson.databind.ObjectMapper;

// 讀取檔案資料
@Configuration // 確保系統再啟動時就會先行配置
public class MyFiles {
	
	// 讀取 input/customers.txt 檔案資料
	@Bean
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
		mapper.afterPropertiesSet(); // 將 bean 的屬性內容配置好(透過 Assert 來檢查)後調用
		reader.setLineMapper(mapper);
		return reader;
	} 

	// 寫入 csv 檔
	@Bean
	public FlatFileItemWriter<Customer> flatFileCustomerWriter() throws Exception {
		FlatFileItemWriter<Customer> writer = new FlatFileItemWriter<>();
		String filePath = "/Users/vincenttuan/mico-servicce-2022/spring_batch_basic/src/main/resources/output/customers.csv";
		writer.setResource(new FileSystemResource(filePath));
		writer.setAppendAllowed(true);
		// 將 Customer 物件轉為文字列
		// Flat file
		writer.setLineAggregator(new DelimitedLineAggregator<Customer>() {
			// init
			{
				setDelimiter(",");
				setFieldExtractor(new BeanWrapperFieldExtractor<Customer>() {
					// init
					{
						setNames(new String[] {"id", "cname", "birthday"});
					}
				});
			}
		});
		writer.afterPropertiesSet();
		return writer;
	}
	
	// 寫入 json 檔
	@Bean
	public FlatFileItemWriter<Customer> jsonFileCustomerWriter() throws Exception {
		FlatFileItemWriter<Customer> writer = new FlatFileItemWriter<>();
		String filePath = "/Users/vincenttuan/mico-servicce-2022/spring_batch_basic/src/main/resources/output/customers.json";
		writer.setResource(new FileSystemResource(filePath));
		writer.setAppendAllowed(true);
		// 將 Customer 物件轉為文字列
		// Json file
		writer.setLineAggregator(new LineAggregator<Customer>() {
			ObjectMapper mapper = new ObjectMapper();
			@Override
			public String aggregate(Customer item) {
				String str = null;
				try {
					// 將 item 物件（Customer）轉字串（預設是轉為 Json 格式）
					str = mapper.writeValueAsString(item);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return str;
			}
		});
		
		writer.afterPropertiesSet();
		return writer;
	}
		
	// 寫入 xml 檔
	@Bean
	public StaxEventItemWriter<Customer> xmlFileCustomerWriter() throws Exception {
		StaxEventItemWriter<Customer> writer = new StaxEventItemWriter<>();
		String filePath = "/Users/vincenttuan/mico-servicce-2022/spring_batch_basic/src/main/resources/output/customers.xml";
		writer.setResource(new FileSystemResource(filePath));
		
		// 將 Customer 物件轉對應到 XML 格式
		XStreamMarshaller marshaller = new XStreamMarshaller();  // xml 編組器
		Map<String, Class> aliases = new HashMap<>();
		aliases.put("customer", Customer.class);
		marshaller.setAliases(aliases); // 配置別名
		
		// 將資料寫入到 writer
		writer.setRootTagName("customers");
		writer.setMarshaller(marshaller);
		
		writer.afterPropertiesSet();
		return writer;
	}
	
	// 批次寫入多組文件
	@Bean
	public CompositeItemWriter<Customer> writeToMultiFiles() throws Exception {
		CompositeItemWriter<Customer> writer = new CompositeItemWriter<>();
		writer.setDelegates(Arrays.asList(flatFileCustomerWriter(), jsonFileCustomerWriter(), xmlFileCustomerWriter()));
		writer.afterPropertiesSet();
		return writer;
	}
	
}
