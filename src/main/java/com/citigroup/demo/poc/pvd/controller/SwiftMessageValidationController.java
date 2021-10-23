package com.citigroup.demo.poc.pvd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citigroup.demo.poc.pvd.model.SwiftValidationRequest;
import com.citigroup.demo.poc.pvd.model.SwiftValidationResponse;
import com.citigroup.demo.poc.pvd.model.SwiftValidationTransaction;
import com.citigroup.demo.poc.pvd.service.AmazonSqsService;
import com.citigroup.demo.poc.pvd.service.SwiftValidationService;
import com.citigroup.demo.poc.pvd.service.SwiftValidationTransactionService;

@RestController
public class SwiftMessageValidationController {

	@Autowired
	private SwiftValidationService swiftValidationService;

	@Autowired
	private SwiftValidationTransactionService swiftValidationTransactionService;

	@Autowired
	private AmazonSqsService amazonSQSService;

	@PostMapping(value = "/swiftValidate", consumes = "text/plain")
	@PreAuthorize("hasAuthority('validation')")
	public SwiftValidationResponse validate(@RequestParam("clientId") String clientId,
			@RequestParam("type") String messageType, @RequestBody String swiftMessage)	{
		SwiftValidationRequest swiftValidationRequest = new SwiftValidationRequest();
		swiftValidationRequest.setClientId(clientId);
		swiftValidationRequest.setSwiftMessageType(messageType);
		swiftValidationRequest.setSwiftMessageText(swiftMessage);
		return swiftValidationService.validate(swiftValidationRequest);
	}

	@GetMapping("/swiftValidate")
	@PreAuthorize("hasAuthority('validation')")
	public SwiftValidationTransaction getTransaction(@RequestParam("txnid") String transactionId) {
		return swiftValidationTransactionService.find(transactionId);

	}

	@GetMapping("/QueueMessage/{numberofmessages}")
	@PreAuthorize("hasAuthority('validation')")
	public String getQueueMessages(@PathVariable("numberofmessages") int numberOfMessages) {
		return amazonSQSService.getMessage(numberOfMessages);
	}
}
