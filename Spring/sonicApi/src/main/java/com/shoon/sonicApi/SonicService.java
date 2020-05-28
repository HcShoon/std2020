package com.shoon.sonicApi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SonicService {

	@Autowired
	Common comn;
	
	public String accountService(Map<String, String> pramMap) {
		
		Map<String,String> headerParms = null;
		Map<String,String> bodyParms = null;
		
		JSONObject resultObj = null;
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
			
			resultObj = comn.httpConn(headerParms, bodyParms);
			resultObj = (JSONObject) resultObj.get("result");

			result = comn.rereplaceResult(resultObj.toString());
			
		}catch (Exception e) {
			e.printStackTrace();
		}

		
		
		return result;
	}

	

	@SuppressWarnings("unchecked")
	public String walletAddService(Map<String, String> pramMap) {
		Map<String,String> headerParms = null;
		Map<String,String> bodyParms = null;
		
		JSONObject resultObj = null;
		String result = null;
		
		try {
			headerParms = new HashMap<String, String>();
			bodyParms = new HashMap<String, String>();
			String nonce = comn.mlsTime();
			
			//String queryString = "api_key=my-api-key&nonce=1547305200000"; // URL Query String
			String queryString = "api_key=" + pramMap.get("apiKey") + "&nonce=" + nonce;
			
			headerParms.put("method", "GET");
			headerParms.put("endpoint", pramMap.get("endpoint"));
			headerParms.put("BS-API-SIGNATURE", comn.signKey(pramMap.get("endpoint"), nonce, queryString, pramMap.get("secretKey")));
			
			bodyParms.put("api_key", pramMap.get("apiKey"));
			bodyParms.put("nonce", nonce);
			
			resultObj = comn.httpConn(headerParms, bodyParms);
			resultObj = (JSONObject) resultObj.get("result");
			
			if(String.valueOf(resultObj.get("is_tag")).equals("0")) { 
				resultObj.replace("is_tag", "사용안함");
				resultObj.remove("label");
				resultObj.remove("tag");
			
			}else resultObj.replace("is_tag", "사용함");
			
			result = comn.rereplaceResult(resultObj.toString());
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return result;
	}

	public String tickerService(Map<String, String> pramMap) {
		Map<String,String> headerParms = null;
		Map<String,String> bodyParms = null;
		
		JSONObject resultObj = null;
		String result = null;
		


		try {
			headerParms = new HashMap<String, String>();
			bodyParms = new HashMap<String, String>();
				
			headerParms.put("method", "GET");
			headerParms.put("endpoint", pramMap.get("endpoint"));
			headerParms.put("BS-API-SIGNATURE", "");	
			
			bodyParms.put("api_key", pramMap.get("apiKey"));
			bodyParms.put("symbol", pramMap.get("symbol"));
			
			resultObj = comn.httpConn(headerParms, bodyParms);
			resultObj = (JSONObject) resultObj.get("result");
			
			resultObj.replace("E", comn.misToDate(resultObj.get("E").toString()));
			resultObj.replace("T", comn.misToDate(resultObj.get("T").toString() + "000"));
			resultObj.replace("t", comn.misToDate(resultObj.get("t").toString() + "000"));

			Iterator iter = resultObj.keySet().iterator();
			
			while(iter.hasNext()){
				   String key = (String) iter.next();
				   String value = resultObj.get(key).toString();
				   value = value.replaceAll("0+$", "").replaceAll("[.]*$", "");
				   resultObj.replace(key, value);
				   
				 			
			}
			
			result = comn.rereplaceResult(resultObj.toString());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public String orderBookService(Map<String, String> pramMap) {
		Map<String,String> headerParms = null;
		Map<String,String> bodyParms = null;
		
		JSONObject resultObj = null;
		String result = null;
		
		try {
			headerParms = new HashMap<String, String>();
			bodyParms = new HashMap<String, String>();
				
			headerParms.put("method", "GET");
			headerParms.put("endpoint", pramMap.get("endpoint"));
			headerParms.put("BS-API-SIGNATURE", "");	
			
			bodyParms.put("api_key", pramMap.get("apiKey"));
			bodyParms.put("symbol", pramMap.get("symbol"));
			
			resultObj = comn.httpConn(headerParms, bodyParms);
			resultObj = (JSONObject) resultObj.get("result");

			result = comn.rereplaceResult(resultObj.toString());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		
		return result;
	}
	
}
