package com.citigroup.demo.poc.pvd;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesResult;
import com.amazonaws.services.sqs.model.SetQueueAttributesRequest;

@Configuration
public class SQSConfiguration {

	private static String standardQueueUrl;
	private static String fifoQueueUrl;

	@Bean
	public AmazonSQS amazonsqs(@Value("${amazonsqs.queuename}") final String queueName,
			@Value("${amazonsqs.deadletter.queuename}") final String deadLetterQueueName,
			@Value("${amazonsqs.deadletter.queueattribute}") final String deadLetterQueueAttribute,
			@Value("${amazonsqs.fifo.queuename}") final String fifoQueueName) {

		AmazonSQS sqs = AmazonSQSClientBuilder.standard().withRegion(Regions.US_EAST_1).build();

		// Create a standard queue

		CreateQueueRequest createStandardQueueRequest = new CreateQueueRequest(queueName);
		SQSConfiguration.standardQueueUrl = sqs.createQueue(createStandardQueueRequest).getQueueUrl();


		// Create a fifo queue

		Map<String, String> queueAttributes = new HashMap<String, String>();
		queueAttributes.put("FifoQueue", "true");
		queueAttributes.put("ContentBasedDeduplication", "true");

		CreateQueueRequest createFifoQueueRequest = new CreateQueueRequest(fifoQueueName)
				.withAttributes(queueAttributes);
		SQSConfiguration.fifoQueueUrl = sqs.createQueue(createFifoQueueRequest).getQueueUrl();

		// Set up a dead letter queue

		String deadLetterQueueUrl = sqs.createQueue(deadLetterQueueName).getQueueUrl();

		GetQueueAttributesResult deadLetterQueueAttributes = sqs.getQueueAttributes(
				new GetQueueAttributesRequest(deadLetterQueueUrl).withAttributeNames(deadLetterQueueAttribute));

		String deadLetterQueueARN = deadLetterQueueAttributes.getAttributes().get(deadLetterQueueAttribute);

		SetQueueAttributesRequest queueAttributesRequest = new SetQueueAttributesRequest()
				.withQueueUrl(standardQueueUrl).addAttributesEntry("RedrivePolicy",
						"{\"maxReceiveCount\":\"2\", " + "\"deadLetterTargetArn\":\"" + deadLetterQueueARN + "\"}");

		sqs.setQueueAttributes(queueAttributesRequest);

		return sqs;

	}

	public static String getStandardQueueURL() {
		return SQSConfiguration.standardQueueUrl;
	}
	
	public static String getfifoQueueURL() {
		return SQSConfiguration.fifoQueueUrl;
	}
	
}
