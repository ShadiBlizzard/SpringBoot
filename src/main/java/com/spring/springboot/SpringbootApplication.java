package com.spring.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ActiveProfiles;

@SpringBootApplication
@ActiveProfiles({"secure"})
public class SpringbootApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
		
	}

	

}
