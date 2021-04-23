package com.citigroup.demo.poc.pvd.model;

import java.util.List;

import lombok.Data;

@Data
public class ApiClient {
	private String apiKey;
	private String name;
	private List<String> authorities;
}
