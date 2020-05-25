package com.shoon.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Service;
@Service
public class Common {
	private String domain = "https://api.bithumb.com";
	
	public String httpConnection(Map<String,String> key, String domainUrl, String order, String payment, String method) {
		String result = "";
		StringBuilder sb = null;
		HashMap<String,String> params = null;
		byte[] postDataBytes = null;
		HashMap<String, String> httpHeaders = null;
		try {
			String fullDomain = domain + domainUrl;
			URL url = new URL(fullDomain);
			System.out.println(domainUrl);
			params = new HashMap<String,String>(); // 파라미터 세팅
			
			
			if(!order.equals("")) {
				params.put("order_currency", order); 
			}
			if(!order.equals("")) {
				params.put("payment_currency", payment); 
			}
			params.put("endpoint", domainUrl);
			
			//System.out.println(httpHeaders.get("Api-Sign"));
			if(key.get("apiKey") != null && key.get("secretKey") != null) {
				
			    httpHeaders = getHttpHeaders(domainUrl, params, key.get("apiKey"), key.get("secretKey"));
				httpHeaders.put("api-client-type", "2");
				
		        StringBuilder postData = new StringBuilder();
		        for(Map.Entry<String,String> param : params.entrySet()) {
		            if(postData.length() != 0) postData.append('&');
		            System.out.println(param.getKey() + " : : " + String.valueOf(param.getValue()));
		           
		            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
		            postData.append('=');
		            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
		        }
		        postDataBytes = postData.toString().getBytes("UTF-8");
			}
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
	
			con.setConnectTimeout(5000); // 서버에 연결되는 Timeout 시간 설정
			con.setReadTimeout(5000); // InputStream 읽어 오는 Timeout 시간 설정
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("Api-Sign", httpHeaders.get("Api-Sign"));
			con.setRequestProperty("Api-Nonce", httpHeaders.get("Api-Nonce"));
			con.setRequestProperty("Api-Key", httpHeaders.get("Api-Key"));
			con.setRequestProperty("api-client-type", httpHeaders.get("api-client-type"));
			con.setRequestMethod(method);
			con.setDoOutput(true);
			
			if(postDataBytes != null) {
				con.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
				con.getOutputStream().write(postDataBytes); 
			}

			sb = new StringBuilder();
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line).append("\n");
									}
				br.close();
				result = sb.toString();
				System.out.println("" + result);
			} else {
				System.out.println(con.getResponseMessage());
			}
			con.disconnect();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public String httpConnection(String domainUrl, String method) {
		String result = "";
		StringBuilder sb = null;

		try {
			String fullDomain = domain + domainUrl;
			URL url = new URL(fullDomain);
			System.out.println(domainUrl);

			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
	
			con.setConnectTimeout(5000); // 서버에 연결되는 Timeout 시간 설정
			con.setReadTimeout(5000); // InputStream 읽어 오는 Timeout 시간 설정
			con.setRequestMethod(method);
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
				System.out.println("" + result);
			} else {
				System.out.println(con.getResponseMessage());
			}
			con.disconnect();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	 private String usecTime() {
	    	/*
			long start = System.nanoTime();
			// do stuff
			long nanoseconds = System.nanoTime();
			long microseconds = TimeUnit.NANOSECONDS.toMicros(nanoseconds);
			long seconds = TimeUnit.NANOSECONDS.toSeconds(nanoseconds);
		
			int elapsedTime = (int) (microseconds + seconds);
		
			System.out.println("elapsedTime ==> " + microseconds + " : " + seconds);
			*/
	    	
			return String.valueOf(System.currentTimeMillis());
	    }

	   
	    
	    public static String encodeURIComponent(String s)
	    {
	      String result = null;
	   
	      try
	      {
	        result = URLEncoder.encode(s, "UTF-8")
	                           .replaceAll("\\+", "%20")
	                           .replaceAll("\\%21", "!")
	                           .replaceAll("\\%27", "'")
	                           .replaceAll("\\%28", "(")
	                           .replaceAll("\\%29", ")")
	                           .replaceAll("\\%26", "&")
	                           .replaceAll("\\%3D", "=")
	                           .replaceAll("\\%7E", "~");
	      }
	   
	      // This exception should never occur.
	      catch (UnsupportedEncodingException e)
	      {
	        result = s;
	      }
	   
	      return result;
	    }

	    private HashMap<String, String> getHttpHeaders(String endpoint, HashMap<String, String> rgData, String apiKey, String apiSecret) {
		    	
			String strData = Util.mapToQueryString(rgData).replace("?", "");
			String nNonce = usecTime();
			
			strData = strData.substring(0, strData.length()-1);
		
		
			System.out.println("1 : " + strData);
			
			strData = encodeURIComponent(strData);
			
			HashMap<String, String> array = new HashMap<String, String>();
		
			
			String str = endpoint + ";"	+ strData + ";" + nNonce;
			//String str = "/info/balance;order_currency=BTC&payment_currency=KRW&endpoint=%2Finfo%2Fbalance;272184496";
			
	        String encoded = asHex(hmacSha512(str, apiSecret));
			
			System.out.println("strData was: " + str);
			System.out.println("apiSecret was: " + apiSecret);
			array.put("Api-Key", apiKey);
			array.put("Api-Sign", encoded);
			array.put("Api-Nonce", String.valueOf(nNonce));
		
			return array;
			
	    }
	    
	    private static final String DEFAULT_ENCODING = "UTF-8";
		private static final String HMAC_SHA512 = "HmacSHA512";
		 
		public static byte[] hmacSha512(String value, String key){
		    try {
		        SecretKeySpec keySpec = new SecretKeySpec(
		                key.getBytes(DEFAULT_ENCODING),
		                HMAC_SHA512);
		 
		        Mac mac = Mac.getInstance(HMAC_SHA512);
		        mac.init(keySpec);
		
		        final byte[] macData = mac.doFinal( value.getBytes( ) );
		        byte[] hex = new Hex().encode( macData );
		        
		        //return mac.doFinal(value.getBytes(DEFAULT_ENCODING));
		        return hex;
		 
		    } catch (NoSuchAlgorithmException e) {
		        throw new RuntimeException(e);
		    } catch (InvalidKeyException e) {
		        throw new RuntimeException(e);
		    } catch (UnsupportedEncodingException e) {
		        throw new RuntimeException(e);
		    }
		}
		 
		public static String asHex(byte[] bytes){
		    return new String(Base64.encodeBase64(bytes));
		}

	 
	
}
