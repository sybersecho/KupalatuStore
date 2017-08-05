package com.ta.toko.module.supplier.implementation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ta.toko.module.common.entity.Address;
import com.ta.toko.module.supplier.entity.Supplier;
import com.ta.toko.module.supplier.interfaces.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {
	private static Logger logger = LoggerFactory.getLogger(SupplierServiceImpl.class);

	public SupplierServiceImpl() {
		logger.debug("supplier service created");
		dummies();
	}

	@Override
	public List<Supplier> getAll() {
		logger.debug("supplier service get all");
		return this.suppliers;
	}

	@Override
	public Supplier findById(long idSupplier) {
		logger.debug("supplier service find by id: " + idSupplier);
		return getDummyById(idSupplier);
	}

	@Override
	public void save(Supplier newSupplier) {
		logger.debug("supplier service save new supplier");
		suppliers.add(newSupplier);

	}

	@Override
	public void update(Supplier supplier) {
		logger.debug("supplier service update supplier with id: " + supplier.getId());
		updateDummy(supplier);

	}

	@Override
	public void delete(long deleteId) {
		logger.debug("supplier service delete supplier with id: " + deleteId);
		deleteDummy(deleteId);

	}

	@Override
	public void deleteAll() {
		logger.debug("supplier service delete all suppliers");
		suppliers.clear();

	}

	private List<Supplier> suppliers = new ArrayList<Supplier>();

	private void dummies() {
		for (int i = 0; i < 12; i++) {
			Supplier supplier = new Supplier();
			supplier.setId(Long.valueOf(i));
			supplier.setName("Product " + (i + 1));
			supplier.setCode("S00" + (i + 1));
			supplier.setContact("Contact " + (i + 1));
			supplier.setEmail((i + 1) + "test@test.com");

			Address supplierAddress = new Address();
			supplierAddress.setCity("city" + (i + 1));
			supplierAddress.setLine1("Line " + (i + 1));
			supplierAddress.setPostCode("12345" + (i + 1));
			supplierAddress.setState("state" + (i + 1));
			supplier.setSupplierAddress(supplierAddress);

			suppliers.add(supplier);
		}
	}

	private Supplier getDummyById(long i) {
		for (Supplier supplier : suppliers) {
			if (supplier.getId() == i) {
				return supplier;
			}
		}
		return new Supplier();
	}

	private void updateDummy(Supplier supplier) {
		for (int i = 0; i < suppliers.size(); i++) {
			if (supplier.getId() == suppliers.get(i).getId()) {
				suppliers.remove(i);
				suppliers.add(i, supplier);
			}
		}
	}

	private void deleteDummy(long id) {
		for (int i = 0; i < suppliers.size(); i++) {
			if (suppliers.get(i).getId() == id) {
				suppliers.remove(i);
			}
		}
	}

}
