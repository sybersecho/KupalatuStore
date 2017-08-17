package com.ta.toko.util;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

public class SessionUtil {
	private static Logger logger = LoggerFactory.getLogger(SessionUtil.class);

	@SuppressWarnings("rawtypes")
	public static void print(Model model, HttpServletRequest request, HttpSession session) {
		if (model != null) {
			logger.debug("---- Model data ----");
			Map modelMap = model.asMap();
			for (Object modelKey : modelMap.keySet()) {
				Object modelValue = modelMap.get(modelKey);
				logger.debug(modelKey + " -- " + modelValue);
			}
			logger.debug("---- End of Model data ----");
		}

		if (request != null) {
			logger.debug("=== Request data ===");
			Enumeration<String> reqEnum = request.getAttributeNames();
			while (reqEnum.hasMoreElements()) {
				String s = reqEnum.nextElement();
				logger.debug(s);
				logger.debug("==" + request.getAttribute(s));
			}
			logger.debug("=== End of Request data ===");
		}

		if (session != null) {
			logger.debug("*** Session data ***");
			Enumeration<String> e = session.getAttributeNames();
			while (e.hasMoreElements()) {
				String s = e.nextElement();
				logger.debug(s);
				logger.debug("**" + session.getAttribute(s));
			}
			logger.debug("*** End of Session data ***");
		}
	}
}
