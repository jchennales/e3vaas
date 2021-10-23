package com.citigroup.demo.poc.pvd.model;

import lombok.Data;

@Data
public class SwiftValidationResponse {
	private String transactionId;
	private boolean validationStatus;
	private String validationMessage;
}
