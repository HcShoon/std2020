package com.shoon.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService {
	private String apiKey = "[apiKey]";
	private String secretKey = "[secretKey]";

	@Autowired
	Common common;
		
	public String tickerApiService(String call,String order, String payment) {
		String domainUrl =  call + order + "_" + payment;
		String method = "GET";

		return common.httpConnection(domainUrl, method );
	}
	
	public String orderbookApiService (String call,String order, String payment) {
		String domainUrl =  call + order + "_" + payment;
		String method = "GET";

		return common.httpConnection(domainUrl,method );
	}
	
	public String accountApiService(String call,String order, String payment) {
		String domainUrl =  call ;
		String method = "POST";
		
		Map<String,String> key = new HashMap<String, String>();
		key.put("apiKey", apiKey);
		key.put("secretKey", secretKey);
		
		return common.httpConnection(key, domainUrl, order, payment, method );
	}
	public String tickerUserApiService( String call,String order, String payment) {
		String domainUrl =  call ;
		String method = "POST";

		Map<String,String> key = new HashMap<String, String>();
		key.put("apiKey", apiKey);
		key.put("secretKey", secretKey);
		
		return common.httpConnection(key, domainUrl, order, payment, method );
	}
	
	

}
