package com.example.demo;

import com.example.demo.businessrules.usecases.CheckConsumerScore;
import com.example.demo.businessrules.usecases.RegisterConsumer;
import com.example.demo.businessrules.usecases.steps.FindConsumerByDocument;
import com.example.demo.businessrules.usecases.steps.GetScore;
import com.example.demo.businessrules.usecases.steps.SaveConsumer;
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
	private SaveConsumer saveConsumer;

	@Bean
	public RegisterConsumer registerConsumer() {
		return new RegisterConsumer(saveConsumer);
	}

	@Autowired
	private FindConsumerByDocument findConsumerByDocument;

	@Autowired
	private GetScore getScore;

	@Bean
	public CheckConsumerScore checkConsumerScore() {
		return new CheckConsumerScore(findConsumerByDocument, getScore);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

