package com.citigroup.demo.poc.pvd.service;

import com.citigroup.demo.poc.pvd.model.MBoolean;

public interface AmazonSqsService {

	MBoolean sendMessage(String swiftMessage,String txnID);
	
	String getMessage(int numberOfMessages);

}
