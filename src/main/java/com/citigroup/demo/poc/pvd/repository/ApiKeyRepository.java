package com.citigroup.demo.poc.pvd.repository;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.citigroup.demo.poc.pvd.model.ApiClient;

@EnableScan
public interface ApiKeyRepository extends CrudRepository<ApiClient, String> {

	Optional<ApiClient> findById(String id);
}
