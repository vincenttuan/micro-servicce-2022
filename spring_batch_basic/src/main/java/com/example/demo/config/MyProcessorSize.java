package com.example.demo.config;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component("myProcessorSize")
public class MyProcessorSize implements ItemProcessor<String, String> {

	@Override
	public String process(String item) throws Exception {
		return item.length() >= 3 ? item : null;
	}

}
