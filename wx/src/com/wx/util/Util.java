package com.wx.util;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.swing.text.NumberFormatter;

import org.apache.commons.collections.iterators.ArrayListIterator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;


public class Util {
	protected final static Log logger = LogFactory.getLog("Util");
	static final int BUFFER = 8192;

	private static final String type_sss = "yyyy-MM-dd HH:mm:ss.SSS";
	private static final String type_ss = "yyyy-MM-dd HH:mm:ss";
	private static final String type_mm = "yyyy-MM-dd HH:mm";
	private static final String type_hh = "yyyy-MM-dd HH";
	private static final String type_dd = "yyyy-MM-dd";
	private static final String type_MM = "yyyy-MM";
	private static final String type_yy = "yyyy";

	public static String ObjectToString(Object object) {
		if (object == null)
			return "";
		else
			return object.toString();
	}

	public static int ObjectToInt(Object object) {
		if (object == null)
			return 0;
		else if (object instanceof String)
			return Integer.parseInt(object.toString());
		else
			return ((Number) object).intValue();
	}

	public static double ObjectToDouble(Object object) {
		if (object == null)
			return 0;
		else if (object instanceof String)
			return Double.parseDouble(object.toString());
		else
			return ((Number) object).doubleValue();

	}

	public static Boolean ObjectToBoolean(Object object) {
		if (object == null)
			return false;
		else {
			return new Boolean(object.toString().equals("1"));
		}
	}

	public static String ObjectToDateString(Object object) {
		DateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (object == null)
			return "";
		else
			try {
				return ft.format(object);
			} catch (Exception e) {
				logger.info("Util类中ObjectToDate()函数处理转换过程中发生错误:");
				logger.info("object:\n" + object);
				logger.info(e);
			}
		return null;
	}

