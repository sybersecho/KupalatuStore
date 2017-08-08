package com.ta.toko.module.product;

public class ProductCriteria {
	private String barcode;
	private String name;

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Barcode: ");
		builder.append(this.barcode);
		builder.append(", ");
		builder.append("Name: ");
		builder.append(this.name);
		return builder.toString();
	}

}
