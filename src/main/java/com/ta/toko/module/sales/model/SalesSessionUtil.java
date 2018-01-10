package com.ta.toko.module.sales.model;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SalesSessionUtil {
	private static Logger logger = LoggerFactory.getLogger(SalesSessionUtil.class);

	public static SalesInformation getSalesInSession(HttpSession session) {
		logger.debug("get sales in session");
		SalesInformation sales = (SalesInformation) session.getAttribute(SalesConstant.SESSION_NAME);

		if (sales == null) {
			logger.info("new sales created");
			sales = new SalesInformation();

			session.setAttribute(SalesConstant.SESSION_NAME, sales);
			// SalesInformation s = (SalesInformation)
			// session.getAttribute(SalesConstant.SESSION_NAME);

		}

		return sales;
	}

	public static void putSalesInSession(HttpSession session, SalesInformation sales) {
		logger.debug("Put sales in session");
		session.setAttribute(SalesConstant.SESSION_NAME, sales);
	}
}
