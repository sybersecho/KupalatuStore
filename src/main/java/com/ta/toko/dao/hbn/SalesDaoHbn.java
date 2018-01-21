package com.ta.toko.dao.hbn;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ta.toko.dao.SalesDao;
import com.ta.toko.entity.Sales;

@Repository
public class SalesDaoHbn extends AbstractDaoHbn<Sales> implements SalesDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Sales> getReport(Date from, Date to) {
		Criteria criteria = getSession().createCriteria(Sales.class);
		criteria.add(Restrictions.ge("saleDate", from));
		criteria.add(Restrictions.le("saleDate", to));
		return criteria.list();
	}

}
