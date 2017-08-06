package com.ta.toko.module.product.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ta.toko.entity.Product;

@Controller
@RequestMapping("/product")
public class ProductController {
	private static Logger logger = LoggerFactory.getLogger(ProductController.class);
	
//	@Autowired
	public ProductController() {
		logger.info("ProductController created");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String homePage(Model model) {
		logger.debug("product home page called..");
//		model.addAttribute("suppliers", service.getAll());
		return "product/product-entries";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addProductPage(Model model) {
		logger.debug("add product home page called..");
		model.addAttribute("product", new Product());
		return "product/product";
	}
}
