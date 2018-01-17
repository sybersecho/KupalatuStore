package com.ta.toko.dao.hbn;

import org.springframework.stereotype.Repository;

import com.ta.toko.dao.SalesDao;
import com.ta.toko.entity.Sales;

@Repository
public class SalesDaoHbn extends AbstractDaoHbn<Sales> implements SalesDao {

}
