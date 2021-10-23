package com.citigroup.demo.poc.pvd.model;

import lombok.Data;

@Data
public class ValidationRequest {
	private String apikey;
	private String rawswiftmessage;
}
