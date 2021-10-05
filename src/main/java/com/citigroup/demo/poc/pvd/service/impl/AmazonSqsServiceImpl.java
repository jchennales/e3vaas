package com.citigroup.demo.poc.pvd.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.citigroup.demo.poc.pvd.SQSConfiguration;
import com.citigroup.demo.poc.pvd.model.MBoolean;
import com.citigroup.demo.poc.pvd.service.AmazonSqsService;

@Service
public class AmazonSqsServiceImpl implements AmazonSqsService {

	private final AmazonSQS amazonSQS;

	@Value("${amazonsqs.fifo.queuegroupname}")
	private String queueGroupName;  
	
	@Autowired
	public AmazonSqsServiceImpl(AmazonSQS amazonSQS) {
		super();
		this.amazonSQS = amazonSQS;
	}

	@Override
	public MBoolean sendMessage(String swiftMessage, String txnID) {

		// Send a message to a standard queue

		try {
			Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();

			messageAttributes.put("TXNID", new MessageAttributeValue().withStringValue(txnID).withDataType("String"));

			SendMessageRequest sendMessageStandardQueue = new SendMessageRequest()
					 .withQueueUrl(SQSConfiguration.getStandardQueueURL()).withMessageBody(swiftMessage)
					// --- if we have to send standard message queue
					//.withQueueUrl(SQSConfiguration.getfifoQueueURL()).withMessageBody(swiftMessage)
					//.withMessageGroupId(queueGroupName)// --- for fifo
					.withDelaySeconds(30) // Message will arrive in the queue after 30 seconds. We can use this only in
											// standard queues
					.withMessageAttributes(messageAttributes);

			amazonSQS.sendMessage(sendMessageStandardQueue);

			return MBoolean.newTrue("Message sent to queue successfully");

		} catch (Exception e) {

			return MBoolean.newFalse("Error ocuured while sending Message to queue.error:" + e.getMessage());
		}

	}

	@Override
	public String getMessage(int numberOfMessages) {

		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(SQSConfiguration.getStandardQueueURL())
		///ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(SQSConfiguration.getfifoQueueURL())
				.withWaitTimeSeconds(10) // Long polling;
				.withMaxNumberOfMessages(numberOfMessages); // Max is 10

		List<Message> sqsMessages = amazonSQS.receiveMessage(receiveMessageRequest).getMessages();

		return sqsMessages.stream().map(message -> message.getBody()).collect(Collectors.joining("\n\n"));

	}

}
