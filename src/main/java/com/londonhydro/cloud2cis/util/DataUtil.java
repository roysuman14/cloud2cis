package com.londonhydro.cloud2cis.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUtil {

	public static String bool01(boolean b) {
		return b ? "1" : "0";
	}

	public static String sanitizeStringRemoveWS(String s) {
		if (s == null || s.isEmpty()) {
			return "";
		}
		return s.replaceAll("\\s+", "");

	}

	public static boolean isEmpty(String s) {
		return (s == null || s.isEmpty());
	}

	public static String sanitizeString(String s) {
		if (s == null || s.isEmpty()) {
			return "";
		}
		return s;
	}

	public static Calendar dateToCalendar(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c;
	}
	
	public static Date yyyymmddToDate(String d) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return formatter.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
