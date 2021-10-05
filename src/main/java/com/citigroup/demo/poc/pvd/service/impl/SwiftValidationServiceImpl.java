package com.citigroup.demo.poc.pvd.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citigroup.demo.poc.pvd.exceptions.SwiftValidationTransactionException;
import com.citigroup.demo.poc.pvd.model.MBoolean;
import com.citigroup.demo.poc.pvd.model.SwiftValidationRequest;
import com.citigroup.demo.poc.pvd.model.SwiftValidationResponse;
import com.citigroup.demo.poc.pvd.model.SwiftValidationTransaction;
import com.citigroup.demo.poc.pvd.service.AmazonSqsService;
import com.citigroup.demo.poc.pvd.service.SwiftValidationFactory;
import com.citigroup.demo.poc.pvd.service.SwiftValidationService;
import com.citigroup.demo.poc.pvd.service.SwiftValidationTransactionService;
import com.citigroup.demo.poc.pvd.service.SwiftValidator;

/**
 * 
 *
 */
@Service
public class SwiftValidationServiceImpl implements SwiftValidationService {

	@Autowired
	private SwiftValidationFactory swiftValidationFactory;

	@Autowired
	private SwiftValidationTransactionService swiftValidationTransactionService;

	@Autowired
	private AmazonSqsService amazonSqsService;
	
	@Override
	public SwiftValidationResponse validate(SwiftValidationRequest swiftValidationRequest)
			throws SwiftValidationTransactionException {
		SwiftValidationResponse swiftValidationResponse = new SwiftValidationResponse();

		SwiftValidator swiftValidator = swiftValidationFactory
				.getSwiftValidator(swiftValidationRequest.getSwiftMessageType());

		MBoolean mBoolean = swiftValidator.validate(swiftValidationRequest.getSwiftMessageText());

		SwiftValidationTransaction transaction = new SwiftValidationTransaction();
		transaction.setClientId(swiftValidationRequest.getClientId());
		transaction.setSwiftMessageType(swiftValidationRequest.getSwiftMessageType().toString());
		transaction.setSwiftMessageText(swiftValidationRequest.getSwiftMessageText());
		transaction.setValidationStatus(mBoolean.value());
		transaction.setValidationMessage(mBoolean.getMessage());
		transaction.setValiadtionAt(LocalDateTime.now().toString());

		String transactionId = swiftValidationTransactionService.save(transaction);

		swiftValidationResponse.setTransactionId(transactionId);
		swiftValidationResponse.setValidationStatus(mBoolean.value());
		swiftValidationResponse.setValidationMessage(mBoolean.getMessage());

		
		amazonSqsService.sendMessage(swiftValidationRequest.getSwiftMessageText(), transactionId);
				
		
		return swiftValidationResponse;
	}

}
