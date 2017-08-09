package com.ta.toko.module.purchase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ta.toko.entity.Address;
import com.ta.toko.entity.Product;
import com.ta.toko.entity.Purchase;
import com.ta.toko.entity.Supplier;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {
	private static Logger logger = LoggerFactory.getLogger(PurchaseController.class);

	public PurchaseController() {
		logger.debug("Purchase Controller created");
		supplierDummies();
		productDummies();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {
		logger.debug("Purchase detail show");
		model.addAttribute("purchased", new Purchase());
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("actionUrl", "/purchase/product");
		return "purchase/purchase-detail";
	}

	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public String showInputProductPage(@ModelAttribute("purchased") Purchase purchased, BindingResult result, Model model) {
		logger.debug("Show input products");
		 model.addAttribute("form", purchased);
		model.addAttribute("actionUrl", "");
		return "purchase/purchase-product";
	}

	List<Supplier> suppliers = new ArrayList<Supplier>();

	private void supplierDummies() {
		for (int i = 1; i <= 10; i++) {
			Supplier s = new Supplier();
			s.setCode("c00" + i);
			s.setContact("contact " + i);
			s.setEmail(1 + "test@tes.com");
			s.setId(Long.valueOf(i));
			s.setName("Supplier " + i);

			Address addr = new Address();
			addr.setId(Long.valueOf(i));
			addr.setCity("City " + i);
			addr.setLine1("Line 1 " + i);

			s.setSupplierAddress(addr);
			suppliers.add(s);
		}
	}

	private List<Product> products = new ArrayList<Product>();

	private void productDummies() {
		for (int i = 1; i <= 10; i++) {
			Product p = new Product();
			p.setBarcode("ABC12" + i);
			p.setDescription("Description of " + i);
			p.setId(Long.valueOf(i));
			p.setName("Name " + i);
			p.setQuantity(0);
			p.setSalesPrice(BigDecimal.ZERO);

			products.add(p);
		}
	}
}
