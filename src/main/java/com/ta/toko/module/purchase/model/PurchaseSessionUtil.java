package com.ta.toko.module.purchase.model;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PurchaseSessionUtil {

	private static Logger logger = LoggerFactory.getLogger(PurchaseSessionUtil.class);

	public static PurchaseInfo getPurchaseInSession(HttpSession session) {
		logger.debug("get purchase in session");
		PurchaseInfo purchase = (PurchaseInfo) session.getAttribute(PurchaseConstant.SESSION_NAME);

		if (purchase == null) {
			logger.info("new Purchase created");
			purchase = new PurchaseInfo();

			session.setAttribute(PurchaseConstant.SESSION_NAME, purchase);
			PurchaseInfo p = (PurchaseInfo) session.getAttribute(PurchaseConstant.SESSION_NAME);
			if (p.equals(purchase)) {
				logger.info("equal");
			} else {
				logger.info("not equal");
			}
		}

		return purchase;
	}

	public static void putPurchaseInfoInSession(HttpSession session, PurchaseInfo purchaseInfo) {
		logger.debug("Put purchase in session");
		session.setAttribute(PurchaseConstant.SESSION_NAME, purchaseInfo);
	}
}
