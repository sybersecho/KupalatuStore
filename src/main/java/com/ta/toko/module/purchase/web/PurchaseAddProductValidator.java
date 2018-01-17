package com.ta.toko.module.purchase.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ta.toko.module.purchase.model.PurchaseInfo;

@Component
public class PurchaseAddProductValidator implements Validator {

	private static Logger logger = LoggerFactory.getLogger(PurchaseAddProductValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return PurchaseInfo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PurchaseInfo purchase = (PurchaseInfo) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName", "NotEmpty.purchase.product.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productBarcode", "NotEmpty.purchase.product.barcode");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "NotEmpty.purchase.quantity");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "purchasePrice", "NotEmpty.purchase.purchasePrice");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productPrice", "NotEmpty.purchase.product.barcode");

		if (purchase.getProductLine().getProduct().getId() == null) {
			logger.debug("product has not been selected");
			errors.rejectValue("productName", "NotNull.purchase.product");
			errors.rejectValue("productBarcode", "NotNull.purchase.product");
		} else {
			if (purchase.getProductLine().getQuantity() <= 0) {
				errors.rejectValue("quantity", "NotBelowZero.purchase.quantity");
			}

			if (purchase.getPurchasePrice() != null && purchase.getPurchasePrice().intValue() <= 0) {
				errors.rejectValue("purchasePrice", "NotBelowZero.purchase.purchasePrice");
			}

			if (purchase.getProductPrice() != null && purchase.getProductPrice().intValue() <= 0) {
				errors.rejectValue("productPrice", "NotBelowZero.purchase.product.salesPrice");
			}
		}

	}

}
