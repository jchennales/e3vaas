package com.citigroup.demo.poc.pvd.service;

import com.citigroup.demo.poc.pvd.model.ApiClient;

public interface ApiClientService {

	ApiClient find(final String apiKey);

}
