package com.ta.toko.module.supplier;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ta.toko.module.common.entity.Address;
import com.ta.toko.module.supplier.entity.Supplier;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	private static Logger logger = LoggerFactory.getLogger(SupplierController.class);

	public SupplierController() {
		logger.info("SupplierController created");
		dummies();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String homePage(Model model) {
		logger.debug("supplier home page called..");
		model.addAttribute("suppliers", this.suppliers);
		return "supplier/supplier-entries";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addSupplierPage(Model model) {
		logger.debug("supplier add page called");
		model.addAttribute("supplier", new Supplier());
		return "supplier/supplier";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addNewSupplier(@Valid Supplier supplier, BindingResult bindingResult, Model model) {
		logger.debug("saving new supplier");
		logger.info("supplier address: " + supplier.getSupplierAddress().getLine1());
		if (bindingResult.hasErrors()) {
			logger.debug("Supplier has error when saving");
			return "supplier/supplier";
		}
		this.suppliers.add(supplier);
		return "redirect:/supplier";
	}

	private List<Supplier> suppliers = new ArrayList<Supplier>();

	private void dummies() {
		for (int i = 0; i < 12; i++) {
			Supplier supplier = new Supplier();
			supplier.setId(Long.valueOf(i));
			supplier.setName("Product " + (i + 1));
			supplier.setCode("S00" + (i + 1));
			supplier.setContact("Contact " + (i + 1));
			supplier.setEmail((i + 1) + "test@test.com");

			Address supplierAddress = new Address();
			supplierAddress.setCity("city" + (i + 1));
			supplierAddress.setLine1("Line " + (i + 1));
			supplierAddress.setPostCode("12345" + (i + 1));
			supplierAddress.setState("state" + (i + 1));
			supplier.setSupplierAddress(supplierAddress);

			suppliers.add(supplier);
		}
	}
}
