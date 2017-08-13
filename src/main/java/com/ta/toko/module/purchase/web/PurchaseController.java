package com.ta.toko.module.purchase.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ta.toko.entity.Address;
import com.ta.toko.entity.Product;
import com.ta.toko.entity.Supplier;
import com.ta.toko.module.product.web.ProductLineAddValidator;
import com.ta.toko.module.purchase.model.ProductLineInfo;
import com.ta.toko.module.purchase.model.PurchaseConstant;
import com.ta.toko.module.purchase.model.PurchaseInfo;
import com.ta.toko.module.purchase.model.PurchaseSessionUtil;

@Controller
@SessionAttributes(PurchaseConstant.SESSION_NAME)
@RequestMapping("/purchase")
public class PurchaseController {
	private static Logger logger = LoggerFactory.getLogger(PurchaseController.class);

	@Autowired
	PurchaseDetailValidator detailValidator;
	@Autowired
	ProductLineSearchValidator productSearchValidator;
	@Autowired
	ProductLineAddValidator productAddValidator;

	public PurchaseController() {
		logger.debug("Purchase Controller created");
		supplierDummies();
		productDummies();

	}

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model, HttpSession session) {
		logger.debug("Purchase detail show");
		PurchaseInfo inSession = PurchaseSessionUtil.getPurchaseInSession(session);

		model.addAttribute(PurchaseConstant.SESSION_NAME, inSession);
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("actionUrl", "/purchase/next/product");

		return "purchase/purchase-detail";
	}

	@RequestMapping(value = "/clear", method = RequestMethod.GET)
	public String clear(Model model, HttpSession session, SessionStatus status) {
		logger.debug("Clear all fields and purchase in session");

		status.setComplete();

		return "redirect:/purchase";
	}

	@RequestMapping(value = "/next/product", method = RequestMethod.POST)
	public String showPurchasedProduct(@ModelAttribute(PurchaseConstant.SESSION_NAME) PurchaseInfo purchase,
			BindingResult result, RedirectAttributes redirect, Model model, HttpServletRequest request) {
		logger.debug("showPurchasedProduct");

		detailValidator.validate(purchase, result);
		if (result.hasErrors()) {
			model.addAttribute("suppliers", suppliers);
			print(model, request, null);
			return "purchase/purchase-detail";
		}

		redirect.addFlashAttribute("productLine", new ProductLineInfo());
		redirect.addFlashAttribute("actionUrl", "/purchase/next/confirm");
		return "redirect:/purchase/product";
	}

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String showInputProductPage(Model model, HttpSession session) {
		logger.debug("Show product details");
		if (!model.containsAttribute("productLine")) {
			model.addAttribute("productLine", new ProductLineInfo());
		}

		if (!model.containsAttribute("actionUrl")) {
			model.addAttribute("actionUrl", "/purchase/next/confirm");
		}

		return "purchase/purchase-product";
	}

	@RequestMapping(value = "/next/confirm", method = RequestMethod.POST)
	public String showSearchProductPage(@RequestParam("action") String action,
			@ModelAttribute("productLine") ProductLineInfo productLine, BindingResult result, HttpSession session,
			RedirectAttributes redirectModel, Model model, HttpServletRequest request) {
		logger.debug("Show result search products page");

		if (action.equals("search")) {
			logger.debug("action is search");
			productSearchValidator.validate(productLine, result);
			if (result.hasErrors()) {
				return "purchase/purchase-product";
			}
			// TODO CALL SERVICE TO RETRIEVE PRODUCT BASE ON CRITERIA
			model.addAttribute("products", products);
			model.addAttribute("productLine", productLine);
			return "purchase/search-product";
		} else if (action.equals("add")) {
			logger.debug("action is add");

			productAddValidator.validate(productLine, result);
			if (result.hasErrors()) {
				// print(model, request, session);
				return "purchase/purchase-product";
			}

			PurchaseInfo p = PurchaseSessionUtil.getPurchaseInSession(session);
			calculatedTotal(productLine);
			addProductLineToPurchase(p, productLine);
			// p.getProductLineInfos().add(productLine);
			productLine = new ProductLineInfo();
			redirectModel.addFlashAttribute("productLine", new ProductLineInfo());
			redirectModel.addFlashAttribute(PurchaseConstant.SESSION_NAME, p);
			redirectModel.addFlashAttribute("actionUrl", "/purchase/next/confirm");
			return "redirect:/purchase/product";
		}
		return "purchase/search-product";
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public String selectedProduct(HttpSession session, @PathVariable Long id,
			@ModelAttribute("productLine") ProductLineInfo productLine, BindingResult result,
			RedirectAttributes model) {
		logger.debug("product with Id " + id + " selected");
		// get product by id selected:
		Product p = getById(id);
		// show selected product on screen
		logger.debug("Prod id: " + p.getId());
		productLine.setProduct(p);
		model.addFlashAttribute("productLine", productLine);
		model.addFlashAttribute("actionUrl", "/purchase/next/confirm");
		return "redirect:/purchase/product";
	}

	@RequestMapping(value = "/select/line/{index}", method = RequestMethod.GET)
	public String selectedProductLine(@PathVariable("index") int index, HttpSession session,
			RedirectAttributes redirect) {
		logger.debug("Select product info index: " + index);
		// get purchased
		PurchaseInfo pInfo = PurchaseSessionUtil.getPurchaseInSession(session);
		// get product line info
		ProductLineInfo pLine = pInfo.getProductLineInfos().get(index - 1);

		redirect.addFlashAttribute("productLine", pLine);
		redirect.addFlashAttribute("actionUrl", "/purchase/next/confirm");

		return "redirect:/purchase/product";
	}

	@RequestMapping(value = "/delete/line/{index}", method = RequestMethod.GET)
	public String deleteSelectedLine(@PathVariable("index") int index, HttpSession session,
			RedirectAttributes redirect) {
		logger.debug("delete product line index: " + index);
		// get purchased
		PurchaseInfo pInfo = PurchaseSessionUtil.getPurchaseInSession(session);
		// get product line info
		pInfo.getProductLineInfos().remove(index - 1);

		redirect.addFlashAttribute("productLine", new ProductLineInfo());
		redirect.addFlashAttribute("actionUrl", "/purchase/next/confirm");
		return "redirect://purchase/product";
	}

	private void calculatedTotal(ProductLineInfo productLine) {
		BigDecimal tot = productLine.getPurchasePrice().multiply(BigDecimal.valueOf(productLine.getQuantity()));
		productLine.setTotalItem(tot);
	}

	private void addProductLineToPurchase(PurchaseInfo purchased, ProductLineInfo productLine) {
		// check if index is not 0
		int lineIndex = productLine.getIndex();
		logger.debug("line of index: " + lineIndex);
		if (lineIndex > 0) {
			purchased.getProductLineInfos().remove(lineIndex - 1);
			purchased.getProductLineInfos().add(lineIndex - 1, productLine);
		} else {
			int sizeOfLines = purchased.getProductLineInfos().size();
			logger.debug("size of lines: " + sizeOfLines);
			productLine.setIndex(sizeOfLines + 1);
			logger.debug("set line position to: " + productLine.getIndex());
			purchased.getProductLineInfos().add(productLine);
		}

	}

	private Product getById(Long id) {
		for (Product product : products) {
			if (product.getId() == id) {
				return product;
			}
		}
		return null;
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

	@SuppressWarnings({ "rawtypes", "unused" })
	private void print(Model model, HttpServletRequest request, HttpSession session) {
		if (model != null) {
			logger.debug("model data");
			Map modelMap = model.asMap();
			for (Object modelKey : modelMap.keySet()) {
				Object modelValue = modelMap.get(modelKey);
				logger.debug(modelKey + " -- " + modelValue);
			}
		}

		if (request != null) {
			logger.debug("=== Request data ===");
			Enumeration<String> reqEnum = request.getAttributeNames();
			while (reqEnum.hasMoreElements()) {
				String s = reqEnum.nextElement();
				logger.debug(s);
				logger.debug("==" + request.getAttribute(s));
			}
		}

		if (session != null) {
			logger.debug("*** Session data ***");
			Enumeration<String> e = session.getAttributeNames();
			while (e.hasMoreElements()) {
				String s = e.nextElement();
				logger.debug(s);
				logger.debug("**" + session.getAttribute(s));
			}
		}
	}
}
