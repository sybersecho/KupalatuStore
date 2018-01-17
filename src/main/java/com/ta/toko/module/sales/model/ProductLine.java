package com.ta.toko.module.sales.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ta.toko.entity.Product;

public class ProductLine implements Serializable {
	
	private static Logger logger = LoggerFactory.getLogger(ProductLine.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Product product;
	private int quantity;
	private BigDecimal subTotal;
	// private String no;
	// private Date date;

	// public String getNo() {
	// return no;
	// }
	//
	// public void setNo(String no) {
	// this.no = no;
	// }

	public ProductLine() {
		this.product = new Product();
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

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

	public void calculateSubTotal() {
		setSubTotal(getProduct().getSalesPrice().multiply(BigDecimal.valueOf(quantity)));
	}

}
