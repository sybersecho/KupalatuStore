package com.ta.toko.module.purchase.web;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ta.toko.module.purchase.model.ProductLineInfo;

@Component
public class ProductLineSearchValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ProductLineInfo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProductLineInfo productLine = (ProductLineInfo) target;

		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "product.name",
		// "NotEmpty.purchase.product.name");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "product.barcode",
		// "NotEmpty.purchase.product.barcode");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity",
		// "NotEmpty.purchase.product.barcode");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "purchasePrice",
		// "NotEmpty.purchase.product.barcode");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sellingPrice",
		// "NotEmpty.purchase.product.barcode");

		if (isBarcodeAndNameEmpty(productLine)) {
			errors.rejectValue("product.barcode", "NotEmpty.purchase.product.barcode");
			errors.rejectValue("product.name", "NotEmpty.purchase.product.name");
		}

	}

	private boolean isBarcodeAndNameEmpty(ProductLineInfo productLine) {

		return isBarcodeEmpty(productLine) && isNameEmpty(productLine);
	}

	private boolean isNameEmpty(ProductLineInfo productLine) {
		return productLine.getProduct().getName().isEmpty() || (productLine.getProduct().getName() == "");
	}

	private boolean isBarcodeEmpty(ProductLineInfo productLine) {
		return productLine.getProduct().getBarcode().isEmpty() || (productLine.getProduct().getBarcode() == "");
	}

}
