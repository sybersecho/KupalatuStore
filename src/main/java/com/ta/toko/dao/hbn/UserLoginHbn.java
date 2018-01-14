package com.ta.toko.dao.hbn;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;

import com.ta.toko.entity.UserLogin;

@Repository
public class UserLoginHbn extends AbstractDaoHbn<UserLogin> implements PersistentTokenRepository {

	private static Logger logger = LoggerFactory.getLogger(UserLoginHbn.class);

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		logger.info("Creating token for user:  " + token.getUsername());
		UserLogin userLogin = new UserLogin();
		userLogin.setLastUsed(token.getDate());
		userLogin.setToken(token.getTokenValue());
		userLogin.setUsername(token.getUsername());
		userLogin.setSeries(token.getSeries());
		save(userLogin);
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		logger.info("Fetch Token if any for seriesId : {}", seriesId);
		try {
			Criteria crit = getSession().createCriteria(UserLogin.class);
			crit.add(Restrictions.eq("series", seriesId));
			UserLogin userLogin = (UserLogin) crit.uniqueResult();

			return new PersistentRememberMeToken(userLogin.getUsername(), userLogin.getSeries(), userLogin.getToken(),
					userLogin.getLastUsed());
		} catch (Exception e) {
			logger.info("Token not found...");
			return null;
		}
	}

	@Override
	public void removeUserTokens(String username) {
		logger.info("Removing Token if any for user : {}", username);
		Criteria crit = getSession().createCriteria(UserLogin.class);
		crit.add(Restrictions.eq("username", username));
		UserLogin userLogin = (UserLogin) crit.uniqueResult();
		if (userLogin != null) {
			logger.info("rememberMe was selected");
			delete(userLogin);
		}

	}

	@Override
	public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
		logger.info("Updating Token for seriesId : {}", seriesId);
		UserLogin userLogin = getByKey(seriesId);
		userLogin.setToken(tokenValue);
		userLogin.setLastUsed(lastUsed);
		update(userLogin);

	}

	private UserLogin getByKey(String seriesId) {
		Criteria crit = getSession().createCriteria(UserLogin.class);
		crit.add(Restrictions.eq("series", seriesId));
		return (UserLogin) crit.uniqueResult();

	}

}
