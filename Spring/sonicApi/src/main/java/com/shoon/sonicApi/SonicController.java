package com.shoon.sonicApi;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class SonicController {
	
	private static final Logger logger = LoggerFactory.getLogger(SonicController.class);
	
	@Autowired
	SonicService snService;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
				
		return "home";
	}
	@RequestMapping(value = "/account", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	@ResponseBody
	public String accountController(@RequestParam String apiKey, @RequestParam String endpoint,@RequestParam String secretKey) {
		
		Map<String, String> pramMap = new HashMap<String, String>();
		
		pramMap.put("apiKey", apiKey);
		pramMap.put("secretKey", secretKey);
		pramMap.put("endpoint", endpoint);
		String result = snService.accountService(pramMap);
		
		return result;
	}
	@RequestMapping(value = "/balance", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	@ResponseBody
	public String balanceController(@RequestParam String apiKey, @RequestParam String endpoint,@RequestParam String secretKey) {
		
		Map<String, String> pramMap = new HashMap<String, String>();
		
		pramMap.put("apiKey", apiKey);
		pramMap.put("secretKey", secretKey);
		pramMap.put("endpoint", endpoint);
		String result = snService.balanceService(pramMap);
		
		return result;
	}
}
