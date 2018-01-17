package com.ta.toko.module.supplier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ta.toko.module.common.AbstractServiceTest;
import com.ta.toko.module.purchase.PurchaseService;
import com.ta.toko.module.purchase.model.ProductLineInfo;
import com.ta.toko.module.purchase.model.PurchaseInfo;
import com.ta.toko.test.dummy.SupplierDummies;

public class PurchaseServiceTest extends AbstractServiceTest {

	private static Logger logger = LoggerFactory.getLogger(PurchaseServiceTest.class);

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private SupplierDummies dummies;
	private PurchaseInfo purchase;

	@Override
	public void setup() {
		dummies.createSupplierAndProduct();
		purchase = new PurchaseInfo();
		purchase.setDetails("test details");
		purchase.setPurchaseNo("No test");
		purchase.setPurchaseDate(new Date());
		// purchase.setPurchasePrice(BigDecimal.valueOf(1000));
		purchase.setSupplier(dummies.getSupplier(1));
		purchase.setTotalPurchased(BigDecimal.valueOf(1000));
		ProductLineInfo lineInfo = new ProductLineInfo();
		lineInfo.setProduct(dummies.getProduct(1));
		lineInfo.setPurchasePrice(BigDecimal.valueOf(100));
		lineInfo.setQuantity(10);
		lineInfo.setSalePrice(BigDecimal.valueOf(150));
		lineInfo.setTotalItem(lineInfo.getPurchasePrice().multiply(BigDecimal.valueOf(lineInfo.getQuantity())));
		List<ProductLineInfo> lineInfos = new ArrayList<ProductLineInfo>();
		lineInfos.add(lineInfo);
		purchase.setProductLineInfos(lineInfos);

	}

	@Test
	public void saveTest() {
		logger.info("***** save ******");
		purchaseService.save(purchase);
		logger.info("***** /save ******");
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}

}
