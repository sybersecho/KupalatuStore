package com.ta.toko.module.supplier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ta.toko.module.common.AbstractServiceTest;
import com.ta.toko.module.sales.SalesService;
import com.ta.toko.module.sales.model.ProductLine;
import com.ta.toko.module.sales.model.SalesInformation;
import com.ta.toko.test.dummy.SupplierDummies;

public class SalesServiceTest extends AbstractServiceTest {
	private static Logger logger = LoggerFactory.getLogger(SalesServiceTest.class);

	@Autowired
	private SupplierDummies dummies;
	@Autowired
	private SalesService salesService;
	private SalesInformation salesInformation;

	@Override
	public void setup() {
		dummies.createSupplierAndProduct();

		salesInformation = new SalesInformation();
		salesInformation.setSalesDate(new Date());
		salesInformation.setNo("123");
		salesInformation.setTotalSales(BigDecimal.valueOf(1000));
		salesInformation.setProductLines(new ArrayList<ProductLine>());
		ProductLine pLine = new ProductLine();
		pLine.setQuantity(100);
		pLine.setProduct(dummies.getProduct(1));
		pLine.setSubTotal(pLine.getProduct().getSalesPrice().multiply(BigDecimal.valueOf(pLine.getQuantity())));
		salesInformation.getProductLines().add(pLine);

	}

	@Test
	public void testSave() {
		salesService.save(salesInformation);

	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}

}
