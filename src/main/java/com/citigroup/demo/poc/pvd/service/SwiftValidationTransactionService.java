package com.citigroup.demo.poc.pvd.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citigroup.demo.poc.pvd.model.SwiftValidationTransaction;
import com.citigroup.demo.poc.pvd.repository.SwiftValidationTransactionRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SwiftValidationTransactionService {

	@Autowired
	private SwiftValidationTransactionRepository swiftValidationTransactionRepository;

	public String save(SwiftValidationTransaction swiftValidationTransaction) {
		log.info("Creating new transaction...");
		String transactionId = null;
		transactionId = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssS"));
		swiftValidationTransaction.setTransactionId(transactionId);
		swiftValidationTransactionRepository.save(swiftValidationTransaction);
		log.info("SwiftValidationTransaction {}", swiftValidationTransaction);
		return transactionId;
	}

	public SwiftValidationTransaction find(String transactionId) {
		log.info("Fetching new transaction for id {}...", transactionId);
		return swiftValidationTransactionRepository.findById(transactionId).orElse(null);
	}

}
