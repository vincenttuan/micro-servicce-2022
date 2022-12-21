package com.example.demo.config;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component("myProcessorLowCase")
public class MyProcessorLowCase implements ItemProcessor<String, String> {

	@Override
	public String process(String item) throws Exception {
		return item.toLowerCase();
	}

}
