package com.ta.toko.module.product;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ta.toko.dao.ProductDao;
import com.ta.toko.entity.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	private ProductDao dao;

	@Autowired
	public ProductServiceImpl(ProductDao dao) {
		logger.debug("Product Service instance created");
		this.dao = dao;
	}

	@Override
	public List<Product> getAll() {
		logger.debug("product service call getAll method");
		return dao.getAll();
	}

	@Override
	public Product findById(long idProduct) {
		logger.debug("product service call findById method with parameter (ID): " + idProduct);
		return dao.get(idProduct);
	}

	@Override
	public void save(Product newProduct) {
		logger.debug("product service call save method");
		dao.save(newProduct);
	}

	@Override
	public void update(Product product) {
		logger.debug("product service call update method");
		dao.update(product);
	}

	@Override
	public void delete(long idProduct) {
		logger.debug("product service call delete method");
		dao.delete(findById(idProduct));
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();

	}

	@Override
	public List<Product> search(ProductCriteria criteria) {
		logger.debug("product service call search with criteria: " + criteria.toString());
		return dao.search(criteria);
	}

}
