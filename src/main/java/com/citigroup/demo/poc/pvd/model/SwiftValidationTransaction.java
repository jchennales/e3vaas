package com.citigroup.demo.poc.pvd.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@DynamoDBTable(tableName = "SwiftValidationTransaction")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SwiftValidationTransaction {
	
	@DynamoDBHashKey(attributeName = "TransactionId")
	private String transactionId;
	
	@DynamoDBAttribute(attributeName = "ClientId")
	private String clientId;

	@DynamoDBAttribute(attributeName = "SwiftMessageType")
	private String swiftMessageType;

	@DynamoDBAttribute(attributeName = "SwiftMessageText")
	private String swiftMessageText;

	@DynamoDBAttribute(attributeName = "ValidationStatus")
	private boolean validationStatus;

	@DynamoDBAttribute(attributeName = "ValidationMessage")
	private String validationMessage;

	@DynamoDBAttribute(attributeName = "ValiadtionAt")
	private String valiadtionAt;

}
