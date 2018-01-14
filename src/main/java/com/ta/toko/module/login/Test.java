package com.ta.toko.module.login;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
	public static void main(String[] args) {
		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		String pass = enc.encode("admin");
		System.out.println(pass);
	}
}
