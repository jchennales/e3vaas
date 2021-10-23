package com.citigroup.demo.poc.pvd.service;

import org.springframework.stereotype.Service;

import com.citigroup.demo.poc.pvd.model.ValidationStatus;

import gr.datamation.swift.common.InvalidMessageFormatException;
import gr.datamation.swift.common.SwiftMessage;
import gr.datamation.swift.processor.SwiftMsgProcessor;
import gr.datamation.swift.validator.SwiftMsgValidator;
import gr.datamation.swift.validator.SwiftValidObj;

@Service
public class SwiftMessageValidator {

	public ValidationStatus validate(final String message) {
		ValidationStatus result = new ValidationStatus();

		SwiftMsgProcessor parser = new SwiftMsgProcessor("\n");
		try {
			System.out.println("Parsing a valid MT101 message");
			SwiftMessage smObj = parser.ParseMsgStringToObject(message);
			System.out.println("Sender " + smObj.getArgLTaddrBlk1());
			System.out.println("Receiver " + smObj.getArgLTaddrBlk2());
			smObj.getTag("20");
			smObj.getTag("50F");
			// Get specific tag from the SwiftMessage object

			// Validate the message
			SwiftValidObj swiftValidObj = new SwiftMsgValidator().validateMsg(smObj);
			// Check if it has any errors
			if (!swiftValidObj.hasErrors()) {
				result.setStatus(true);
				result.setMessage("message is valid");
			} else {
				result.setStatus(false);
				result.setMessage("message is invalid" + swiftValidObj.getErrorMessage());
			}
		} catch (InvalidMessageFormatException e) {
			System.err.println("Message cannot be parsed");
			result.setStatus(false);
			result.setMessage("message is invalid" + e.getMessage());

		}

		return result;
	}

}
