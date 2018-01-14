package com.ta.toko.dao;

import java.util.Set;

import com.ta.toko.entity.Role;
import com.ta.toko.entity.User;

public interface UserDao extends BaseDao<User> {
	public User findByUserName(String username);

	public Set<Role> getUserRole(String username);
}
