package com.shoon.sonicApi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

@Component
public class Common {
	private String sonicUrl = "https://open-api.bitsonic.co.kr";
	
	public JSONObject httpConn(Map<String, String> headerParms, Map<String, String> bodyParms) {
		
		String result = "";
		StringBuilder sb = null;
		JSONObject jsonObj = null;
		
		try {
			String fullDomain = sonicUrl + headerParms.get("endpoint");
			
			if(bodyParms.size() > 0) {
				
		        StringBuilder postData = new StringBuilder();
		        
		        for(Map.Entry<String,String> param : bodyParms.entrySet()) {
		            if(postData.length() != 0) postData.append('&');       
		            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
		            postData.append('=');
		            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
		        }
		        
		        fullDomain += "?" + postData;
		        
			}

			URL url = new URL(fullDomain);
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			con.setConnectTimeout(1000); 
			con.setReadTimeout(1000); 
			con.setRequestMethod(headerParms.get("method"));
			con.setRequestProperty("Accept", "application/json");
			JSONParser parser = new JSONParser(); 
			


			if(!headerParms.get("BS-API-SIGNATURE").isEmpty())
				con.setRequestProperty("BS-API-SIGNATURE", headerParms.get("BS-API-SIGNATURE"));
			
			con.setDoOutput(false);
			sb = new StringBuilder();
			
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
				String line;
				
				while ((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}
				br.close();
				result = sb.toString();
				Object obj = parser.parse( result ); 
				jsonObj = (JSONObject) obj;
				System.out.println(jsonObj.toString());
				
			} else {
				System.out.println(con.getResponseMessage());
			}
			con.disconnect();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonObj;
	}

	public String mlsTime() {
		
	      Date date = new Date();
	      long timeMilli = date.getTime();
		
		return String.valueOf(timeMilli);
	}
	
	public String signKey(String endpoint, String nonce, String queryString, String secretKey) {
		// 사인키를 위한 String 생성
		String strForSign = endpoint + "/" + nonce +"/" + queryString;  
		System.out.println("strForSign : " + strForSign);
		String signatureResult = null;
		try {
			// HMAC SHA256
			Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secretKeySpec;
			secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
			sha256_HMAC.init(secretKeySpec);
			signatureResult =  Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(strForSign.getBytes("UTF-8")));
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return signatureResult;
	}

	public String rereplaceResult(String result) {
		// TODO Auto-generated method stub
		result = result.replaceAll("\"email\"", "\"이메일\"")
					   .replaceAll("\"user_level\"", "\"회원 레벨\"")
					   .replaceAll("\"commission_level\"", "\"수수료 등급\"")
					   .replaceAll("\"is_using_bsc_fee\"", "\"BSC 수수료 사용 여부\"")
					   .replaceAll("\"symbol\"", "\"화폐를 의미하는 영문 코드\"")
					   .replaceAll("\"avail_balance\"", "\"가용 자산\"")
					   .replaceAll("\"pending_balance\"", "\"pending 자산\"")
					   .replaceAll("\"total_balance\"", "\"자산 합계\"")
					   .replaceAll("\"address\"", "\"지갑 주소\"")
					   .replaceAll("\"is_tag\"", "\"tag 사용 여부\"")
					   .replaceAll("\"label\"", "\"tag 종류\"")
					   .replaceAll("\"tag\"", "\"tag 값\"")
					   .replaceAll("\"e\"", "\"이벤트 타입\"")
					   .replaceAll("\"E\"", "\"이벤트 시간\"")
					   .replaceAll("\"P\"", "\"변동률\"")
					   .replaceAll("\"p\"", "\"변동가\"")
					   .replaceAll("\"x\"", "\"전일 종가\"")
					   .replaceAll("\"s\"", "\"화폐 영문코드 + 마켓 화폐 영문 코드\"")
					   .replaceAll("\"i\"", "\"간격\"")
					   .replaceAll("\"o\"", "\"시가\"")
					   .replaceAll("\"c\"", "\"종가\"")
					   .replaceAll("\"h\"", "\"고가\"")
					   .replaceAll("\"l\"", "\"저가\"")
					   .replaceAll("\"v\"", "\"거래량\"")
					   .replaceAll("\"q\"", "\"마켓 심볼 기준 거래량\"")
					   .replaceAll("\"t\"", "\"기준 시간 하루 전\"")
					   .replaceAll("\"T\"", "\"기준 시간\"");
						
					
		return result;
		
	}
	public String misToDate(String rdate) {
		
		String resultDate;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");     
		resultDate = sdf.format(new Date(Long.parseLong((rdate))));
		
		return resultDate;
	}
	
}
