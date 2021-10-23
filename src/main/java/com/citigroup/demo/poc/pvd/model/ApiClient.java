package com.citigroup.demo.poc.pvd.model;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@DynamoDBTable(tableName = "ApiClient")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiClient {
	
	@DynamoDBHashKey(attributeName = "ApiKey")
	private String apiKey;

	@DynamoDBAttribute(attributeName = "ClientName")
	private String name;

	@DynamoDBAttribute(attributeName = "ClientId")
	private String clientId;

	@DynamoDBAttribute(attributeName = "Authorities")
	private List<String> authorities;
}
