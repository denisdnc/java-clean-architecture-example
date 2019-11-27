package com.example.demo;

import com.example.demo.businessrules.usecases.CreateCustomer;
import com.example.demo.businessrules.usecases.steps.FindCustomerByDocument;
import com.example.demo.businessrules.usecases.steps.SaveCustomer;
import com.example.demo.interfaceadapters.CustomerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.example.demo")
@EnableFeignClients
public class DemoApplication {

	@Autowired
	private FindCustomerByDocument findCustomerByDocument;

	@Autowired
	private SaveCustomer saveCustomer;

	@Bean
	public CustomerConverter customerConverter() {
		return new CustomerConverter();
	}

	@Bean
	public CreateCustomer createCustomer() {
		return new CreateCustomer(findCustomerByDocument, saveCustomer);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

