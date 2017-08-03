package com.ta.toko.module.supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	private static Logger logger = LoggerFactory.getLogger(SupplierController.class);

	public SupplierController() {
		logger.info("SupplierController created");
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage() {
		logger.info("supplier home page called..");
		return "supplier/supplier-entries";
	}
}
