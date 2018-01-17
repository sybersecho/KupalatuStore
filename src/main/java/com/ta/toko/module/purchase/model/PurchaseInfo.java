package com.ta.toko.module.purchase.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ta.toko.entity.Product;
import com.ta.toko.entity.Supplier;

public class PurchaseInfo implements Serializable {

	private static Logger logger = LoggerFactory.getLogger(PurchaseInfo.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String purchaseNo;
	private Date purchaseDate;// = dateWithoutTime();
	private Supplier supplier;
	private String details;
	private BigDecimal totalPurchased = BigDecimal.ZERO;
	private List<ProductLineInfo> productLineInfos = new ArrayList<ProductLineInfo>();
	private ProductLineInfo productLine = new ProductLineInfo();
	private boolean isEdit = false;
	private int index = -1;

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

	public void setProductPrice(BigDecimal salesPrice) {
		productLine.setSalePrice(salesPrice);
	}

	public BigDecimal getProductPrice() {
		return productLine.getSalePrice();
	}

	public void setQuantity(int quantity) {
		productLine.setQuantity(quantity);
	}

	public int getQuantity() {
		return productLine.getQuantity();
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		productLine.setPurchasePrice(purchasePrice);
	}

	public BigDecimal getPurchasePrice() {
		return productLine.getPurchasePrice();
	}

	public ProductLineInfo getProductLine() {
		return productLine;
	}

	public void setProductLine(ProductLineInfo productLine) {
		this.productLine = productLine;
	}

	public PurchaseInfo() {
		logger.info("Purchase info created");
	}

	public boolean isEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
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
		builder.append("Supplier.: " + (this.supplier == null ? ("id is null") : this.supplier.getId()));
		if (this.productLineInfos != null && !this.productLineInfos.isEmpty()) {
			for (ProductLineInfo productLineInfo : productLineInfos) {
				builder.append("\n\tProduct: " + productLineInfo.getProduct().getName());
				builder.append(", ");
				builder.append("Quantity: " + productLineInfo.getQuantity());
			}
		}
		return builder.toString();
	}

	public void addLineInfo(ProductLineInfo lineInfo) {
		addTotalPurchase(lineInfo.getTotalItem());
		productLineInfos.add(lineInfo);
	}

	public void removeLineAt(int index) {
		subtractTotalPurchase(productLineInfos.get(index).getTotalItem());
		productLineInfos.remove(index);
	}

	public void updateLineAt(int index, ProductLineInfo updatedLine) {
		removeLineAt(index);
		addLineAt(index, updatedLine);
	}

	public void addLineAt(int index, ProductLineInfo lineInfo) {
		addTotalPurchase(lineInfo.getTotalItem());
		productLineInfos.add(index, lineInfo);
	}

	private void addTotalPurchase(BigDecimal totalItem) {
		BigDecimal total = getTotalPurchased().add(totalItem);
		setTotalPurchased(total);
	}

	private void subtractTotalPurchase(BigDecimal with) {
		BigDecimal total = getTotalPurchased().subtract(with);
		setTotalPurchased(total);
	}

	public BigDecimal getTotalPurchased() {
		return totalPurchased;
	}

	public void setTotalPurchased(BigDecimal totalPurchased) {
		this.totalPurchased = totalPurchased;
	}

	public void addProductToLine() {
		this.productLine.calculateTotalItem();
//		logger.info("total item : " + this.productLine.getTotalItem());
//		logger.info("current line size: " + productLineInfos.size());
		if (isEdit) {
			updateLineAt(index - 1, productLine);
		} else {
			addLineInfo(productLine);
		}
//		logger.info("current line size: " + productLineInfos.size());
		productLine = new ProductLineInfo();

	}

	public void setProduct(Product p) {
		productLine.setProduct(p);
	}

	public void selectProductLine(int index) {
		productLine = productLineInfos.get(index - 1);
		setIndex(index);
		setEdit(true);

	}

}
