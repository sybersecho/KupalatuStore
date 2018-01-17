package com.ta.toko.dao.hbn;

import org.springframework.stereotype.Repository;

import com.ta.toko.dao.PurchaseDao;
import com.ta.toko.entity.Purchase;

@Repository
public class PurchaseDaoHbn extends AbstractDaoHbn<Purchase> implements PurchaseDao {

}
