package com.ta.toko.module.supplier;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import com.ta.toko.entity.Address;
import com.ta.toko.entity.Supplier;

public class StringToSupplierConverter implements Converter<String, Supplier> {
	private static Logger logger = LoggerFactory.getLogger(StringToSupplierConverter.class);

	public StringToSupplierConverter() {
		logger.debug("StringToSupplierConverter created");
		supplierDummies();
	}

	@Override
	public Supplier convert(String source) {
		logger.debug("conver called");

		return findById(Long.valueOf(source));
	}

	List<Supplier> suppliers = new ArrayList<Supplier>();

	private void supplierDummies() {
		for (int i = 1; i <= 10; i++) {
			Supplier s = new Supplier();
			s.setCode("c00" + i);
			s.setContact("contact " + i);
			s.setEmail(1 + "test@tes.com");
			s.setId(Long.valueOf(i));
			s.setName("Supplier " + i);

			Address addr = new Address();
			addr.setId(Long.valueOf(i));
			addr.setCity("City " + i);
			addr.setLine1("Line 1 " + i);
			addr.setState("State " + i);

			s.setSupplierAddress(addr);
			suppliers.add(s);
		}
	}

	private Supplier findById(long id) {
		for (Supplier supplier : suppliers) {
			if (supplier.getId() == id) {
				return supplier;
			}
		}
		return null;
	}

}
