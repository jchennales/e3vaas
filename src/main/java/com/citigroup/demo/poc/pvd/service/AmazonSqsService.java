package com.citigroup.demo.poc.pvd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.citigroup.demo.poc.pvd.SQSConfiguration;

@Service
public class AmazonSqsService {

	@Autowired
	private AmazonSQS amazonSQS;

	public void sendMessage(String swiftMessage, String txnID) {

		Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();

		messageAttributes.put("TXNID", new MessageAttributeValue().withStringValue(txnID).withDataType("String"));

		SendMessageRequest sendMessageStandardQueue = new SendMessageRequest()
				.withQueueUrl(SQSConfiguration.getStandardQueueURL())
				.withMessageBody(swiftMessage)
				.withDelaySeconds(30)
				.withMessageAttributes(messageAttributes);

		amazonSQS.sendMessage(sendMessageStandardQueue);

	}

	public String getMessage(int numberOfMessages) {

		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(SQSConfiguration.getStandardQueueURL())
				.withWaitTimeSeconds(10)
				.withMaxNumberOfMessages(numberOfMessages);

		List<Message> sqsMessages = amazonSQS.receiveMessage(receiveMessageRequest).getMessages();

		return sqsMessages.stream().map(message -> message.getBody()).collect(Collectors.joining("\n\n"));

	}

}
