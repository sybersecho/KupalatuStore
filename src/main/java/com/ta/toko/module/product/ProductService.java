package com.ta.toko.module.product;

import java.util.List;

import com.ta.toko.entity.Product;

public interface ProductService {
	public List<Product> getAll();

	public Product findById(long idProduct);

	public void save(Product newProduct);

	public void update(Product product);

	public void delete(long idProduct);

	public void deleteAll();
}
