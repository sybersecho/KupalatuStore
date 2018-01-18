package com.ta.toko.module.sales.web;

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
import com.ta.toko.entity.User;
import com.ta.toko.module.login.CustomUserDetails;
import com.ta.toko.module.product.ProductCriteria;
import com.ta.toko.module.product.ProductService;
import com.ta.toko.module.sales.SalesService;
import com.ta.toko.module.sales.SalesSessionUtil;
import com.ta.toko.module.sales.model.SalesConstant;
import com.ta.toko.module.sales.model.SalesInformation;
import com.ta.toko.util.SessionUtil;

@Controller
@SessionAttributes(SalesConstant.SESSION_NAME)
@RequestMapping("/sales")
public class SalesController {
	private static Logger logger = LoggerFactory.getLogger(SalesController.class);

	// private SalesInformation currentSales;
	@Autowired
	private SalesSearchProductValidator searchValidator;
	@Autowired
	private SalesAddProductValidator addValidator;
	@Autowired
	private SalesDetailValidator detailValidator;
	@Autowired
	private ProductService productService;
	@Autowired
	private SalesService salesService;
	// private ProductLine temp;

	public SalesController() {
		logger.debug("Sales Controller created");
	}

	@ModelAttribute("salesActive")
	public String salesActive() {
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
		logger.debug("Sales home");

		SalesInformation currentSales = SalesSessionUtil.getSalesInSession(session);
		model.addAttribute(SalesConstant.SESSION_NAME, currentSales);
		// model.addAttribute("salesActive", "active");

		return "sales/sales";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String searchProduct(@Valid @ModelAttribute(SalesConstant.SESSION_NAME) SalesInformation sales,
			BindingResult result, @RequestParam("action") String action, Model model, HttpSession session,
			HttpServletRequest request, SessionStatus status, RedirectAttributes redirectModel) {
		SessionUtil.print(model, request, session);

		if (action.equals("search")) {
			logger.info("action search");
			return searchProduct(sales, model, result);
		} else if (action.equals("add")) {
			logger.info("action add");
			return addLineToSales(sales, result);
		}

		detailValidator.validate(sales, result);
		if (result.hasErrors()) {
			return "sales/sales";
		}

		salesService.save(sales);
		status.setComplete();
		SalesSessionUtil.removeSalesInSession(session);

		redirectModel.addFlashAttribute("saleSaved", true);

		return "redirect:/sales";
	}

	private String addLineToSales(SalesInformation sales, BindingResult result) {
		addValidator.validate(sales, result);
		if (result.hasErrors()) {
			return "sales/sales";
		}

		sales.addProductToLine();

		return "redirect:/sales";
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public String selectProduct(@PathVariable Long id, @RequestParam("i") int index, HttpSession session, Model model) {
		logger.debug("product with Id " + id + " selected");

		SalesInformation information = SalesSessionUtil.getSalesInSession(session);
		Product selectedProduct = productService.findById(id);

		information.setProduct(selectedProduct);

		return "redirect:/sales";
	}

	@RequestMapping(value = "/delete/line/{index}", method = RequestMethod.GET)
	public String deleteSelectedLine(@PathVariable("index") int index, HttpSession session) {
		logger.debug("delete product line index: " + index);
		SalesInformation currentSales = SalesSessionUtil.getSalesInSession(session);
		currentSales.removeProductLine(index - 1);
		return "redirect:/sales";
	}

	@RequestMapping(value = "/select/line/{index}", method = RequestMethod.GET)
	public String SelecteLine(@PathVariable("index") int index, HttpSession session) {
		logger.debug("select line index: " + index);
		// temp = currentSales.getProductLines().get(index - 1);
		SalesInformation currentSales = SalesSessionUtil.getSalesInSession(session);
		currentSales.selectLine(index);

		return "redirect:/sales";
	}

	private String searchProduct(SalesInformation sales, Model model, BindingResult result) {

		searchValidator.validate(sales, result);
		if (result.hasErrors()) {
			return "sales/sales";
		}

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
