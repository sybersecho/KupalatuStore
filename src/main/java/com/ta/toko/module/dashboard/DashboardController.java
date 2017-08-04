package com.ta.toko.module.dashboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class DashboardController {
	private static Logger logger = LoggerFactory.getLogger(DashboardController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String homePage() {
		logger.info("redirect to supplier home page");
		return "redirect:/supplier";
	}
}
