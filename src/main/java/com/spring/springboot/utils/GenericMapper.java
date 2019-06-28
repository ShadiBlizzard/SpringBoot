package com.spring.springboot.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenericMapper {
	
	@Bean
	ModelMapper getMapper() {
		return new ModelMapper();
	}

}
