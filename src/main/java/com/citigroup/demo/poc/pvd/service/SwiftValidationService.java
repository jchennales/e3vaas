package com.citigroup.demo.poc.pvd.service;

import com.citigroup.demo.poc.pvd.exceptions.SwiftValidationTransactionException;
import com.citigroup.demo.poc.pvd.model.SwiftValidationRequest;
import com.citigroup.demo.poc.pvd.model.SwiftValidationResponse;

/**
 * 
 *
 */
public interface SwiftValidationService {

	/**
	 * 
	 * @param swiftValidationRequest
	 * @return
	 * @throws SwiftValidationTransactionException 
	 */
	SwiftValidationResponse validate(SwiftValidationRequest swiftValidationRequest) throws SwiftValidationTransactionException;
}
