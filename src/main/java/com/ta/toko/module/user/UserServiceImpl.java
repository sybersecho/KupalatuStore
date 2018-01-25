package com.ta.toko.module.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ta.toko.dao.UserDao;
import com.ta.toko.entity.User;
import com.ta.toko.module.user.model.UserProfile;

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

	@Override
	public void updateProfile(UserProfile profile) {
		User user = userDao.findByUserName(profile.getUsername());
		if(user==null) {
			System.out.println("usernull");
		}else {
			System.out.println("user not null");
		}
		
//		if(profile==null) {
//			System.out.println("usernull");
//		}else {
//			System.out.println("user not null");
//		}
		user.setPassword(passwordEncoder.encode(profile.getPassword()));
		update(user);

	}

}
