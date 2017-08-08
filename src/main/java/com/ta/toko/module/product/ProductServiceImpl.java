package com.ta.toko.module.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ta.toko.entity.Product;

@Service
// @Transactional
public class ProductServiceImpl implements ProductService {

	private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	public ProductServiceImpl() {
		logger.debug("Product Service instance created");
		dummies();
	}

	@Override
	public List<Product> getAll() {
		logger.debug("product service call getAll method");
		return products;
	}

	@Override
	public Product findById(long idProduct) {
		logger.debug("product service call findById method with parameter (ID): " + idProduct);
		return findPById(idProduct);
	}

	@Override
	public void save(Product newProduct) {
		logger.debug("product service call save method");
		products.add(newProduct);
	}

	@Override
	public void update(Product product) {
		logger.debug("product service call update method");
		updateProduct(product);
	}

	@Override
	public void delete(long idProduct) {
		logger.debug("product service call delete method");
		deleteProduct(idProduct);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Product> search(ProductCriteria criteria) {
		logger.debug("product service call search with criteria: " + criteria.toString());
		return products;
	}

	private List<Product> products = new ArrayList<Product>();

	private void dummies() {
		for (int i = 1; i <= 15; i++) {
			Product p = new Product();
			p.setId(Long.valueOf(i));
			p.setBarcode("123123123" + i);
			p.setDescription("description of object " + i);
			p.setName("Product " + i);
			p.setQuantity(0);
			p.setSalesPrice(BigDecimal.ZERO);
			p.setUnit("PCS");

			products.add(p);
		}
	}

	private Product findPById(long id) {
		Product p = new Product();
		for (Product product : products) {
			if (product.getId() == id) {
				return product;
			}
		}
		return p;
	}

	private void updateProduct(Product updatedP) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == updatedP.getId()) {
				products.remove(i);
				products.add(i, updatedP);
				break;
			}
		}
	}

	private void deleteProduct(long id) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == id) {
				products.remove(i);
				break;
			}
		}
	}

}
