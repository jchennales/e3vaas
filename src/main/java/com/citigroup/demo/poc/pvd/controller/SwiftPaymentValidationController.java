package com.citigroup.demo.poc.pvd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citigroup.demo.poc.pvd.model.ValidationRequest;
import com.citigroup.demo.poc.pvd.model.ValidationStatus;
import com.citigroup.demo.poc.pvd.service.PaymentValidationService;

@RestController
public class SwiftPaymentValidationController {

	@Autowired
	private PaymentValidationService paymentvalidationservice;

	@PostMapping(value = "/SwiftValidation", consumes = "text/plain")
	@PreAuthorize("hasAuthority('validation')")
	
	public ValidationStatus validate(@RequestParam("apikey") String apikey, @RequestBody String swiftMessage) {
		System.out.println("process started");
		ValidationRequest validationrequest = new ValidationRequest();
		validationrequest.setApikey(apikey);
		validationrequest.setRawswiftmessage(swiftMessage);

		return paymentvalidationservice.validate(validationrequest);
	}

}
