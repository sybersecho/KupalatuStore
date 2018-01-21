package com.ta.toko.module.sales;

import java.util.Date;
import java.util.List;

import com.ta.toko.module.sales.model.SalesInformation;

public interface SalesService {
	public void save(SalesInformation sales);

	public List<SalesInformation> searchSales(Date from, Date to);
}
