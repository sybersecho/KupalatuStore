package com.ta.toko.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {
	
	private static Logger logger = LoggerFactory.getLogger(CustomPasswordEncoder.class);
	

	@Override
	public String encode(CharSequence rawPassword) {

		String hashed = BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(12));
		logger.info(rawPassword.toString() + " : " + hashed);
		return hashed;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		logger.info("is match?" + rawPassword.toString() + " : " + encodedPassword);
		return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
	}

}
