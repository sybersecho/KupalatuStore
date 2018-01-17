package com.ta.toko.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;

public interface BaseDao<T extends Serializable> {
	void save(T t);

	T get(Serializable id);

	T load(Serializable id);

	List<T> getAll();
	
	List<T> findAll(Criteria criteria);

	void update(T t);

	void bulkUpdate(List<T> ts);

	void delete(T t);

	void deleteById(Serializable id);

	void deleteAll();

	long count();

	boolean exists(Serializable id);

}
