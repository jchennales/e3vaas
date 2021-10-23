package com.citigroup.demo.poc.pvd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

@Configuration
public class DynamoDBConfiguration {

	@Bean
	public AmazonDynamoDB client() {
		return AmazonDynamoDBClientBuilder.standard().build();
	}

}
