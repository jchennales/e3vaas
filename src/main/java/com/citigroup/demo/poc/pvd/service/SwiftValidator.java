package com.citigroup.demo.poc.pvd.service;

import com.citigroup.demo.poc.pvd.model.MBoolean;

/**
 * 
 *
 */
public interface SwiftValidator {

	/**
	 * 
	 * @param swiftMessageText
	 * @return
	 */
	MBoolean validate(String swiftMessageText);
}
