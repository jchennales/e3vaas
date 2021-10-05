package com.citigroup.demo.poc.pvd.service.impl;

import static com.citigroup.demo.poc.pvd.model.ApiClientKeys.API_KEY;
import static com.citigroup.demo.poc.pvd.model.ApiClientKeys.AUTHAURITIES;
import static com.citigroup.demo.poc.pvd.model.ApiClientKeys.CLIENT_NAME;
import static com.citigroup.demo.poc.pvd.model.ApiClientKeys.TABLE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.citigroup.demo.poc.pvd.model.ApiClient;
import com.citigroup.demo.poc.pvd.service.ApiClientService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApiClientServiceImpl implements ApiClientService {


	@Autowired
	private DynamoDB dynamoDB;

	@Override
	public ApiClient find(String apiKey) {
		log.debug("Finding the ApiClinet using API KEY: {}", apiKey);
		Table table = dynamoDB.getTable(TABLE.keyName());

		GetItemSpec spec = new GetItemSpec().withPrimaryKey(API_KEY.keyName(), apiKey);

		try {
			log.debug("Attempting to read the item...");
			Item outcome = table.getItem(spec);
			log.debug("GetItem succeeded: {}", outcome);
			ApiClient apiClient = new ApiClient();
			apiClient.setApiKey(outcome.getString(API_KEY.keyName()));
			apiClient.setName(outcome.getString(CLIENT_NAME.keyName()));
			apiClient.setAuthorities(outcome.getList(AUTHAURITIES.keyName()));
			return apiClient;
		} catch (Exception e) {
			log.warn("Unable to find API Clinet for API KEY: {} - ", apiKey, e.getMessage());
			return null;
		}
	}

}
