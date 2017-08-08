package com.ta.toko.module.product;

import static org.junit.Assert.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ta.toko.module.common.AbstractServiceTest;

public class ProductServiceTest extends AbstractServiceTest{
	
	private static Logger logger = LoggerFactory.getLogger(ProductServiceTest.class);

	@Override
	public void setup() {
		logger.debug("Setup Product Service Test");
		
	}

	@Override
	public void tearDown() {
		logger.debug("TearDown Product Service Test");
//		ser
	}

}
