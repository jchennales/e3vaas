package com.citigroup.demo.poc.pvd.service;

import com.citigroup.demo.poc.pvd.model.SwiftMessageType;

/**
 * 

 *
 */
public interface SwiftValidationFactory {
	/**
	 * 
	 * @param swiftMessageType
	 * @return
	 */
	public SwiftValidator getSwiftValidator(SwiftMessageType swiftMessageType);
}
