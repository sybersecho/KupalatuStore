package com.ta.toko.module.report.Model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ta.toko.module.purchase.model.ProductLineInfo;
import com.ta.toko.module.purchase.model.PurchaseInfo;

public class ReportPurchase {
	private Date fromDate = new Date();
	private Date toDate = new Date();
	private List<PurchaseInfo> purchased = new ArrayList<PurchaseInfo>();
	private int productCount;
	private int purchaseQty;
	private BigDecimal totalPurchase;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	public List<PurchaseInfo> getPurchased() {
		return purchased;
	}

	public void setPurchased(List<PurchaseInfo> purchased) {
		this.purchased = purchased;
	}

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
		// this.fromDate = fromDate;
		try {
			setFromDate(formatter.parse(strFromDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getStrToDate() {
		return formatter.format(getToDate());
	}

	public void setStrToDate(String strToDate) {
		// this.toDate = toDate;
		try {
			setToDate(formatter.parse(strToDate));
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

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public int getPurchaseQty() {
		return purchaseQty;
	}

	public void setPurchaseQty(int purchaseQty) {
		this.purchaseQty = purchaseQty;
	}

	public BigDecimal getTotalPurchase() {
		return totalPurchase;
	}

	public void setTotalPurchase(BigDecimal totalPurchase) {
		this.totalPurchase = totalPurchase;
	}

	public void getDetails() {
		setPurchaseQty(0);
		setProductCount(0);
		setTotalPurchase(BigDecimal.ZERO);
		if (!purchased.isEmpty()) {
			for (PurchaseInfo pInfo : purchased) {
				for (ProductLineInfo pLineInfo : pInfo.getProductLineInfos()) {
					setProductCount(getProductCount() + 1);
					setPurchaseQty(getPurchaseQty() + pLineInfo.getQuantity());
				}
				setTotalPurchase(getTotalPurchase().add(pInfo.getTotalPurchased()));
			}

		}

	}

}