	public static Date ObjectToDate(Object object) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (object == null)
			return new Date();
		else
			try {
				return formatter.parse(object.toString());
			} catch (ParseException e) {
				logger.info("Util类中ObjectToDate()函数处理转换过程中发生错误:");
				logger.info("object:\n" + object);
				logger.info(e);
			}
		return null;
	}

	public static String String2String(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if (date == null)
			return formatter.format(System.currentTimeMillis());
		else
			return date;
	}

	// 文件压缩方法
	public static void zipDIR(String sourceDIR, String targetZipFile) {

		try {
			FileOutputStream target = new FileOutputStream(targetZipFile);

			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
					target));

			int BUFFER_SIZE = 1024;

			byte buff[] = new byte[BUFFER_SIZE];

			File dir = new File(sourceDIR);
			compressFile(dir, out);

			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** 压缩一个文件 */
	private static void compressFile(File file, ZipOutputStream out) {
		if (!file.exists()) {
			return;
		}
		try {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(file));
			ZipEntry entry = new ZipEntry(file.getName());
			out.putNextEntry(entry);
			int count;
			byte data[] = new byte[BUFFER];
			while ((count = bis.read(data, 0, BUFFER)) != -1) {
				out.write(data, 0, count);
			}
			bis.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取系统时间
	 * 
	 * @return
	 */
	public static String getSystemDate() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long date = System.currentTimeMillis();
		return sf.format(date);
	}
	/**
	 * 获取系统时间，精确到天
	 * @return
	 */
	public static String getSystemDateDay() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		long date = System.currentTimeMillis();
		return sf.format(date);
	}
	
	public static double getFormatDouble(double d) {
		DecimalFormat dcmFmt = new DecimalFormat("0.0");
		return Double.valueOf(dcmFmt.format(d));
	}

	/**
	 * 获取指定范围随机数
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static double getRandomNo(double x, double y) {
		double m = (double) ((Math.random() * (y - x) + x));
		// m = Util.getFormatDouble(m);
		return m;
	}

	public static String nullToString(String str) {
		if (null == str) {
			str = "";
		}
		return str;
	}

	/***
	 * 时间运算类，算对小时的加减法
	 * 
	 * @param 传过来的时间
	 * @param 需要加的分钟数
	 * @return
	 * @throws ParseException
	 */

	public static String getTimeOperation(String time, int value)
			throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = formatter.parse(time);
		long Time = (date1.getTime() / 1000) + 60 * value;
		date1.setTime(Time * 1000);
		return formatter.format(date1);
	}

	/**
	 * 将日期转化为毫秒
	 * 
	 * @param date
	 *            传入的日期，格式为yyyy-MM-dd HH:mm:ss
	 * @throws ParseException
	 */
	public static long getCurrentDateSS(String date) throws ParseException {

		String type = getDateType(date);

		SimpleDateFormat sdf = new SimpleDateFormat(type);

		long millionSeconds = sdf.parse(date).getTime();// 毫秒

		return millionSeconds;
	}

	/**
	 * 将日期转化为毫秒
	 * 
	 * @param date
	 *            传入的日期，格式为yyyy-MM-dd HH:mm:ss.SSS
	 * @throws ParseException
	 */
	public static long getCurrentDateMS(String date) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		long millionSeconds = sdf.parse(date).getTime();// 毫秒

		return millionSeconds;
	}

	/**
	 * 系统时间，精确到毫秒
	 */
	public static String getSysDateMS() {

		long ct = System.currentTimeMillis();

		//////System.out.println(ct);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		String date = sdf.format(ct);

		//////System.out.println(date);

		return date;
	}

	/**
	 * 系统时间，精确到秒
	 */
	public static String getSysDateSS() {

		long ct = System.currentTimeMillis();

		//////System.out.println(ct);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String date = sdf.format(ct);

		//////System.out.println(date);

		return date;
	}

	/**
	 * 传入毫秒数返回格式时间
	 * 
	 * @param ct
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getSSDateByCurrentTime(long ct) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return sdf.format(ct);

	}

	/**
	 * 传入毫秒数返回格式时间
	 * 
	 * @param ct
	 * @return yyyy-MM-dd HH:mm:ss.SSS
	 */
	public static String getMSDateByCurrentTime(long ct) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		return sdf.format(ct);

	}

	/**
	 * 根据所给的毫秒数返回时间格式内容
	 * 
	 * @param Msecond
	 *            毫秒数
	 */
	public static String getTimeMemo(int mlsecond) {
		// 时间内容
		StringBuffer timeMemo = new StringBuffer();
		// 大于3600000为小时，大于60000为分钟，大于1000为秒
		if (mlsecond >= 3600000) {
			timeMemo.append((mlsecond / 3600000) + "小时");
			// 剩余分钟的毫秒数
			int msecond = mlsecond % 3600000;
			if (msecond / 60000 != 0)
				timeMemo.append((msecond / 60000) + "分钟");
			// 剩余秒的毫秒数
			int second = msecond % 60000;
			if (second / 1000 != 0)
				timeMemo.append((second / 1000) + "秒");
			// 剩余毫秒数
			mlsecond = second % 1000;
			if (mlsecond != 0)
				timeMemo.append(mlsecond + "毫秒");
		} else if (mlsecond >= 60000) {
			timeMemo.append((mlsecond / 60000) + "分钟");
			// 剩余秒的毫秒数
			int second = mlsecond % 60000;
			if (second / 1000 != 0)
				timeMemo.append((second / 1000) + "秒");
			// 剩余毫秒数
			mlsecond = second % 1000;
			if (mlsecond != 0)
				timeMemo.append(mlsecond + "毫秒");
		} else if (mlsecond >= 1000) {
			timeMemo.append((mlsecond / 1000) + "秒");
			// 剩余毫秒数
			mlsecond = mlsecond % 1000;
			if (mlsecond != 0)
				timeMemo.append(mlsecond + "毫秒");
		} else {
			timeMemo.append(mlsecond + "毫秒");
		}
		return timeMemo.toString();
	}

	/**
	 * 根据小数点保留位数值返回截取小数点的数据
	 */
	public static String getDataByPointNum(double data, Integer point_num) {
		if (point_num == null)
			return data + "";
		// 首先声明整数位字符串
		StringBuffer point_str = new StringBuffer("####0");
		// 循环保留位数
		for (int i = 0; i < point_num; i++) {
			if (i == 0)
				point_str.append(".");
			point_str.append("#");
		}
		// 声明截取对象
		DecimalFormat Doubleformatter = new DecimalFormat(point_str.toString());
		return Doubleformatter.format(data);
	}

	public static String getDateType(String date) {
		String type = "";
		if (date.length() <= 4)
			type = type_yy; // 年
		else if (date.length() <= 7)
			type = type_MM; // 年-月
		else if (date.length() <= 10)
			type = type_dd; // 年-月-日
		else if (date.length() <= 13)
			type = type_hh; // 年-月-日 时
		else if (date.length() <= 16)
			type = type_mm; // 年-月-日 时:分
		else if (date.length() <= 19)
			type = type_ss; // 年-月-日 时:分:秒
		else
			type = type_sss; // 年-月-日 时:分:秒.毫秒
		return type;
	}

	
    /**
     * list 转为数组
     * @param list
     * @return
     */
    public static String[] list2Array(List list){
    	
    	 String[] strs1 =  (String[]) list.toArray(new String[list.size()]);
    	 
    	 return strs1;
    }
    
    /**
     * 数组转化为list
     * @param 数组
     * @return
     */
    public static List array2List(String[] strs){
    	
    	List myList = new ArrayList();
  
    	String[] temp = strs;
    	 
    	Collections.addAll(myList, temp);
    	
    	return myList;


    }
    
    
    /**
     *  json 中 ""  替换为" "
     */
    public static String jonsmstr(String strs){
    	
    	
    	if(strs != null && strs.length() > 0){
    		
    		strs = strs.replaceAll("\"\"", "\" \"");
    		
    	}
    	return strs;
    	
    	
    }
    


	public static String prjPropertyNumToString(String prjPropertyNum) {
		String str="";
		if(prjPropertyNum.equals("001"))
			str="新建";
		else if(prjPropertyNum.equals("002"))
			str="改建";	
		else if(prjPropertyNum.equals("003"))
			str="扩建";	
		else if(prjPropertyNum.equals("004"))
			str="恢复";	
		else if(prjPropertyNum.equals("005"))
			str="迁建";	
		else if(prjPropertyNum.equals("006"))
			str="拆除";	
		else if(prjPropertyNum.equals("099"))
			str="其他";
				
		return str;
	}
	public static String prjFunctionNumToString(String prjFunctionNum) {
		String str="";
		if(prjFunctionNum.equals("100"))
			str="居住建筑";
		else if(prjFunctionNum.equals("200"))
			str="居住建筑配套工程";
		else if(prjFunctionNum.equals("300"))
			str="公共建筑";
		else if(prjFunctionNum.equals("301"))
			str="办公建筑";
		else if(prjFunctionNum.equals("302"))
			str="商业建筑";
		else if(prjFunctionNum.equals("303"))
			str="旅游建筑";
		else if(prjFunctionNum.equals("304"))
			str="科教文卫建筑";
		else if(prjFunctionNum.equals("305"))
			str="交通运输类";
		else if(prjFunctionNum.equals("306"))
			str="通信建筑";
		else if(prjFunctionNum.equals("307"))
			str="公共建筑配套工程";
		else if(prjFunctionNum.equals("400"))
			str="商住楼";
		else if(prjFunctionNum.equals("500"))
			str="农业建筑";
		else if(prjFunctionNum.equals("600"))
			str="农业建筑配套工程";
		else if(prjFunctionNum.equals("700"))
			str="工业建筑";
		else if(prjFunctionNum.equals("800"))
			str="工业建筑配套工程";
		else if(prjFunctionNum.equals("999"))
			str="其他";
		else if(prjFunctionNum.equals("010"))
			str="给水";
		else if(prjFunctionNum.equals("011"))
			str="排水";
		else if(prjFunctionNum.equals("012"))
			str="燃气";
		else if(prjFunctionNum.equals("013"))
			str="热力";
		else if(prjFunctionNum.equals("014"))
			str="道路";
		else if(prjFunctionNum.equals("015"))
			str="桥隧";
		else if(prjFunctionNum.equals("016"))
			str="风景园林";
		else if(prjFunctionNum.equals("017"))
			str="环境园林";
		else if(prjFunctionNum.equals("018"))
			str="公共交通";
		else if(prjFunctionNum.equals("099"))
			str="其他";
				
		return str;
	}
	public static String prjTypeNumToString(String prjTypeNum) {
		String str="";
		if(prjTypeNum.equals("01"))
			str="房屋建筑";
			else if(prjTypeNum.equals("02"))
				str="市政工程";
			else if(prjTypeNum.equals("99"))
				str="其他";
		return str;
	}
	public static String prjApprovalLevelNumToString(String prjApprovalLevelNum) {
		String str="";
		if(prjApprovalLevelNum.equals("001"))
			str="部级";
			else if(prjApprovalLevelNum.equals("002"))
				str="省级";
			else if(prjApprovalLevelNum.equals("003"))
				str="地市级";
			else if(prjApprovalLevelNum.equals("004"))
				str="区县级";	
		return str;
	}
	
	public static String nullToInt(int i) {
		String str=String.valueOf(i);
		if (null == str) {
			str = "";
		}
		return str;
	}
    
    
    public static void main(String[] args) {
    	Map map = new HashMap();
    	map.put("flag", 0);
//    	SimpleUser s = new SimpleUser();
//    	s.setUserId("42");
    	Map s = new HashMap();
    	s.put("userId", "44");
    	s.put("messageFlag", "27");
    	map.put("obj", s);
    	

	}

}
