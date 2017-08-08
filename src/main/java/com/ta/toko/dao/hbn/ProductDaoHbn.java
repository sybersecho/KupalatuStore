package com.ta.toko.dao.hbn;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ta.toko.dao.ProductDao;
import com.ta.toko.entity.Product;
import com.ta.toko.module.product.ProductCriteria;

@Repository
public class ProductDaoHbn extends AbstractDaoHbn<Product> implements ProductDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> search(ProductCriteria criteria) {
		// TODO Auto-generated method stub
		Criteria hbnCriteria = getSession().createCriteria(Product.class);
		if(!criteria.getBarcode().isEmpty() || criteria.getBarcode() != "") {
			hbnCriteria.add(Restrictions.eq("barcode", criteria.getBarcode()));
		}
		
		if(!criteria.getName().isEmpty() || criteria.getName() != "") {
			hbnCriteria.add(Restrictions.eq("name", criteria.getName()));
		}
		return hbnCriteria.list();
	}

}
