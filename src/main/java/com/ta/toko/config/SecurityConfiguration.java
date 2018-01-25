package com.ta.toko.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.ta.toko.module.login.LoginServiceImpl;
import com.ta.toko.util.CustomPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoginServiceImpl loginServiceImpl;

	@Autowired
	private PersistentTokenRepository persistentTokenRepository;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginServiceImpl);
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(loginServiceImpl);
		authenticationProvider.setPasswordEncoder(passwordEncoder());

		return authenticationProvider;
	}

	@Bean
	public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
		PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
				"remember-me", loginServiceImpl, persistentTokenRepository);
		return tokenBasedservice;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
		// encoder.s
		return new CustomPasswordEncoder();
		// return new StandardPasswordEncoder();
	}

	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
		// http.authorizeRequests().anyRequest().permitAll();
		http.authorizeRequests().antMatchers("/login", "/resources/**").permitAll();
		http.authorizeRequests().antMatchers("/**", "/supplier", "/product", "/sales", "/purchase", "/profile")
				.access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().and().formLogin().loginProcessingUrl("/j_spring_security_check").loginPage("/login")
				.failureUrl("/login?error").defaultSuccessUrl("/").usernameParameter("username")
				.passwordParameter("password").and().rememberMe().rememberMeParameter("remember-me")
				.tokenRepository(persistentTokenRepository).tokenValiditySeconds(86400).and().logout()
				.logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login").and().exceptionHandling()
				.accessDeniedPage("/accessdenied").and().csrf();
		// .and().rememberMe()
		// .tokenRepository(tokenRepository)
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
}
