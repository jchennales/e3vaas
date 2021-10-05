package com.citigroup.demo.poc.pvd.model;

public class ValidationRequest {
	
	private String rawswiftmessage, apikey;

	public String getRawswiftmessage() {
		return rawswiftmessage;
	}

	public void setRawswiftmessage(String rawswiftmessage) {
		this.rawswiftmessage = rawswiftmessage;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	@Override
	public String toString() {
		return "ValidationRequest [rawswiftmessage=" + rawswiftmessage + ", apikey=" + apikey + "]";
	}
	
	

}



