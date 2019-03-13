package com.quadcore.lively.api.twitter.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UtilDateToSqlDate {

	public static Date change(java.util.Date date) {
		return new Date(date.getTime());
	}
	
	public static Date change(String dt) {
		Date d2 = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date d1 = sdf.parse(dt);
			d2 = new Date(d1.getTime()); //d1.getTime은 long 타입의 값이 되고 new Date로 sql date을 만듬
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d2;
		
	}
	
	
}
