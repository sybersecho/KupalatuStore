package com.ta.toko.module.supplier;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ta.toko.dao.SupplierDao;
import com.ta.toko.entity.Supplier;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {
	private static Logger logger = LoggerFactory.getLogger(SupplierServiceImpl.class);

	@Autowired
	private SupplierDao dao;

	// @Autowired
	public SupplierServiceImpl() {
		logger.debug("supplier service created");
		// this.dao = dao;
	}

	@Override
	@Transactional
	public List<Supplier> getAll() {
		logger.debug("supplier service get all");
		return dao.getAll();
	}

	@Override
	@Transactional
	public Supplier findById(long idSupplier) {
		logger.debug("supplier service find by id: " + idSupplier);
		return dao.get(idSupplier);
	}

	@Override
	@Transactional
	public void save(Supplier newSupplier) {
		logger.debug("supplier service save new supplier");
		dao.save(newSupplier);

	}

	@Override
	@Transactional
	public void update(Supplier supplier) {
		logger.debug("supplier service update supplier with id: " + supplier.getId());
		dao.update(supplier);

	}

	@Override
	@Transactional
	public void delete(long deleteId) {
		logger.debug("supplier service delete supplier with id: " + deleteId);
		dao.delete(findById(deleteId));

	}

	@Override
	@Transactional
	public void deleteAll() {
		logger.debug("supplier service delete all suppliers");
		dao.deleteAll();

	}

}
