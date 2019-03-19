package com.quadcore.lively.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {

	
	public static Date stringToDate(String df) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d1 = null;
		Date d2 = null;
		
		try {
			d1 = sdf.parse(df);
			d2 = new Date(d1.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return d2;
	}
	
	public static java.util.Date longToDate(long ts) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = new java.util.Date(ts);
		java.util.Date result = null;
		try {
			result = sdf.parse(date.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
