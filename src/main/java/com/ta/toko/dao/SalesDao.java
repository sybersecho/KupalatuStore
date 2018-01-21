package com.ta.toko.dao;

import java.util.Date;
import java.util.List;

import com.ta.toko.entity.Sales;

public interface SalesDao extends BaseDao<Sales> {

	List<Sales> getReport(Date from, Date to);

}
