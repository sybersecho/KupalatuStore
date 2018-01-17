package com.ta.toko.module.sales;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ta.toko.module.sales.model.SalesConstant;
import com.ta.toko.module.sales.model.SalesInformation;

public class SalesSessionUtil {
	private static Logger logger = LoggerFactory.getLogger(SalesSessionUtil.class);

	public static SalesInformation getSalesInSession(HttpSession session) {
		logger.info("get sales in session");
		SalesInformation sales = (SalesInformation) session.getAttribute(SalesConstant.SESSION_NAME);
		if (sales == null) {
			logger.info("create new Sales");
			sales = new SalesInformation();
		}else {
			logger.info("sales not null");
		}

		return sales;
	}

	public static void putSalesInSession(HttpSession session, SalesInformation sales) {
		logger.info("put sales in session");
		session.setAttribute(SalesConstant.SESSION_NAME, sales);
	}

	public static void removeSalesInSession(HttpSession session) {
		logger.info("remove sales in session");
		session.removeAttribute(SalesConstant.SESSION_NAME);
	}
}
