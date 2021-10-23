package com.citigroup.demo.poc.pvd.model;

import lombok.Data;

@Data
public class SwiftValidationRequest {
	private String clientId;
	private String swiftMessageType;
	private String swiftMessageText;
}
