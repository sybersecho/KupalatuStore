package com.ta.toko.module.report;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ta.toko.module.purchase.PurchaseService;
import com.ta.toko.module.purchase.model.PurchaseInfo;
import com.ta.toko.module.report.Model.ReportPurchase;
import com.ta.toko.module.report.Model.ReportSales;
import com.ta.toko.module.sales.SalesService;
import com.ta.toko.module.sales.model.SalesInformation;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private SalesService salesService;

	@Override
	public ReportPurchase searchPurchase(Date from, Date to) {
		List<PurchaseInfo> purchases = purchaseService.searchPurchase(from, to);

		ReportPurchase report = new ReportPurchase();
		report.setFromDate(from);
		report.setToDate(to);

		if (!purchases.isEmpty()) {
			report.setPurchased(purchases);
		}
		return report;
	}

	@Override
	public ReportSales searchSales(Date from, Date to) {
		List<SalesInformation> sales = salesService.searchSales(from, to);
		ReportSales report = new ReportSales();
		report.setFromDate(from);
		report.setToDate(to);
		
		if(!sales.isEmpty()) {
			report.setSales(sales);
		}
		
		return report;
	}

}
