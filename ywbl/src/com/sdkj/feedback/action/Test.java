package com.sdkj.feedback.action;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;

import com.sdkj.util.context.CommonFunc;

public class Test {
	public static void main(String[] args) throws ParseException { 
		
		String daysAfter = CommonFunc.getDaysAfter(CommonFunc.CurrentDateJianHao(),15);
		System.out.println("DAyy=="+daysAfter);
		
		
		
		
	}
}
