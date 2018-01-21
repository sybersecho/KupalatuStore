package com.ta.toko.dao;

import java.util.Date;
import java.util.List;

import com.ta.toko.entity.Purchase;

public interface PurchaseDao extends BaseDao<Purchase> {

	List<Purchase> getReport(Date from, Date to);

}
