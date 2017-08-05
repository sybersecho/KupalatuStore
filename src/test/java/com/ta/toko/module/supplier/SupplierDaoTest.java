package com.ta.toko.module.supplier;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ta.toko.dao.SupplierDao;
import com.ta.toko.entity.Address;
import com.ta.toko.entity.Supplier;
import com.ta.toko.module.common.AbstractDaoTest;

public class SupplierDaoTest extends AbstractDaoTest {

	private static Logger logger = LoggerFactory.getLogger(SupplierDaoTest.class);
	
	@Autowired
	private SupplierDao supplierDao;

	@Override
	@Before
	@Transactional
	public void setup() {
		logger.info("setup new supplier");
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

		supplierDao.save(supplier);

	}

	@Test
	@Transactional
	public void getAll() {
		assertEquals(1, supplierDao.count());
	}

	@Override
	@After
	@Transactional
	public void tearDown() {
		logger.info("delete all");
		supplierDao.deleteAll();

	}

}
