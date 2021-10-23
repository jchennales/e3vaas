package com.citigroup.demo.poc.pvd;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;

@Configuration
public class SQSConfiguration {

	private static String queueUrl;

	@Bean
	public AmazonSQS amazonsqs(@Value("${amazonsqs.queuename}") final String queueName) {

		AmazonSQS sqs = AmazonSQSClientBuilder.standard().withRegion(Regions.US_EAST_1).build();

		CreateQueueRequest createStandardQueueRequest = new CreateQueueRequest(queueName);
		SQSConfiguration.queueUrl = sqs.createQueue(createStandardQueueRequest).getQueueUrl();

		return sqs;

	}

	public static String getStandardQueueURL() {
		return SQSConfiguration.queueUrl;
	}
	
}
