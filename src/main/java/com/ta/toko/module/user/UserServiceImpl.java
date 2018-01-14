package com.ta.toko.module.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ta.toko.dao.UserDao;
import com.ta.toko.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDao userDao;

	@Override
	public void save(User newUser) {
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		userDao.save(newUser);
	}

	@Override
	public User findById(Long id) {
		return userDao.get(id);
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public void update(User updatedUser) {
		userDao.update(updatedUser);

	}

	@Override
	public void delete(Long id) {
		userDao.delete(findById(id));
	}

}
