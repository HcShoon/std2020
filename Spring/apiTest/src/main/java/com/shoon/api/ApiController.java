package com.shoon.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * Handles requests for the application home page.
 */
@Controller
public class ApiController {
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Autowired
	ApiService apiService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		return "home";
	}
	
	@RequestMapping(value = "/ticker", method = RequestMethod.GET)
	@ResponseBody
	public String ticker(@RequestParam String call,@RequestParam String order,@RequestParam String payment) {
				
		return apiService.tickerApiService(call,order,payment);
	}
	
	@RequestMapping(value = "/orderbook", method = RequestMethod.GET)
	@ResponseBody
	public String orderbook(@RequestParam String call,@RequestParam String order,@RequestParam String payment) {
				
		return apiService.orderbookApiService(call,order,payment);
	}
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	@ResponseBody
	public String account(@RequestParam String call,@RequestParam String order,@RequestParam String payment) {
		
		return apiService.accountApiService(call,order,payment);
	}
	
	@RequestMapping(value = "/tickerUser", method = RequestMethod.GET)
	@ResponseBody
	public String tickerUser(@RequestParam String call,@RequestParam String order,@RequestParam String payment) {
		call = "/info/ticker";
		return apiService.tickerUserApiService(call,order,payment);
	}

}
