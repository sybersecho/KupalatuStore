package com.ta.toko.module.sales.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ta.toko.entity.Product;

public class SalesInformation implements Serializable {

	private static Logger logger = LoggerFactory.getLogger(SalesInformation.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date salesDate = new Date();
	private String no;
	private BigDecimal totalSales = BigDecimal.ZERO;
	private List<ProductLine> productLines = new ArrayList<ProductLine>();
	private ProductLine productLine = new ProductLine();
	private int editPosition = -1;
	private boolean isEdited = false;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	public void setProductBarcode(String productBarcode) {
		productLine.getProduct().setBarcode(productBarcode);
	}

	public String getProductBarcode() {
		return productLine.getProduct().getBarcode();
	}

	public void setProductName(String productName) {
		productLine.getProduct().setName(productName);
	}

	public String getProductName() {
		return productLine.getProduct().getName();
	}

	public BigDecimal getSalesPrice() {
		return productLine.getProduct().getSalesPrice();
	}

	public void setQuantity(int quantity) {
		productLine.setQuantity(quantity);
	}

	public int getQuantity() {
		return productLine.getQuantity();
	}

	public void addProductLine(ProductLine newLine) {
		this.productLines.add(newLine);
		this.productLine = new ProductLine();
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
		this.productLine = this.productLines.get(index - 1);
		this.editPosition = index;
		this.isEdited = true;

	}

	public ProductLine getEditedProduct() {
		return productLine;
	}

	public void setEditedProduct(ProductLine editedProduct) {
		this.productLine = editedProduct;
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

	public Date getSalesDate() {
		return salesDate;
	}

	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}

	public String getCurrentDate() {
//		if(getSalesDate()== null) {
//			logger.info("sales date null");
//			setSalesDate(new Date());
//		}else {
//			logger.info("sales date not null");
//		}
		return formatter.format(getSalesDate());
	}

	public void setCurrentDate(String currentDate) {
		try {
			setSalesDate(formatter.parse(currentDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
		this.totalSales = this.totalSales.add(productLine.getSubTotal());
		this.productLine = new ProductLine();
		this.editPosition = -1;
		this.isEdited = false;

	}

	public void setProduct(Product selectedProduct) {
		productLine.setProduct(selectedProduct);
	}

	public void addProductToLine() {
		logger.info("sub total: {}", this.productLine.getSubTotal());
		if (isEdited) {
			logger.info("pos: {}", editPosition);
			updateLineAt(editPosition - 1, productLine);
		} else {
			this.productLine.calculateSubTotal();
			addLine(this.productLine);
		}

		this.productLine = new ProductLine();
	}

	private void addLine(ProductLine productLine) {
		calculateTotalSales(productLine.getSubTotal());
		productLines.add(productLine);
	}

	private void calculateTotalSales(BigDecimal subTotal) {
		setTotalSales(subTotal.add(getTotalSales()));
	}

	private void updateLineAt(int i, ProductLine productLine) {
		removeProductLine(i);
		productLine.calculateSubTotal();
		addLineAt(i, productLine);
		this.isEdited = false;
		this.editPosition = -1;
	}

	private void addLineAt(int i, ProductLine productLine) {
		calculateTotalSales(productLine.getSubTotal());
		productLines.add(i, productLine);

	}

}
