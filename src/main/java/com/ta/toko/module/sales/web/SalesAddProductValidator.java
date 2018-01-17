package com.ta.toko.module.sales.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ta.toko.module.sales.model.SalesInformation;

@Component
public class SalesAddProductValidator implements Validator {

	private static Logger logger = LoggerFactory.getLogger(SalesAddProductValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return SalesInformation.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SalesInformation salesInformation = (SalesInformation) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName", "NotEmpty.purchase.product.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productBarcode", "NotEmpty.purchase.product.barcode");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "NotEmpty.purchase.quantity");

		if (salesInformation.getEditedProduct().getProduct().getId() == null) {
			logger.debug("product has not been selected");
			errors.rejectValue("productName", "NotNull.purchase.product");
			errors.rejectValue("productBarcode", "NotNull.purchase.product");
		} else {
			if (salesInformation.getQuantity() <= 0) {
				errors.rejectValue("quantity", "NotBelowZero.purchase.quantity");
			}
		}
	}

}
