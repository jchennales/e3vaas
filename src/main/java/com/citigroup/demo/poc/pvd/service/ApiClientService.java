package com.citigroup.demo.poc.pvd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citigroup.demo.poc.pvd.model.ApiClient;
import com.citigroup.demo.poc.pvd.repository.ApiKeyRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApiClientService {

	@Autowired
	ApiKeyRepository apiKeyRepository;

	public ApiClient find(String apiKey) {
		log.info("Finding the ApiClinet from repository using API KEY: {}", apiKey);
		return apiKeyRepository.findById(apiKey).orElse(null);
	}

}
