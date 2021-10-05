package com.citigroup.demo.poc.pvd.service;

import com.citigroup.demo.poc.pvd.exceptions.SwiftValidationTransactionException;
import com.citigroup.demo.poc.pvd.model.SwiftValidationTransaction;

/**
 * 
 *
 */
public interface SwiftValidationTransactionService {

	/**
	 * 
	 * @param swiftValidationTransaction
	 * @return
	 * @throws SwiftValidationTransactionException
	 */
	
	//insert data dynamodb
	String save(SwiftValidationTransaction swiftValidationTransaction) throws SwiftValidationTransactionException;

	/**
	 * 
	 * @param transactionId
	 * @return
	 */
	
	//select data from dynamodb
	SwiftValidationTransaction find(String transactionId);

}
