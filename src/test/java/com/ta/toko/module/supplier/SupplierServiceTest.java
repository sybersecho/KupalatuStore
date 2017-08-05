package com.ta.toko.module.supplier;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ta.toko.module.common.AbstractServiceTest;
import com.ta.toko.module.supplier.interfaces.SupplierService;

public class SupplierServiceTest extends AbstractServiceTest {
	
	private static Logger logger = LoggerFactory.getLogger(SupplierServiceTest.class);

	@Autowired
	private SupplierService service;

	@Override
	public void setup() {
		// TODO what need to do
		logger.debug("test supplier service setup");
	}

	@Test
	public void getAllTest() {
		logger.debug("test supplier service get all");
		assertNotNull(service.getAll());
	}

	@Test
	public void findByIdTest() {
		logger.debug("test supplier service find by id");
		assertNotNull(service.findById(1L));
	}

	public void save() {
		logger.debug("test supplier service save");
	}

	public void update() {
		logger.debug("test supplier service update");
	}

	public void delete() {
		logger.debug("test supplier service delete");
	}

	public void deleteAll() {
		logger.debug("test supplier service delete all");
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		logger.debug("test supplier service tear down");
	}
}
