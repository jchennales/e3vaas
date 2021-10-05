package com.citigroup.demo.poc.pvd.model;

public enum ValidationKeys {
	TABLE("PaymentValidation"), API_KEY("ApiKey"),SWIFTMESSGAE("swiftmessagetext"),TRANSACTIONID("TransactionID"),VALIDATIOONSTATUS("validationstatus"),MESSAGETEXT("messagetext"),TIMESTAMP("timestamp");

	private String key;

	ValidationKeys(String key) {
		this.key = key;
	}

	public String key() {
		return key;
	}
}
