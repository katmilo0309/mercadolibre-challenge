package com.mercadolibre.challenge.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {

	private static Logger LOG = LoggerFactory.getLogger(DateUtils.class);

	private static String PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	public static Date getDateFromString(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat(PATTERN, Locale.ENGLISH);
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	public static String getStringFromDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(PATTERN, Locale.ENGLISH);
		return formatter.format(date);
	}
}
