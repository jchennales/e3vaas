package com.citigroup.demo.poc.pvd.service;

import org.springframework.stereotype.Component;

import com.google.gson.GsonBuilder;

import gr.datamation.swift.common.InvalidMessageFormatException;
import gr.datamation.swift.common.SwiftMessage;
import gr.datamation.swift.processor.SwiftMsgProcessor;
import gr.datamation.swift.validator.SwiftMsgValidator;
import gr.datamation.swift.validator.SwiftValidObj;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SwiftValidator {

	public String validate(String swiftMessageText) {

		SwiftMsgProcessor parser = new SwiftMsgProcessor("\n");
		try {
			SwiftMessage smObj = parser.ParseMsgStringToObject(swiftMessageText);
			// Validate the message
			SwiftValidObj swiftValidObj = new SwiftMsgValidator().validateMsg(smObj);
			// Check if it has any errors
			if (swiftValidObj.hasErrors()) {
				return new GsonBuilder().setPrettyPrinting().create().toJson(swiftValidObj.getValidationErrorList());
			} else {
				return null;
			}
		} catch (InvalidMessageFormatException e) {
			log.error("Message cannot be parsed");
			return "Message cannot be parsed. " + e.getMessage();
		}
	}
}
