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

		
		if (isPurchaseDateNull(purchase)) {
			errors.rejectValue("purchaseDate", "NotEmpty1.purchase.purchaseDate");
		}

		if (!isPurchaseDateNull(purchase) && isInFuture(purchase)) {
			errors.rejectValue("purchaseDate", "NotInfuture.purchase.purchaseDate");
		}
	}

	private boolean isInFuture(PurchaseInfo purchase) {
		return purchase.getPurchaseDate().compareTo(new Date()) > 0;
	}

	private boolean isPurchaseDateNull(PurchaseInfo purchase) {
		return purchase.getPurchaseDate() == null;
	}

}
