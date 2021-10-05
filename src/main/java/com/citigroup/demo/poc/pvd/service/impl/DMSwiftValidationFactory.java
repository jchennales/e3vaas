package com.citigroup.demo.poc.pvd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.citigroup.demo.poc.pvd.model.SwiftMessageType;
import com.citigroup.demo.poc.pvd.service.SwiftValidationFactory;
import com.citigroup.demo.poc.pvd.service.SwiftValidator;

/**
 * 
 *
 */
@Component
public class DMSwiftValidationFactory implements SwiftValidationFactory {

	@Autowired
	private MTSwiftMessageValidator mtSwiftMessageValidator;

	@Override
	public SwiftValidator getSwiftValidator(SwiftMessageType swiftMessageType) {
		return mtSwiftMessageValidator;
	}
}
