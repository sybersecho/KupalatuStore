package com.ta.toko.module.supplier;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ta.toko.entity.Address;
import com.ta.toko.entity.Supplier;
import com.ta.toko.module.common.AbstractServiceTest;

public class SupplierServiceTest extends AbstractServiceTest {

	private static Logger logger = LoggerFactory.getLogger(SupplierServiceTest.class);

	@Autowired
	private SupplierService service;

	@Override
	@Before
	public void setup() {
		// TODO what need to do
		logger.debug("test supplier service setup");
		Supplier supplier = new Supplier();
		supplier.setName("Product 1");
		supplier.setCode("S001");
		supplier.setContact("Contact 1");
		supplier.setEmail("test@test.com");

		Address supplierAddress = new Address();
		supplierAddress.setCity("city");
		supplierAddress.setLine1("Line 1");
		supplierAddress.setPostCode("123456");
		supplierAddress.setState("state");
		supplier.setSupplierAddress(supplierAddress);

		service.save(supplier);
	}

	@Test
	public void getAllTest() {
		logger.debug("test supplier service get all");
		assertNotNull(service.getAll());
	}

	@Test
	public void findByIdTest() {
		logger.debug("test supplier service find by id");
		Supplier s = service.getAll().get(0);
		assertNotNull(service.findById(s.getId()));
	}

	public void save() {
		// TODO add test script
		logger.debug("test supplier service save");
	}

	public void update() {
		// TODO add test script
		logger.debug("test supplier service update");
	}

	public void delete() {
		// TODO add test script
		logger.debug("test supplier service delete");
	}

	public void deleteAll() {
		// TODO add test script
		logger.debug("test supplier service delete all");
	}

	@Override
	@After
	public void tearDown() {
		logger.debug("test supplier service tear down");
		service.deleteAll();
	}
}
