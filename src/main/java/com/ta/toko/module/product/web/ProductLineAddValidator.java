package com.ta.toko.module.product.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ta.toko.module.purchase.model.ProductLineInfo;

@Component
public class ProductLineAddValidator implements Validator {
	private static Logger logger = LoggerFactory.getLogger(ProductLineAddValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return ProductLineInfo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProductLineInfo productLine = (ProductLineInfo) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "product.name", "NotEmpty.purchase.product.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "product.barcode", "NotEmpty.purchase.product.barcode");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "NotEmpty.purchase.quantity");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "purchasePrice", "NotEmpty.purchase.purchasePrice");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sellingPrice",
		// "NotEmpty.purchase.product.barcode");
		logger.debug("Product Id: " + productLine.getProduct().getId());
		if (productLine.getProduct().getId() == null) {
			logger.debug("product has not been selected");
			errors.rejectValue("product.id", "search.purchase.product");
		}
		
		if(productLine.getQuantity() <= 0 ) {
			logger.debug("product quantity below 0");
			errors.rejectValue("quantity", "NotBelowZero.purchase.quantity");
		}
		
		if(productLine.getPurchasePrice() != null && productLine.getPurchasePrice().intValue() <= 0 ) {
			logger.debug("purchasePrice below 0");
			errors.rejectValue("quantity", "NotBelowZero.purchase.purchasePrice");
		}
		
		if(productLine.getProduct().getSalesPrice() != null && productLine.getProduct().getSalesPrice().intValue() <= 0) {
			logger.debug("salesPrice below 0");
			errors.rejectValue("product.salesPrice", "NotBelowZero.purchase.product.salesPrice");
		}

	}

}
