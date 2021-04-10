package com.citigroup.demo.poc.pvd.model;

import lombok.Data;

@Data
public class PaymentRequest {
	private String sourceAccount;
	private String targetAccount;
	private Double amount;
}
