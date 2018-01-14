package com.ta.toko.module.role;

import java.util.List;

import com.ta.toko.entity.Role;

public interface RoleService {

	public List<Role> getAll();

	public Role findByName(String name);

	public void save(Role newRole);
}
