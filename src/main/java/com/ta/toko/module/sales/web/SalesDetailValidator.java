package com.ta.toko.module.sales.web;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ta.toko.module.sales.model.SalesInformation;

@Component
public class SalesDetailValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SalesInformation.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SalesInformation salesInformation = (SalesInformation) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "no", "NotEmpty.sales.no");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "salesDate", "NotEmpty.sales.salesDate");

		if (isInFuture(salesInformation)) {
			errors.rejectValue("salesDate", "NotInfuture.sales.salesDate");
		}

		if (salesInformation.getProductLines().size() <= 0) {
			errors.rejectValue("productLines", "NotNull.productline");
		}

	}

	private boolean isInFuture(SalesInformation salesInformation) {
		return salesInformation.getSalesDate().compareTo(new Date()) > 0;
	}

}
