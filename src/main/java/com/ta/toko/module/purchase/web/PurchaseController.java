package com.ta.toko.module.purchase.web;

import java.util.ArrayList;
import java.util.List;

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

import com.ta.toko.entity.Product;
import com.ta.toko.entity.Supplier;
import com.ta.toko.module.product.ProductCriteria;
import com.ta.toko.module.product.ProductService;
import com.ta.toko.module.product.web.ProductLineAddValidator;
import com.ta.toko.module.purchase.model.ProductLineInfo;
import com.ta.toko.module.purchase.model.PurchaseConstant;
import com.ta.toko.module.purchase.model.PurchaseInfo;
import com.ta.toko.module.purchase.model.PurchaseSessionUtil;
import com.ta.toko.module.supplier.SupplierService;
import com.ta.toko.test.dummy.SupplierDummies;
import com.ta.toko.util.SessionUtil;

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
	@Autowired
	SupplierDummies dummies;
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private ProductService productService;
	
	List<Supplier> suppliers = new ArrayList<Supplier>();

	public PurchaseController() {
		logger.debug("Purchase Controller created");
		// supplierDummies();
		// dummies.saveDummies();
		// productDummies();

	}

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model, HttpSession session) {
		logger.debug("Purchase detail show");
		PurchaseInfo inSession = PurchaseSessionUtil.getPurchaseInSession(session);
		dummies.createSupplierAndProduct();
		suppliers = supplierService.getAll();
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
			model.addAttribute("suppliers", supplierService.getAll());
			SessionUtil.print(model, request, null);
			return "purchase/purchase-detail";
		}

		redirect.addFlashAttribute("productLine", new ProductLineInfo());
		redirect.addFlashAttribute("actionUrl", "/purchase/next/confirm");
		redirect.addFlashAttribute("index", "-1");
		return "redirect:/purchase/product";
	}

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String showInputProductPage(Model model, HttpSession session, HttpServletRequest request) {
		logger.debug("Show product details");
		if (!model.containsAttribute("productLine")) {
			model.addAttribute("productLine", new ProductLineInfo());
		}

		if (!model.containsAttribute("actionUrl")) {
			model.addAttribute("actionUrl", "/purchase/next/confirm");
		}

		PurchaseInfo purchased = PurchaseSessionUtil.getPurchaseInSession(session);
		if (purchased.getSupplier().getId() != null) {
			long supplierId = purchased.getSupplier().getId();
			purchased.setSupplier(supplierService.findById(supplierId));
		}

		SessionUtil.print(model, request, session);

		return "purchase/purchase-product";
	}

	@RequestMapping(value = "/next/confirm", method = RequestMethod.POST)
	public String showSearchProductPage(@RequestParam("action") String action,
			@ModelAttribute("productLine") ProductLineInfo productLine, BindingResult result, HttpSession session,
			RedirectAttributes redirectModel, Model model, HttpServletRequest request) {
		logger.debug("Show result search products page");
		int index = (int) (request.getParameter("index") == "" ? -1 : Integer.parseInt(request.getParameter("index")));
		PurchaseInfo purchased = PurchaseSessionUtil.getPurchaseInSession(session);
		if (action.equals("search")) {
			logger.debug("action is search");
			productSearchValidator.validate(productLine, result);
			if (result.hasErrors()) {
				return "purchase/purchase-product";
			}
			// TODO CALL SERVICE TO RETRIEVE PRODUCT BASE ON CRITERIA
			ProductCriteria productCriteria = new ProductCriteria();
			if (productLine.getProduct().getName() != null) {
				productCriteria.setName(productLine.getProduct().getName());
			}

			if (productLine.getProduct().getBarcode() != null) {
				productCriteria.setBarcode(productLine.getProduct().getBarcode());
			}
			redirectModel.addFlashAttribute("products", productService.search(productCriteria));
			redirectModel.addFlashAttribute("index", index);
			redirectModel.addFlashAttribute("productLine", productLine);
			// put current selected info into session
			session.setAttribute("productLine", productLine);
			return "redirect:/purchase/search";
			//return "purchase/search-product";
		} else if (action.equals("add")) {
			logger.debug("action is add");

			productAddValidator.validate(productLine, result);
			if (result.hasErrors()) {
				return "purchase/purchase-product";
			}

			productLine.calculateTotalItem();
			logger.debug("Line total item: " + productLine.getTotalItem());
			if (index > 0) {
				purchased.updateLineAt(index - 1, productLine);
			} else {
				purchased.addLineInfo(productLine);
			}

			redirectModel.addFlashAttribute("productLine", new ProductLineInfo());
			redirectModel.addFlashAttribute(PurchaseConstant.SESSION_NAME, purchased);
			redirectModel.addFlashAttribute("actionUrl", "/purchase/next/confirm");
			return "redirect:/purchase/product";
		}

		logger.debug("Action is submit");

		if (purchased.getProductLineInfos().isEmpty()) {
			redirectModel.addFlashAttribute("isEmpty", true);
			return "redirect:/purchase/product";
		}

		return "redirect:/purchase/confirm";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchProduct(Model model, HttpSession session) {
		return "purchase/search-product";
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public String confirmPurchased(Model model, HttpSession session) {
		logger.debug("Confirmation page show");
		PurchaseInfo purchased = PurchaseSessionUtil.getPurchaseInSession(session);

		model.addAttribute("supplier", purchased.getSupplier());
		model.addAttribute("purchased", purchased);
		return "purchase/confirmed";
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String submitPurchased(Model model, HttpSession session, SessionStatus sessionStatus) {
		logger.debug("Submit purchased");
		PurchaseInfo purchased = PurchaseSessionUtil.getPurchaseInSession(session);
		SessionUtil.print(model, null, session);
		sessionStatus.setComplete();

		return "redirect:/purchase";
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public String selectedProduct(HttpSession session, @PathVariable Long id, @RequestParam("index") int index,
			RedirectAttributes model) {
		logger.debug("product with Id " + id + " selected");
		ProductLineInfo pInfo = (ProductLineInfo) session.getAttribute("productLine");
		logger.debug("pInfo before: " + pInfo.toString());

		// TODO SERVICE SHOULD CALL A METHOD TO RETRIVE A PRODUCT
		// get product by id selected:
		Product p = productService.findById(id);
		// show selected product on screen
		logger.debug("Product id: " + p.getId());
		p.setSalesPrice(pInfo.getProduct().getSalesPrice());
		pInfo.setProduct(p);

		model.addFlashAttribute("productLine", pInfo);
		model.addFlashAttribute("actionUrl", "/purchase/next/confirm");
		model.addFlashAttribute("index", index);

		// remove selected product info from session
		session.removeAttribute("productLine");

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
		redirect.addFlashAttribute("index", index);
		redirect.addFlashAttribute("actionUrl", "/purchase/next/confirm");

		return "redirect:/purchase/product";
	}

	@RequestMapping(value = "/delete/line/{index}", method = RequestMethod.GET)
	public String deleteSelectedLine(@PathVariable("index") int index, HttpSession session,
			RedirectAttributes redirect) {
		logger.debug("delete product line index: " + index);
		// get purchased
		PurchaseInfo purchased = PurchaseSessionUtil.getPurchaseInSession(session);
		// get product line info
		logger.debug("Current Total Purchased: " + purchased.getTotalPurchased());
		purchased.removeLineAt(index - 1);
		logger.debug("Current Total Purchased: " + purchased.getTotalPurchased());

		redirect.addFlashAttribute("productLine", new ProductLineInfo());
		redirect.addFlashAttribute("actionUrl", "/purchase/next/confirm");
		return "redirect:/purchase/product";
	}

}
