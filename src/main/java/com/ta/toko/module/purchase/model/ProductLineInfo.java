package com.ta.toko.module.purchase.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ta.toko.entity.Product;

public class ProductLineInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Product product = new Product();
	private int quantity;
	private BigDecimal purchasePrice;
	private BigDecimal totalItem = BigDecimal.ZERO;
	private BigDecimal salePrice = BigDecimal.ZERO;

	public BigDecimal getSalePrice() {
		// this.salePrice = product.getSalesPrice();
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
		this.salePrice = product.getSalesPrice();
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product: " + (product == null ? "null" : product.getId()));
		builder.append(", ");
		builder.append("Quantity: " + quantity);
		builder.append(", ");
		builder.append("Purchase Price: " + purchasePrice);
		builder.append(", ");
		builder.append("total Item: " + totalItem);
		return builder.toString();
	}
}
