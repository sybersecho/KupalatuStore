package com.ta.toko.dao.hbn;

import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ta.toko.dao.UserDao;
import com.ta.toko.entity.Role;
import com.ta.toko.entity.User;

@Repository
public class UserDaoHbn extends AbstractDaoHbn<User> implements UserDao {

	@Override
	public User findByUserName(String username) {
		Criteria criteria = getSession().createCriteria(User.class);
		if (username != null) {
			criteria.add(Restrictions.eq("username", username));
		}
		return (User) criteria.uniqueResult();
	}

	@Override
	public Set<Role> getUserRole(String username) {
		User user = findByUserName(username);
		return user.getUserRoles();
	}

}
