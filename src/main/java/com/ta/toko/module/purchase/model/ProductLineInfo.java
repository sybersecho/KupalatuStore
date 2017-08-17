package com.ta.toko.module.purchase.model;

import java.math.BigDecimal;

import com.ta.toko.entity.Product;

public class ProductLineInfo {
	private Product product;
	private int quantity;
	private BigDecimal purchasePrice;
	private BigDecimal totalItem = BigDecimal.ZERO;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public BigDecimal getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(BigDecimal totalItem) {
		this.totalItem = totalItem;
	}

	public void calculateTotalItem() {
		setTotalItem(getPurchasePrice().multiply(BigDecimal.valueOf(getQuantity())));
	}

}
