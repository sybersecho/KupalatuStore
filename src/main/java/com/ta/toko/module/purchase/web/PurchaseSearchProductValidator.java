package com.ta.toko.module.purchase.web;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ta.toko.module.purchase.model.PurchaseInfo;

@Component
public class PurchaseSearchProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PurchaseInfo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PurchaseInfo purchase = (PurchaseInfo) target;

		if (isBarcodeAndNameEmpty(purchase)) {
			errors.rejectValue("productName", "NotEmpty.purchase.product.name");
			errors.rejectValue("productBarcode", "NotEmpty.purchase.product.barcode");
		}

	}

	private boolean isBarcodeAndNameEmpty(PurchaseInfo productLine) {

		return isBarcodeEmpty(productLine) && isNameEmpty(productLine);
	}

	private boolean isNameEmpty(PurchaseInfo purchase) {
		return purchase.getProductName().isEmpty() || (purchase.getProductName() == "");
	}

	private boolean isBarcodeEmpty(PurchaseInfo purchase) {
		return purchase.getProductBarcode().isEmpty() || (purchase.getProductBarcode() == "");
	}

}
