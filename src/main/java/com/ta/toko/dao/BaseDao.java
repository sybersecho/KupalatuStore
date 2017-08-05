package com.ta.toko.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T extends Serializable> {
	void save(T t);

	T get(Serializable id);

	T load(Serializable id);

	List<T> getAll();

	void update(T t);

	void delete(T t);

	void deleteById(Serializable id);

	void deleteAll();

	long count();

	boolean exists(Serializable id);

}
