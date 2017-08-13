package com.ta.toko.module.purchase.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ta.toko.entity.Supplier;

//@Component
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = WebApplicationContext.SCOPE_SESSION)
// @Scope("session")
public class PurchaseInfo implements Serializable {

	private static Logger logger = LoggerFactory.getLogger(PurchaseInfo.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String purchaseNo;
	private Date purchaseDate;
	private Supplier supplier;
	private String details;
	private List<ProductLineInfo> productLineInfos = new ArrayList<ProductLineInfo>();

	public PurchaseInfo() {
		logger.info("Purchase info created");
	}

	public String getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public List<ProductLineInfo> getProductLineInfos() {
		return productLineInfos;
	}

	public void setProductLineInfos(List<ProductLineInfo> productLineInfos) {
		this.productLineInfos = productLineInfos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Purchase No: " + this.purchaseNo);
		builder.append(", Purchase Date: " + this.purchaseDate);
		builder.append(", ");
		builder.append("Details: " + this.details);
		builder.append(", ");
		builder.append("Supplier: " + (this.supplier == null ? "null" : this.supplier.getName()));
		if (this.productLineInfos != null || !this.productLineInfos.isEmpty()) {
			for (ProductLineInfo productLineInfo : productLineInfos) {
				builder.append("\n\tProduct: " + productLineInfo.getProduct().getName());
				builder.append(", ");
				builder.append("Quantity: " + productLineInfo.getQuantity());
			}
		}
		return builder.toString();
	}

	public void addProduct() {

	}

}
