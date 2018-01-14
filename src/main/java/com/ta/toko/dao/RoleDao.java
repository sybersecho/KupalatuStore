package com.ta.toko.dao;

import com.ta.toko.entity.Role;

public interface RoleDao extends BaseDao<Role> {
	public Role findByName(String name);
}
