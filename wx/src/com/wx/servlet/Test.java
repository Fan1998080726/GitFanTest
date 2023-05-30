package com.wx.servlet;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Test {
	static long count=0;
	public static void main(String[] args) {
		  Map<String, String> map = new HashMap<String, String>();  
	
	        map.put("prjId","111"); 
	        map.put("openid", "2222999999999999999999999999999999999999999999999999");
 
//	  	  for (Object string : map) {
//				
//			}
//	        String str = Size(map.);
	      String strq =  map.toString();
//	       	System.out.println("str="+str);
	}
//		 Calendar calendar = Calendar.getInstance();
//	        Date firstTime = calendar.getTime();
//	        // 间隔：2分钟
//	        long period = 1000 * 60 * 1;
//	 
//	        Timer timer = new Timer();
//
//	        timer.schedule(new TimerTask() {
//	            @Override
//	            public void run() {
//	                // 执行你的方法
//	    	   	        System.out.println("Count="+count);
//	            }
//	        }, firstTime, period);
		
		
 	    
	
	
	
	public static String Size(long size){
		   if (size < 1024) {  
	 	          return String.valueOf(size) + "B";  
	 	      } else {  
	 	          size = size / 1024;  
	 	      }  
	 	      //如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
	 	      if (size < 1024) {  
	 	          return String.valueOf(size) + "KB";  
	 	      } else {  
	 	          size = size / 1024;  
	 	      }  
	 	      if (size < 1024) {  
	 	          //因为如果以MB为单位的话，要保留最后1位小数，  
	 	          //因此，把此数乘以100之后再取余  
	 	          size = size * 100;  
	 	          return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "MB";  
	 	      } else {  
	 	          //否则如果要以GB为单位的，先除于1024再作同样的处理  
	 	          size = size * 100 / 1024;  
	 	          return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "GB";  
	 	      }  
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
