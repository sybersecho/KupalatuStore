package com.ta.toko.module.product.web;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ta.toko.module.product.ProductCriteria;

@Component
public class ProductSearchValidator implements Validator {
//	private static Logger logger = LoggerFactory.getLogger(ProductSearchValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return ProductCriteria.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProductCriteria criteria = (ProductCriteria) target;
		
		if (isBarcodeEmpty(criteria) && isNameEmpty(criteria)) {
			errors.rejectValue("barcode", "NotEmpty.criteria.barcode");
			errors.rejectValue("name", "NotEmpty.criteria.name");
		}
	}

	private boolean isNameEmpty(ProductCriteria criteria) {
		return criteria.getName().isEmpty() || criteria.getName() == "";
	}

	private boolean isBarcodeEmpty(ProductCriteria criteria) {
		return criteria.getBarcode().isEmpty() || criteria.getBarcode() == "";
	}

}
