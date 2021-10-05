package com.citigroup.demo.poc.pvd.service;

import com.citigroup.demo.poc.pvd.model.ValidationStatus;

public interface SwiftMessageValidator {

	ValidationStatus validate(final String message);
}
