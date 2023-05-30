package com.sdkj.util.context;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 20160101@author Administrator
 * 获取系统时间工具类
 */
public class SystemTimeUtil {

	protected final static Log logger = LogFactory.getLog("SystemTimeUtil");
	
	public static String getSYSTEMTime() throws IOException{
		   Date date = new Date();
		   SimpleDateFormat formater = new SimpleDateFormat();  
	       formater.applyPattern("yyyy-MM-dd HH:mm:ss");//系统时间格式
	       String  LogManOperationTime = formater.format(date);
	       return LogManOperationTime;
	}
}
