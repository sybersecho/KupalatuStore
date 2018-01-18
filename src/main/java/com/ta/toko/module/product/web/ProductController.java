package com.ta.toko.module.product.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ta.toko.entity.Product;
import com.ta.toko.module.login.CustomUserDetails;
import com.ta.toko.module.product.ProductCriteria;
import com.ta.toko.module.product.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	private static Logger logger = LoggerFactory.getLogger(ProductController.class);

	private ProductService service;

	@Autowired
	private ProductSearchValidator validator;
	
	@ModelAttribute("productActive")
	public String productActive() {
		return "active";
	}
	
	@ModelAttribute("user")
	public CustomUserDetails getUser() {
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return user;
	}

	@Autowired
	public ProductController(ProductService service) {
		logger.info("ProductController created");
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String homePage(Model model) {
		logger.debug("product home page called..");
		model.addAttribute("actionUrl", "/product/search");
		model.addAttribute("criteria", new ProductCriteria());
		model.addAttribute("products", service.emptyList());
		return "product/product-entries";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addProductPage(Model model) {
		logger.debug("add product home page called..");
		model.addAttribute("product", new Product());
		return "product/product";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addProduct(@Valid Product product, BindingResult result, RedirectAttributes model) {
		logger.debug("Saving a product");
		if (result.hasErrors()) {
			logger.debug("product has error");
			return "product/product";
		}

		service.save(product);
		// <div class="alert alert-success alert-dismissable">
		model.addFlashAttribute("alertType", "alert-success");
		model.addFlashAttribute("alert", true);
		model.addFlashAttribute("alertMessage", "Success save a product.");
		return "redirect:/product";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchProduct(@ModelAttribute("criteria") ProductCriteria criteria, BindingResult result,
			Model model) {
		logger.debug("Search product with: " + criteria.toString());

		validator.validate(criteria, result);
		if (result.hasErrors()) {
			logger.info("product criteria has error");
			model.addAttribute("alertType", "alert-danger");
			model.addAttribute("alert", true);
			model.addAttribute("alertMessage", "Please fill in one of the criteria");
			return "product/product-entries";
		}

		model.addAttribute("products", service.search(criteria));
		model.addAttribute("actionUrl", "/product/search");
		return "product/product-entries";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String showProductDetailPage(@PathVariable Long id, Model model) {
		logger.debug("show detail of product with ID: " + id);
		model.addAttribute("product", service.findById(id));
		model.addAttribute("actionUrl", "/product/" + id);
		return "product/product";
	}

	// TODO is this needed?
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String saveProductDetailPage(@Valid Product product, BindingResult result, RedirectAttributes model) {
		logger.debug("show detail of product with ID: " + product.getId());

		if (result.hasErrors()) {
			logger.debug("Fail to update product.");
			return "product/product";
		}

		service.update(product);

		model.addFlashAttribute("alertType", "alert-success");
		model.addFlashAttribute("alert", true);
		model.addFlashAttribute("alertMessage", "Success update a product.");
		return "redirect:/product";
	}

	// TODO change request method to post
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable Long id, RedirectAttributes model) {
		logger.debug("delete product with ID: " + id);

		service.delete(id);
		model.addFlashAttribute("alertType", "alert-success");
		model.addFlashAttribute("alert", true);
		model.addFlashAttribute("alertMessage", "Success delete a product.");
		return "redirect:/product";
	}
}
