package com.ta.toko.module.purchase.web;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ta.toko.module.purchase.model.PurchaseInfo;

@Component
public class PurchaseDetailValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PurchaseInfo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PurchaseInfo purchase = (PurchaseInfo) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "purchaseNo", "NotEmpty.purchase.purchaseNo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "purchaseDate", "NotEmpty.purchase.purchaseDate");

		
		if (isPurchaseDateNullOrStringEmpty(purchase) ) {
			errors.rejectValue("purchaseDate", "NotEmpty.purchase.purchaseDate");
		}

		if (!isPurchaseDateNullOrStringEmpty(purchase) && isInFuture(purchase)) {
			errors.rejectValue("purchaseDate", "NotInfuture.purchase.purchaseDate");
		}
	}

	private boolean isInFuture(PurchaseInfo purchase) {
		return purchase.getPurchaseDate().compareTo(new Date()) > 0;
	}

	private boolean isPurchaseDateNullOrStringEmpty(PurchaseInfo purchase) {
		return purchase.getPurchaseDate() == null || purchase.getPurchaseDate().toString().isEmpty();
	}

}
