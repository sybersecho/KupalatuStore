package com.ta.toko.module.sales.web;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ta.toko.entity.Product;
import com.ta.toko.module.product.ProductCriteria;
import com.ta.toko.module.product.ProductService;
import com.ta.toko.module.sales.model.ProductLine;
import com.ta.toko.module.sales.model.SalesConstant;
import com.ta.toko.module.sales.model.SalesInformation;

@Controller
@SessionAttributes(SalesConstant.SESSION_NAME)
@RequestMapping("/sales")
public class SalesController {
	private static Logger logger = LoggerFactory.getLogger(SalesController.class);

	private SalesInformation currentSales;
	@Autowired
	private ProductService productService;
	private ProductLine temp;

	public SalesController() {
		logger.debug("Sales Controller created");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {
		logger.debug("Sales home");

		if (currentSales == null) {
			currentSales = new SalesInformation();
		}
		model.addAttribute("sale", currentSales);

		return "sales/sales";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String searchProduct(@RequestParam("action") String action, @Valid SalesInformation sales,
			BindingResult result, Model model, HttpSession session) {
		logger.info("method post");

		if (action.equals("search")) {
			logger.info("action search");
			return searchProduct(sales, model);
		} else if (action.equals("add")) {
			logger.info("action add");
			ProductLine productLine = sales.getEditedProduct();
			productLine.setSubTotal(
					productLine.getProduct().getSalesPrice().multiply(BigDecimal.valueOf(productLine.getQuantity())));
			if (currentSales.isEdited()) {
				currentSales.updateProductLine(temp);
			} else {
				currentSales.addProductLine(productLine);
			}

			return "redirect:/sales";
		}

		return "sales/search-product";
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public String selectProduct(@PathVariable Long id, @RequestParam("i") int index, HttpSession session, Model model) {
		logger.debug("product with Id " + id + " selected");
		// currentSales
		// SalesInformation information = SalesSessionUtil.getSalesInSession(session);
		Product selectedProduct = productService.findById(id);
		if (currentSales.isEdited()) {
			currentSales.getEditedProduct().setProduct(selectedProduct);
		} else {
			ProductLine line = new ProductLine();
			line.setProduct(selectedProduct);
			currentSales.setEditedProduct(line);
		}

		// if (index >= 0) {
		// currentSales.setEdited(true);
		// currentSales.setEditPosition(index);
		// } else {
		// currentSales.setEdited(false);
		// currentSales.setEditPosition(-1);
		// }
		// logger.info("current size: " + currentSales.getProductLines().size());
		// // logger.info("information size: " + information.getProductLines().size());
		// model.addAttribute("sale", currentSales);

		return "redirect:/sales";
	}

	@RequestMapping(value = "/delete/line/{index}", method = RequestMethod.GET)
	public String deleteSelectedLine(@PathVariable("index") int index) {
		logger.debug("delete product line index: " + index);

		currentSales.removeProductLine(index - 1);
		return "redirect:/sales";
	}

	@RequestMapping(value = "/select/line/{index}", method = RequestMethod.GET)
	public String SelecteLine(@PathVariable("index") int index) {
		logger.debug("select line index: " + index);
		temp = currentSales.getProductLines().get(index - 1);
		currentSales.selectLine(index - 1);

		return "redirect:/sales";
	}

	private String searchProduct(SalesInformation sales, Model model) {
		// productSearchValidator.validate(productLine, result);
		// if (result.hasErrors()) {
		// return "purchase/purchase-product";
		// }
		ProductCriteria productCriteria = new ProductCriteria();
		Product temp = sales.getEditedProduct().getProduct();
		if (temp.getName() != null) {
			productCriteria.setName(temp.getName());
		}

		if (temp.getBarcode() != null) {
			productCriteria.setBarcode(temp.getBarcode());
		}

		model.addAttribute("products", productService.search(productCriteria));
		model.addAttribute("index", sales.getEditPosition());
		return "sales/search-product";
	}
}
