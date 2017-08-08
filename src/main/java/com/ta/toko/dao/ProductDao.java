package com.ta.toko.dao;

import java.util.List;

import com.ta.toko.entity.Product;
import com.ta.toko.module.product.ProductCriteria;

public interface ProductDao extends BaseDao<Product> {
	public List<Product> search(ProductCriteria criteria);
}
