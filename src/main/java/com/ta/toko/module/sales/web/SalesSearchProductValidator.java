package com.ta.toko.module.sales.web;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ta.toko.module.sales.model.SalesInformation;

@Component
public class SalesSearchProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SalesInformation.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SalesInformation sales = (SalesInformation) target;

		if (isBarcodeAndNameEmpty(sales)) {
			errors.rejectValue("productName", "NotEmpty.purchase.product.name");
			errors.rejectValue("productBarcode", "NotEmpty.purchase.product.barcode");
		}

	}

	private boolean isBarcodeAndNameEmpty(SalesInformation sales) {

		return isBarcodeEmpty(sales) && isNameEmpty(sales);
	}

	private boolean isNameEmpty(SalesInformation sales) {
		return sales.getProductName().isEmpty() || (sales.getProductName() == "");
	}

	private boolean isBarcodeEmpty(SalesInformation sales) {
		return sales.getProductBarcode().isEmpty() || (sales.getProductBarcode() == "");
	}

}
