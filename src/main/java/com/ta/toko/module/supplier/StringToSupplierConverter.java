package com.ta.toko.module.supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ta.toko.entity.Supplier;

@Component
public class StringToSupplierConverter implements Converter<String, Supplier> {
	private static Logger logger = LoggerFactory.getLogger(StringToSupplierConverter.class);

	@Autowired
	private SupplierService supplierService;

	public StringToSupplierConverter() {
		logger.debug("StringToSupplierConverter created");
	}

	@Override
	public Supplier convert(String source) {
		logger.debug("convert source: " + source);

		return supplierService.findById(Long.valueOf(source));
	}

}
