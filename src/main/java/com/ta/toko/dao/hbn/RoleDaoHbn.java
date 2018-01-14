package com.ta.toko.dao.hbn;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ta.toko.dao.RoleDao;
import com.ta.toko.entity.Role;

@Repository
public class RoleDaoHbn extends AbstractDaoHbn<Role> implements RoleDao {

	@Override
	public Role findByName(String name) {
		Criteria criteria = getSession().createCriteria(Role.class);
		criteria.add(Restrictions.eq("name", name));
		return (Role) criteria.uniqueResult();
	}

}
