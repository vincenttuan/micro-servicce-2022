package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

@Component
public class MyJdbc {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	@StepScope
	private JdbcPagingItemReader<User> jdbcUserReader() {
		JdbcPagingItemReader<User> reader = new JdbcPagingItemReader<>();
		reader.setDataSource(dataSource);
		reader.setFetchSize(2); // 每次讀取 2 筆
		// ORM
		reader.setRowMapper(new BeanPropertyRowMapper<>(User.class));
		// 指定 MySQL 配置
		MySqlPagingQueryProvider provider = new MySqlPagingQueryProvider();
		provider.setSelectClause("id, username, password, age");
		provider.setFromClause("from user");
		// 排序
		Map<String, Order> sort = new HashMap<>();
		sort.put("id", Order.ASCENDING);
		provider.setSortKeys(sort);
		// 提交
		reader.setQueryProvider(provider);
		return reader;
	}
	
}
