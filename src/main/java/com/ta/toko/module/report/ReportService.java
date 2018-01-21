package com.ta.toko.module.report;

import java.util.Date;

import com.ta.toko.module.report.Model.ReportPurchase;
import com.ta.toko.module.report.Model.ReportSales;

public interface ReportService {
	public ReportPurchase searchPurchase(Date from, Date to);
	public ReportSales searchSales(Date from, Date to);
}
