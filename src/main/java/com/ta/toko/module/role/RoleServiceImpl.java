package com.ta.toko.module.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ta.toko.dao.RoleDao;
import com.ta.toko.entity.Role;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Role> getAll() {
		return roleDao.getAll();
	}

	@Override
	public Role findByName(String name) {
		// roleDao.g
		return roleDao.findByName(name);
	}

	@Override
	public void save(Role newRole) {
		roleDao.save(newRole);
	}

}
