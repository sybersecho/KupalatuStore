package com.ta.toko.module.supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ta.toko.module.supplier.entity.Supplier;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	private static Logger logger = LoggerFactory.getLogger(SupplierController.class);

	public SupplierController() {
		logger.info("SupplierController created");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String homePage() {
		logger.debug("supplier home page called..");
		return "supplier/supplier-entries";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addSupplierPage(Model model) {
		logger.debug("supplier add page called");
		model.addAttribute("supplier", new Supplier());
		return "supplier/supplier";
	}
}
