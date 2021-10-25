package com.citigroup.demo.poc.pvd.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citigroup.demo.poc.pvd.model.SwiftValidationRequest;
import com.citigroup.demo.poc.pvd.model.SwiftValidationResponse;
import com.citigroup.demo.poc.pvd.model.SwiftValidationTransaction;

@Service
public class SwiftValidationService {

	@Autowired
	private SwiftValidator swiftValidator;

	@Autowired
	private SwiftValidationTransactionService swiftValidationTransactionService;

	@Autowired
	private AmazonSqsService amazonSqsService;

	public SwiftValidationResponse validate(SwiftValidationRequest swiftValidationRequest) {

		SwiftValidationResponse swiftValidationResponse = new SwiftValidationResponse();

		String validationErrors = swiftValidator.validate(swiftValidationRequest.getSwiftMessageText());

		SwiftValidationTransaction transaction = new SwiftValidationTransaction();
		transaction.setClientId(swiftValidationRequest.getClientId());
		transaction.setSwiftMessageType(swiftValidationRequest.getSwiftMessageType().toString());
		transaction.setSwiftMessageText(swiftValidationRequest.getSwiftMessageText());
		transaction.setValidationStatus(validationErrors.isEmpty());
		transaction.setValidationMessage(validationErrors);
		transaction.setValiadtionAt(LocalDateTime.now().toString());

		String transactionId = swiftValidationTransactionService.save(transaction);

		swiftValidationResponse.setTransactionId(transactionId);
		swiftValidationResponse.setValidationStatus(validationErrors.isEmpty());
		swiftValidationResponse.setValidationMessage(validationErrors);
		
		amazonSqsService.sendMessage(swiftValidationRequest.getSwiftMessageText(), transactionId);
		
		return swiftValidationResponse;
	}

}
