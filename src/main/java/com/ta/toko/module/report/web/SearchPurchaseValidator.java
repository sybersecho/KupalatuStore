package com.ta.toko.module.report.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ta.toko.module.report.Model.ReportPurchase;

@Component
public class SearchPurchaseValidator implements Validator {

	private static Logger logger = LoggerFactory.getLogger(SearchPurchaseValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return ReportPurchase.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ReportPurchase validate = (ReportPurchase) target;

		if (isNotNull(validate) && validate.getFromDate().compareTo(validate.getToDate()) > 0) {
			logger.debug("the from date is lower than the to date");
			errors.rejectValue("strFromDate", "notLower.purchase.fromDate");
			errors.rejectValue("strToDate", "notLower.purchase.toDate");
		}

	}

	private boolean isNotNull(ReportPurchase validate) {
		return validate.getFromDate() != null && validate.getToDate() != null;
	}

}
