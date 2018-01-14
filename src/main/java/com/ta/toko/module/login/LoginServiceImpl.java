package com.ta.toko.module.login;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ta.toko.dao.UserDao;
import com.ta.toko.entity.Role;
import com.ta.toko.entity.User;

@Service
// @Transactional(readOnly = true)
public class LoginServiceImpl implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("load user by user name: " + username);
		User user = userDao.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("username was not found");
		}

		Set<Role> roles = user.getUserRoles();

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

		if (roles != null) {
			for (Role role : roles) {
				GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
				grantList.add(authority);
			}
		}

		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),
				user.getPassword(), true, true, true, true, getGrantedAuthorities(user));

		return userDetails;
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Role role : user.getUserRoles()) {
			logger.info("User role : {}", role.getName());
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		logger.info("authorities : {}", authorities);
		return authorities;
	}

}
