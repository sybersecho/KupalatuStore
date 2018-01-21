package com.ta.toko.module.purchase;

import java.util.Date;
import java.util.List;

import com.ta.toko.module.purchase.model.PurchaseInfo;

public interface PurchaseService {
	public void save(PurchaseInfo purchase);

	public List<PurchaseInfo> searchPurchase(Date from, Date to);
}
