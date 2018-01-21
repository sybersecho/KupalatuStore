package com.ta.toko.module.report.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ta.toko.module.login.CustomUserDetails;
import com.ta.toko.module.report.ReportService;
import com.ta.toko.module.report.Model.ReportPurchase;
import com.ta.toko.module.report.Model.ReportSales;
import com.ta.toko.util.SessionUtil;

@Controller
@RequestMapping("/report")
public class ReportController {
	private static Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	private ReportService reportService;
	@Autowired
	private SearchPurchaseValidator purchaseValidator;
	@Autowired
	private SearchSalesValidator salesValidator;

	public ReportController() {
		logger.info("Report controller created");
	}

	@ModelAttribute("reportActive")
	public String reportActive() {
		return "active";
	}

	@ModelAttribute("user")
	public CustomUserDetails getUser() {
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return user;
	}

	@RequestMapping(value = "/purchase", method = RequestMethod.GET)
	public String showPurchaseReport(Model model) {
		model.addAttribute("report", new ReportPurchase());
		model.addAttribute("reportPurchaseActive", "active");

		return "report/purchase";
	}

	@RequestMapping(value = "/purchase", method = RequestMethod.POST)
	public String getPurchaseReport(@Valid @ModelAttribute("report") ReportPurchase report, BindingResult result,
			@RequestParam("action") String action, Model model, HttpServletRequest request) {
		SessionUtil.print(model, request, null);
		model.addAttribute("reportPurchaseActive", "active");
		purchaseValidator.validate(report, result);
		if (result.hasErrors()) {
			return "report/purchase";
		}

		if (action.equals("search")) {
			logger.info("action search");
			report = reportService.searchPurchase(report.getFromDate(), report.getToDate());
			report.getDetails();
			logger.info("purhcase size: {}", report.getPurchased().size());
			model.addAttribute("report", report);
		} else if (action.equals("clear")) {
			logger.info("action clear");
			model.addAttribute("report", new ReportPurchase());
			// return "report/purchase";
		}
		return "report/purchase";
	}

	@RequestMapping(value = "/sales", method = RequestMethod.GET)
	public String showSalesReport(Model model) {
		model.addAttribute("report", new ReportSales());
		model.addAttribute("reportSalesActive", "active");

		return "report/sales";
	}

	@RequestMapping(value = "/sales", method = RequestMethod.POST)
	public String getSalesReport(@Valid @ModelAttribute("report") ReportSales report, BindingResult result,
			@RequestParam("action") String action, Model model, HttpServletRequest request) {
		SessionUtil.print(model, request, null);
		model.addAttribute("reportSalesActive", "active");
		salesValidator.validate(report, result);
		if (result.hasErrors()) {
			return "report/sales";
		}

		if (action.equals("search")) {
			logger.info("action search");
			report = reportService.searchSales(report.getFromDate(), report.getToDate());
			report.getDetails();
			// logger.info("purhcase size: {}", report.getPurchased().size());
			model.addAttribute("report", report);
		} else if (action.equals("clear")) {
			logger.info("action clear");
			model.addAttribute("report", new ReportSales());
			// return "report/sales";
			// return addLineToSales(sales, result);
		}
		return "report/sales";
	}
}
