package com.ta.toko.dao.hbn;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ta.toko.dao.PurchaseDao;
import com.ta.toko.entity.Purchase;

@Repository
public class PurchaseDaoHbn extends AbstractDaoHbn<Purchase> implements PurchaseDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Purchase> getReport(Date from, Date to) {
		Criteria criteria = getSession().createCriteria(Purchase.class);
		criteria.add(Restrictions.ge("purchaseDate", from));
		criteria.add(Restrictions.le("purchaseDate", to));
		return criteria.list();
	}

}
