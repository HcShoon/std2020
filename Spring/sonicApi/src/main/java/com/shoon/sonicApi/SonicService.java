package com.shoon.sonicApi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SonicService {

	@Autowired
	Common comn;
	
	public String accountService(Map<String, String> pramMap) {
		
		Map<String,String> headerParms = null;
		Map<String,String> bodyParms = null;
		
		String result = null;
		
		try {
			headerParms = new HashMap<String, String>();
			bodyParms = new HashMap<String, String>();
			String nonce = comn.mlsTime();
			
			//String queryString = "nonce=1547305200000&api_key=my-api-key"; // URL Query String
			String queryString = "api_key=" + pramMap.get("apiKey") + "&nonce=" + nonce;
			
			headerParms.put("method", "GET");
			headerParms.put("endpoint", pramMap.get("endpoint"));
			headerParms.put("BS-API-SIGNATURE", comn.signKey(pramMap.get("endpoint"), nonce, queryString, pramMap.get("secretKey")));
			
			bodyParms.put("api_key", pramMap.get("apiKey"));
			bodyParms.put("nonce", nonce);
			
			result = comn.httpConn(headerParms, bodyParms);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		result = comn.rereplaceResult(result);
		
		
		return result;
	}

	public String balanceService(Map<String, String> pramMap) {
		Map<String,String> headerParms = null;
		Map<String,String> bodyParms = null;
		
		String result = null;
		
		try {
			headerParms = new HashMap<String, String>();
			bodyParms = new HashMap<String, String>();
			String nonce = comn.mlsTime();
			
			//String queryString = "nonce=1547305200000&api_key=my-api-key"; // URL Query String
			String queryString = "api_key=" + pramMap.get("apiKey") + "&nonce=" + nonce;
			
			headerParms.put("method", "GET");
			headerParms.put("endpoint", pramMap.get("endpoint"));
			headerParms.put("BS-API-SIGNATURE", comn.signKey(pramMap.get("endpoint"), nonce, queryString, pramMap.get("secretKey")));
			
			bodyParms.put("api_key", pramMap.get("apiKey"));
			bodyParms.put("nonce", nonce);
			
			result = comn.httpConn(headerParms, bodyParms);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		result = comn.rereplaceResult(result);
		
		
		return result;
	}
	
}
