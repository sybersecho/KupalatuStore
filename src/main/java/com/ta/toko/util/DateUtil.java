package com.ta.toko.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static final String SIMPLE_FORMAT = "dd/mm/yyyy";
	private static DateFormat dateFormat;

	public static Date getCurrentDate() {
		Date now = null;
		try {
			now = dateFormat.parse(getStrCurrentDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return now;
	}

	public static String getStrCurrentDate() {
		dateFormat = new SimpleDateFormat(SIMPLE_FORMAT);
		return dateFormat.format(new Date());
	}
}
