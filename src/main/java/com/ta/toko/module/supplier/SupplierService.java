package com.ta.toko.module.supplier;

import java.util.List;

import com.ta.toko.entity.Supplier;

public interface SupplierService {
	public List<Supplier> getAll();

	public Supplier findById(long idSupplier);

	public void save(Supplier newSupplier);

	public void update(Supplier supplier);

	public void delete(long deleteId);

	public void deleteAll();
}
