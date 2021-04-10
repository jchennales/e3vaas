package com.citigroup.demo.poc.pvd.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.citigroup.demo.poc.pvd.model.PaymentRequest;

@RestController
@EnableWebMvc
public class PaymentValidatorController {
    @RequestMapping(path = "/payment", method = RequestMethod.POST)
    public String validatePayment(@RequestBody PaymentRequest paymentRequest) {
    	
    	String result = "";
    	
    	if (paymentRequest.getSourceAccount() == null || paymentRequest.getSourceAccount().length() < 8) {
    		result += "Source account is invalid. ";
    	}
    	
    	if (paymentRequest.getTargetAccount() == null || paymentRequest.getTargetAccount().length() < 8) {
    		result += "Target account is invalid. ";
    	}

    	if (paymentRequest.getAmount() == null || paymentRequest.getAmount() <= 0) {
    		result += "Payment amount is invalid. ";
    	}
    	
        if (result.isEmpty()) result = "Valid!";

        return result;
        
    }

    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam("name") Optional<String> name, Principal principal) {

        return "Hello " + name.orElse("user");
        
    }

}
