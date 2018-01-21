package com.ta.toko.module.report.Model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ta.toko.module.sales.model.ProductLine;
import com.ta.toko.module.sales.model.SalesInformation;

public class ReportSales {
	private Date fromDate = new Date();
	private Date toDate = new Date();
	private List<SalesInformation> sales = new ArrayList<SalesInformation>();
	private int productCount;
	private int salesQty;
	private BigDecimal totalSales;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public String getStrFromDate() {
		return formatter.format(getFromDate());
	}

	public void setStrFromDate(String strFromDate) {
		try {
			setFromDate(formatter.parse(strFromDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getStrToDate() {
		return formatter.format(getToDate());
	}

	public void setStrToDate(String strToDate) {
		try {
			setToDate(formatter.parse(strToDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public List<SalesInformation> getSales() {
		return sales;
	}

	public void setSales(List<SalesInformation> sales) {
		this.sales = sales;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public int getSalesQty() {
		return salesQty;
	}

	public void setSalesQty(int salesQty) {
		this.salesQty = salesQty;
	}

	public BigDecimal getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(BigDecimal totalSales) {
		this.totalSales = totalSales;
	}

	public void getDetails() {
		setSalesQty(0);
		setProductCount(0);
		setTotalSales(BigDecimal.ZERO);

		if (!sales.isEmpty()) {
			for (SalesInformation s : sales) {
				for (ProductLine sLine : s.getProductLines()) {
					setProductCount(getProductCount() + 1);
					setSalesQty(getSalesQty() + sLine.getQuantity());
				}
				setTotalSales(getTotalSales().add(s.getTotalSales()));
			}
		}

	}

}
