package com.citigroup.demo.poc.pvd.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.citigroup.demo.poc.pvd.model.MBoolean;
import com.citigroup.demo.poc.pvd.service.SwiftValidator;
import com.google.gson.GsonBuilder;

import gr.datamation.swift.common.InvalidMessageFormatException;
import gr.datamation.swift.common.SwiftMessage;
import gr.datamation.swift.processor.SwiftMsgProcessor;
import gr.datamation.swift.validator.SwiftMsgValidator;
import gr.datamation.swift.validator.SwiftValidObj;

/**
 * 
 *
 */
@Service
public class MTSwiftMessageValidator implements SwiftValidator {

	private static final Logger LOGGER = LoggerFactory.getLogger(MTSwiftMessageValidator.class);

	@Override
	public MBoolean validate(String swiftMessageText) {
		MBoolean mBoolean = MBoolean.newFalse();

		SwiftMsgProcessor parser = new SwiftMsgProcessor("\n");
		try {
			SwiftMessage smObj = parser.ParseMsgStringToObject(swiftMessageText);
			// Validate the message
			SwiftValidObj swiftValidObj = new SwiftMsgValidator().validateMsg(smObj);
			// Check if it has any errors
			if (swiftValidObj.hasErrors()) {
				mBoolean.setFalse("Message is invalid.",
						new GsonBuilder().setPrettyPrinting().create().toJson(swiftValidObj.getValidationErrorList()));
			} else {
				mBoolean.setTrue("Message is valid.");
			}
		} catch (InvalidMessageFormatException e) {
			LOGGER.error("Message cannot be parsed");
			mBoolean.setFalse("Message cannot be parsed.", "Error:", e.getMessage());
		}
		return mBoolean;
	}

}
