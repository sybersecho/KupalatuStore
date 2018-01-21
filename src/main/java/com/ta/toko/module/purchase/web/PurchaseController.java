package com.ta.toko.module.purchase.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ta.toko.entity.Product;
import com.ta.toko.entity.Supplier;
import com.ta.toko.module.login.CustomUserDetails;
import com.ta.toko.module.product.ProductCriteria;
import com.ta.toko.module.product.ProductService;
import com.ta.toko.module.purchase.PurchaseService;
import com.ta.toko.module.purchase.PurchaseSessionUtil;
import com.ta.toko.module.purchase.model.PurchaseConstant;
import com.ta.toko.module.purchase.model.PurchaseInfo;
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
	PurchaseSearchProductValidator searchValidator;
	@Autowired
	private PurchaseAddProductValidator addProductValidator;
	@Autowired
	SupplierDummies dummies;
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private ProductService productService;
	@Autowired
	private PurchaseService purchaseService;

	List<Supplier> suppliers = new ArrayList<Supplier>();

	public PurchaseController() {
		logger.debug("Purchase Controller created");
	}

	@ModelAttribute("purchaseActive")
	public String purchaseActive() {
		return "active";
	}

	@ModelAttribute("user")
	public CustomUserDetails getUser() {
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return user;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model, HttpSession session) {
		logger.debug("Purchase detail show");
		PurchaseInfo purchaseInSession = PurchaseSessionUtil.getPurchaseInSession(session);
		dummies.createSupplierAndProduct();
		suppliers = supplierService.getAll();
		model.addAttribute(PurchaseConstant.SESSION_NAME, purchaseInSession);
		model.addAttribute("suppliers", suppliers);
		logger.info("line info: " + purchaseInSession.getProductLineInfos().size());
		logger.info("Date: " + purchaseInSession.getPurchaseDate());
		return "purchase/purchase-detail";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String purchaseDetail(@Valid @ModelAttribute(PurchaseConstant.SESSION_NAME) PurchaseInfo purchase,
			BindingResult result, @RequestParam("action") String action, Model model, HttpServletRequest request) {
		// SessionUtil.print(model, request, null);
		logger.info(purchase.getPurchaseDate() + "");
		if (action.equals("search")) {
			return searchProduct(model, purchase, result);
		} else if (action.equals("add")) {
			return addLineToPurchase(purchase, result, model);
		} else {
			detailValidator.validate(purchase, result);
			if (result.hasErrors()) {
				model.addAttribute("suppliers", suppliers);
				return "purchase/purchase-detail";
			}
			return "redirect:/purchase/confirm";
		}

	}

	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public String confirmPurchased(Model model, HttpSession session) {
		logger.debug("Confirmation page show");
		PurchaseInfo purchased = PurchaseSessionUtil.getPurchaseInSession(session);

		model.addAttribute("supplier", purchased.getSupplier());
		model.addAttribute("purchased", purchased);
		return "purchase/confirmed";
	}

	@RequestMapping(value = "/select/line/{index}", method = RequestMethod.GET)
	public String selectedProductLine(@PathVariable("index") int index, HttpSession session) {
		logger.debug("Select product info index: " + index);
		// get purchased
		PurchaseInfo purchased = PurchaseSessionUtil.getPurchaseInSession(session);
		purchased.selectProductLine(index);

		return "redirect:/purchase";
	}

	@RequestMapping(value = "/delete/line/{index}", method = RequestMethod.GET)
	public String deleteSelectedLine(@PathVariable("index") int index, HttpSession session) {
		logger.debug("delete product line index: " + index);
		// get purchased
		PurchaseInfo purchased = PurchaseSessionUtil.getPurchaseInSession(session);
		purchased.removeLineAt(index - 1);
		return "redirect:/purchase";
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public String selectedProduct(HttpSession session, @PathVariable Long id, @RequestParam("index") int index,
			Model model) {
		logger.debug("product with Id " + id + " selected");

		Product p = productService.findById(id);
		// show selected product on screen
		logger.debug("Product id: " + p.getId());

		PurchaseInfo purchaseInSession = PurchaseSessionUtil.getPurchaseInSession(session);
		purchaseInSession.setProduct(p);
		logger.info("date: {}", purchaseInSession.getPurchaseDate());
		return "redirect:/purchase";
	}

	@RequestMapping(value = "/confirmed", method = RequestMethod.GET)
	public String submitPurchased(RedirectAttributes model, HttpSession session, SessionStatus status) {
		logger.debug("Submit purchased");
		PurchaseInfo purchased = PurchaseSessionUtil.getPurchaseInSession(session);
		purchaseService.save(purchased);
		status.setComplete();
		PurchaseSessionUtil.removePurchaseInfoInSession(session);
		model.addFlashAttribute("purchaseSaved", true);

		return "redirect:/purchase";
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public String cancelSearchProduct() {
		return "redirect:/purchase";
	}

	@RequestMapping(value = "/clear", method = RequestMethod.GET)
	public String clear(HttpSession session, SessionStatus status) {
		logger.debug("clear purchased");
		status.setComplete();
		PurchaseSessionUtil.removePurchaseInfoInSession(session);
		return "redirect:/purchase";
	}

	private String searchProduct(Model model, PurchaseInfo purchase, BindingResult result) {
		logger.debug("action is search");

		searchValidator.validate(purchase, result);
		if (result.hasErrors()) {
			model.addAttribute("suppliers", suppliers);
			return "purchase/purchase-detail";
		}

		ProductCriteria productCriteria = new ProductCriteria();
		if (purchase.getProductName() != null) {
			productCriteria.setName(purchase.getProductName());
		}

		if (purchase.getProductBarcode() != null) {
			productCriteria.setBarcode(purchase.getProductBarcode());
		}

		model.addAttribute(PurchaseConstant.SESSION_NAME, purchase);

		model.addAttribute("products", productService.search(productCriteria));
		model.addAttribute("index", -1);
		return "purchase/search-product";
	}

	private String addLineToPurchase(PurchaseInfo purchase, BindingResult result, Model model) {
		logger.debug("action is add");
		addProductValidator.validate(purchase, result);
		if (result.hasErrors()) {
			model.addAttribute("suppliers", suppliers);
			return "purchase/purchase-detail";
		}

		purchase.addProductToLine();
		return "redirect:/purchase";
	}

}
