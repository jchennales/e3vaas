package com.citigroup.demo.poc.pvd.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.citigroup.demo.poc.pvd.model.ValidationKeys;
import com.citigroup.demo.poc.pvd.model.ValidationRequest;
import com.citigroup.demo.poc.pvd.model.ValidationStatus;

@Service
public class PaymentValidationServiceImpl implements PaymentValidationService {
	@Autowired
	private SwiftMessageValidator swiftmessagevalidator;
	@Autowired
	private DynamoDB dynamodb;

	@Override
	public ValidationStatus validate(ValidationRequest validationrequest) {
		// validation request and apikey
		// step 1 validation
		ValidationStatus validationstatus = swiftmessagevalidator.validate(validationrequest.getRawswiftmessage());
		System.out.println("validation request" + validationrequest);
		System.out.println("validation request2" + validationstatus);

		// insert data in dynamodb

		Table table = dynamodb.getTable(ValidationKeys.TABLE.key());

		String transactionID = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssS"));

		System.out.println("transactionID request2" + transactionID);
		
		try {
			System.out.println("Adding a new item...");
			PutItemOutcome outcome = table.putItem(new Item()
					.withPrimaryKey(ValidationKeys.TRANSACTIONID.key(), transactionID)
					.withString(ValidationKeys.API_KEY.key(), validationrequest.getApikey())
					.withBoolean(ValidationKeys.VALIDATIOONSTATUS.key(), validationstatus.isStatus())
					.withString(ValidationKeys.MESSAGETEXT.key(), validationstatus.getMessage())
					.withString(ValidationKeys.SWIFTMESSGAE.key(), validationrequest.getRawswiftmessage())
					.withString(ValidationKeys.TIMESTAMP.key(), transactionID));
			
			
			
	
			

			System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return validationstatus;
	}

}
