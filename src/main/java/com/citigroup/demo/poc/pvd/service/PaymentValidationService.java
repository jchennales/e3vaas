package com.citigroup.demo.poc.pvd.service;

import com.citigroup.demo.poc.pvd.model.ValidationRequest;
import com.citigroup.demo.poc.pvd.model.ValidationStatus;

public interface PaymentValidationService {

	// load data in dynamodb
	ValidationStatus validate(ValidationRequest validationrequest);
	
}
