package com.ta.toko.module.product.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ta.toko.entity.Product;
import com.ta.toko.module.product.ProductCriteria;

@Controller
@RequestMapping("/product")
public class ProductController {
	private static Logger logger = LoggerFactory.getLogger(ProductController.class);

	// @Autowired
	public ProductController() {
		logger.info("ProductController created");
		dummies();
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
		if(result.hasErrors()) {
			logger.debug("product has error");
			return "product/product";
		}
		products.add(product);
		model.addFlashAttribute("alert", true);
		return "redirect:/product";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchProduct(@ModelAttribute("criteria") ProductCriteria criteria, Model model) {
		logger.debug("Search product with barcode: " + criteria.getBarcode() + " and Name: " + criteria.getName());
		model.addAttribute("products", products);
		model.addAttribute("actionUrl", "/product/search");
		return "product/product-entries";
	}

	private List<Product> products = new ArrayList<Product>();

	private void dummies() {
		for (int i = 1; i <= 15; i++) {
			Product p = new Product();
			p.setId(Long.valueOf(i));
			p.setBarcode("123123123" + i);
			p.setDescription("description of object " + i);
			p.setName("Product " + i);
			p.setQuantity(0);
			p.setSalesPrice(BigDecimal.ZERO);
			p.setUnit("PCS");

			products.add(p);
		}
	}

	private List<Product> emptyList() {
		return new ArrayList<Product>();
	}
}
