package com.ta.toko.test.dummy;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ta.toko.entity.Address;
import com.ta.toko.entity.Product;
import com.ta.toko.entity.Supplier;
import com.ta.toko.module.product.ProductService;
import com.ta.toko.module.supplier.SupplierService;

@Component
public class SupplierDummies {
	private static Logger logger = LoggerFactory.getLogger(SupplierDummies.class);
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private ProductService productService;
	private boolean isLoaded = false;

	public SupplierDummies() {
		logger.debug("supplier dumies loaded!!!");
		// this.service = service;
		// saveDummies();
	}

	public void createSupplierAndProduct() {
		if (!isLoaded) {
			createSuppliers();
			createProducts();
			isLoaded = true;
		}
	}

	public Supplier getSupplier(int index) {
		return getSuppliers().get(index - 1);
	}

	public List<Supplier> getSuppliers() {
		return supplierService.getAll();
	}

	public Product getProduct(int index) {
		return getProducts().get(index - 1);
	}

	public List<Product> getProducts() {
		return productService.getAll();
	}

	private void createSuppliers() {
		for (int i = 1; i <= 10; i++) {
			Supplier s = new Supplier();
			s.setCode("c00" + i);
			s.setContact("contact " + i);
			s.setEmail(1 + "test@tes.com");
			// s.setId(Long.valueOf(i));
			s.setName("Supplier " + i);

			Address addr = new Address();
			// addr.setId(Long.valueOf(i));
			addr.setCity("City " + i);
			addr.setLine1("Line 1 " + i);
			addr.setState("State " + i);
			addr.setPostCode("1234" + i);

			s.setSupplierAddress(addr);
			supplierService.save(s);
		}
	}

	private void createProducts() {
		for (int i = 1; i <= 10; i++) {
			Product p = new Product();
			p.setBarcode("ABC12" + i);
			p.setDescription("Description of " + i);
			p.setId(Long.valueOf(i));
			p.setName("Name " + i);
			p.setUnit("Unit " + i);
			p.setQuantity(0);
			p.setSalesPrice(BigDecimal.valueOf(10).multiply(BigDecimal.valueOf(i)));

			productService.save(p);
		}
	}
}
