package com.ta.toko.module.product.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ta.toko.entity.Product;
import com.ta.toko.module.product.ProductCriteria;
import com.ta.toko.module.product.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	private static Logger logger = LoggerFactory.getLogger(ProductController.class);

	private ProductService service;

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
		model.addAttribute("products", emptyList());
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
		//FIXME remove this fake generate id when dao is ready
		List<Product> products = service.getAll();
		long dummyId = products.get(products.size() - 1).getId() + 1;
		product.setId(dummyId);
		service.save(product);

		model.addFlashAttribute("alert", true);
		model.addFlashAttribute("alertMessage", "Success save a product.");
		return "redirect:/product";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchProduct(@ModelAttribute("criteria") ProductCriteria criteria, Model model) {
		logger.debug("Search product with: " + criteria.toString());
		
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
		model.addFlashAttribute("alert", true);
		model.addFlashAttribute("alertMessage", "Success update a product.");
		return "redirect:/product";
	}

	// TODO change request method to post
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable Long id, RedirectAttributes model) {
		logger.debug("delete product with ID: " + id);

		service.delete(id);
		model.addFlashAttribute("alert", true);
		model.addFlashAttribute("alertMessage", "Success delete a product.");
		return "redirect:/product";
	}

	// FIXME remove these method when the service is ready

	private List<Product> emptyList() {
		return new ArrayList<Product>();
	}
}
