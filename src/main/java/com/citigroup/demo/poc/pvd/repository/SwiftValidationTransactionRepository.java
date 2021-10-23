package com.citigroup.demo.poc.pvd.repository;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.citigroup.demo.poc.pvd.model.SwiftValidationTransaction;

@EnableScan
public interface SwiftValidationTransactionRepository extends CrudRepository<SwiftValidationTransaction, String> {

	Optional<SwiftValidationTransaction> findById(String id);
}
