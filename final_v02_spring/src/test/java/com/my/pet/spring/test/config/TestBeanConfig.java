package com.my.pet.spring.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.my.pet.spring.controller.MyController;

@Configuration
public class TestBeanConfig {
	
	@Bean
	public MyController myController() {
		return new MyController();
	}

}
