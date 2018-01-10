package com.ta.toko.module.sales.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalesInformation {

	private Date date = new Date();
	private String no;
	private BigDecimal totalSales = BigDecimal.ZERO;
	private List<ProductLine> productLines = new ArrayList<ProductLine>();
	private ProductLine editedProduct;
	private int editPosition = -1;
	private boolean isEdited = false;

	public void addProductLine(ProductLine newLine) {
		this.productLines.add(newLine);
		this.editedProduct = new ProductLine();
		this.totalSales = this.totalSales.add(newLine.getSubTotal());
		this.editPosition = -1;
		this.isEdited = false;
	}

	public void removeProductLine(int index) {
		ProductLine temp = this.productLines.get(index);
		this.totalSales = this.totalSales.subtract(temp.getSubTotal());
		this.productLines.remove(index);

	}

	public void selectLine(int index) {
		ProductLine temp = this.productLines.get(index);
		this.editedProduct = temp;
		this.editPosition = index;
		this.isEdited = true;

	}

	public ProductLine getEditedProduct() {
		return editedProduct;
	}

	public void setEditedProduct(ProductLine editedProduct) {
		this.editedProduct = editedProduct;
	}

	public int getEditPosition() {
		return editPosition;
	}

	public void setEditPosition(int editPosition) {
		this.editPosition = editPosition;
	}

	public boolean isEdited() {
		return isEdited;
	}

	public void setEdited(boolean isEdited) {
		this.isEdited = isEdited;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(BigDecimal totalSales) {
		this.totalSales = totalSales;
	}

	public List<ProductLine> getProductLines() {
		return productLines;
	}

	public void setProductLines(List<ProductLine> productLines) {
		this.productLines = productLines;
	}

	public void updateProductLine(ProductLine productLine) {
		this.totalSales = this.totalSales.subtract(productLine.getSubTotal());
		this.totalSales = this.totalSales.add(editedProduct.getSubTotal());
		this.editedProduct = new ProductLine();
		this.editPosition = -1;
		this.isEdited = false;

	}

}
