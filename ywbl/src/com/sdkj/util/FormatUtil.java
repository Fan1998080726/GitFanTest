package com.sdkj.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtil {
	private static SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private static SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String getDateFormat1(Date date) {
		String dateFormatStr = simpleDateFormat1.format(date);
		return dateFormatStr;
	}
	
	public static String getDateFormat2(Date date) {
		String dateFormatStr = simpleDateFormat2.format(date);
		return dateFormatStr;
	}
	
	public static Date strToDate1(String dateStr){
		Date date = null;
		try {
			date = simpleDateFormat3.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
