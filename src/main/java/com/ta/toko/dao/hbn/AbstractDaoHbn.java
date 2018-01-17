package com.ta.toko.dao.hbn;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import com.ta.toko.dao.BaseDao;

@Repository
@Transactional
public abstract class AbstractDaoHbn<T extends Serializable> implements BaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;
	private Class<T> domainClass;
	private int batchSize = 30;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	private Class<T> getDomainClass() {
		if (domainClass == null) {
			ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
			this.domainClass = (Class<T>) thisType.getActualTypeArguments()[0];
		}

		return domainClass;
	}

	private String getDomainClassName() {
		return getDomainClass().getName();
	}

	public void save(T t) {
		Method method = ReflectionUtils.findMethod(getDomainClass(), "setDateCreated", new Class[] { Date.class });
		if (method != null) {
			try {
				method.invoke(t, new Date());
			} catch (Exception e) {
			}
		}

		getSession().save(t);
	}

	@SuppressWarnings("unchecked")
	public T get(Serializable id) {
		return (T) getSession().get(getDomainClassName(), id);
	}

	@SuppressWarnings("unchecked")
	public T load(Serializable id) {
		return (T) getSession().load(getDomainClassName(), id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(Criteria criteria) {
		return criteria.list();
	}

	// @SuppressWarnings("unchecked")
	@Transactional
	public List<T> getAll() {
		return findAll(getSession().createCriteria(getDomainClass()));
	}

	public void update(T t) {
		getSession().update(t);
	}

	public void delete(T t) {
		getSession().delete(t);
	}

	public void deleteById(Serializable id) {
		delete(load(id));
	}

	public void deleteAll() {
		getSession().createQuery("delete " + getDomainClassName()).executeUpdate();
	}

	public long count() {
		return (Long) getSession().createQuery("select count(*) from " + getDomainClassName()).uniqueResult();
	}

	public boolean exists(Serializable id) {
		return (get(id) != null);
	}

	public void bulkUpdate(List<T> ts) {
		getSession().getTransaction().begin();
		int i = 0;
		for (T t : ts) {
			getSession().persist(t);
			if (i % batchSize == 0 && i > 0) {
				getSession().flush();
				getSession().clear();
			}
			i++;
		}
		getSession().getTransaction().commit();
	}
}
