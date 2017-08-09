package com.ta.toko.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Purchase {
	private String pruchaseNo;
	private Date purchaseDate = new Date();
	private Supplier supplier;
	private String details;
	private BigDecimal totalPurchased;
	private PurchasedDetail tempPurcaseDetail = new PurchasedDetail();//transient
	private List<PurchasedDetail> purchasedDetails;

	public String getPruchaseNo() {
		return pruchaseNo;
	}

	public void setPruchaseNo(String pruchaseNo) {
		this.pruchaseNo = pruchaseNo;
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

	public BigDecimal getTotalPurchased() {
		return totalPurchased;
	}

	public void setTotalPurchased(BigDecimal totalPurchased) {
		this.totalPurchased = totalPurchased;
	}

	public PurchasedDetail getTempPurcaseDetail() {
		return tempPurcaseDetail;
	}

	public void setTempPurcaseDetail(PurchasedDetail tempPurcaseDetail) {
		this.tempPurcaseDetail = tempPurcaseDetail;
	}

	public List<PurchasedDetail> getPurchasedDetails() {
		return purchasedDetails;
	}

	public void setPurchasedDetails(List<PurchasedDetail> purchasedDetails) {
		this.purchasedDetails = purchasedDetails;
	}

}
