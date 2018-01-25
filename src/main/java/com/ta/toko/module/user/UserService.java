package com.ta.toko.module.user;

import java.util.List;

import com.ta.toko.entity.User;
import com.ta.toko.module.user.model.UserProfile;

public interface UserService {
	public void save(User newUser);

	public User findById(Long id);

	public List<User> getAll();

	public void update(User updatedUser);

	public void delete(Long id);

	public void updateProfile(UserProfile profile);
}
