package com.ta.toko.module.supplier.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ta.toko.entity.Supplier;
import com.ta.toko.module.supplier.SupplierService;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

	private static Logger logger = LoggerFactory.getLogger(SupplierController.class);
	private SupplierService service;

	@Autowired
	public SupplierController(SupplierService service) {
		logger.info("SupplierController created");
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String homePage(Model model) {
		logger.debug("supplier home page called..");
		model.addAttribute("suppliers", service.getAll());
		return "supplier/supplier-entries";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addSupplierPage(Model model) {
		logger.debug("supplier add page called");
		model.addAttribute("actionUrl", "/supplier/add");
		model.addAttribute("supplier", new Supplier());
		return "supplier/supplier";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addNewSupplier(@Valid Supplier supplier, BindingResult bindingResult, Model model) {
		logger.debug("saving new supplier");
		if (bindingResult.hasErrors()) {
			logger.debug("Supplier has error when saving");
			return "supplier/supplier";
		}
//		long lastId = service.getAll().get(service.getAll().size() - 1).getId();
//		supplier.setId(lastId + 1);
		service.save(supplier);
		return "redirect:/supplier";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editSupplier(@Valid Supplier supplier, BindingResult bindingResult, Model model) {
		logger.debug("edit supplier");
		if (bindingResult.hasErrors()) {
			logger.debug("Supplier has error when saving");
			return "supplier/supplier";
		}
		service.update(supplier);
		return "redirect:/supplier";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String showASupplier(@PathVariable Long id, Model model) {
		logger.info("show supplier with ID: " + id);
		Supplier supplier = service.findById(id);

		model.addAttribute("actionUrl", "/supplier/edit");
		model.addAttribute("supplier", supplier);
		return "supplier/supplier";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteASupplier(@PathVariable Long id, Model model) {
		logger.info("delete supplier with ID: " + id);
		service.delete(id);
		return "redirect:/supplier";
	}
}
