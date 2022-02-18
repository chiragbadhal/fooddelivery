package com.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FooddeliveryApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext =SpringApplication.run(FooddeliveryApplication.class, args);
	}

}
