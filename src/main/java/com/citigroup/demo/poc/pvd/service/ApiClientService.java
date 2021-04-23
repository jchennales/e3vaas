package com.citigroup.demo.poc.pvd.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.citigroup.demo.poc.pvd.model.ApiClient;

@Component
public class ApiClientService {

	// TODO: Replace this with the actual implementation reading client data from DB
	private HashMap<String, ApiClient> dummyClientDatabase;
	
	@PostConstruct
	private void init() {
		
		dummyClientDatabase = new HashMap<>();
		
		List<String> standardRoleAuthorities = Arrays.asList("hello", "validation"); 
		List<String> healthRoleAuthorities = Arrays.asList("health"); 
		List<String> testRoleAuthorities = Arrays.asList("hello"); 
		List<String> adminRoleAuthorities = Arrays.asList("hello", "validation", "health", "admin"); 
		
		ApiClient paypal = new ApiClient();
		paypal.setApiKey("5a862b8d-f2d2-45e0-9149-da4e84cfd012");
		paypal.setName("PayPal");
		paypal.setAuthorities(standardRoleAuthorities);
		dummyClientDatabase.put(paypal.getApiKey(), paypal);
		
		ApiClient uber = new ApiClient();
		uber.setApiKey("e9e280ac-8daf-4070-b4f0-c077e89318e4");
		uber.setName("Uber");
		uber.setAuthorities(standardRoleAuthorities);
		dummyClientDatabase.put(uber.getApiKey(), uber);

		ApiClient test = new ApiClient();
		test.setApiKey("926d7c48-5645-47cc-8edb-cd1c31c768b7");
		test.setName("Test");
		test.setAuthorities(testRoleAuthorities);
		dummyClientDatabase.put(test.getApiKey(), test);

		ApiClient health = new ApiClient();
		health.setApiKey("a4f8b49d-3a15-40d6-9e58-a08bc7bbc949");
		health.setName("Health");
		health.setAuthorities(healthRoleAuthorities);
		dummyClientDatabase.put(health.getApiKey(), health);

		ApiClient admin = new ApiClient();
		admin.setApiKey("210a0d7e-5d39-4bd0-9e22-90f4799b78e4");
		admin.setName("Admin");
		admin.setAuthorities(adminRoleAuthorities);
		dummyClientDatabase.put(admin.getApiKey(), admin);
	}
	
	public ApiClient findClient(String apiKey) {
		return dummyClientDatabase.get(apiKey);
	}
	
}
