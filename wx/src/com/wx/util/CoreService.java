package com.wx.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
//import net.sf.json.JSONObject;
import javax.net.ssl.HttpsURLConnection;

import com.alibaba.fastjson.JSONObject;
import com.wx.servlet.TokenThread;

public class CoreService {      
	public static String GETOPENID = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";  
	/*通过code获取用户openid*/
	public static JSONObject getOpenid(String code) throws IOException{	
//		System.out.println("into public static JSONObject getOpenid(String code) throws IOException{");
//		System.out.println("getOpenid===code==="+code);
//		System.out.println("TokenTest.jgcAppID==="+TokenTest.jgcAppID);
//		System.out.println("TokenTest.jgcAppsecret==="+TokenTest.jgcAppsecret);
		JSONObject jsonObject = null;	
		String path = GETOPENID.replace("APPID", TokenTest.jgcAppID).
				replace("SECRET", TokenTest.jgcAppsecret).replace("CODE", code);
//		System.out.println("path==="+path);
		StringBuffer buffer = new StringBuffer(); 
		URL url = new URL(path);               
		HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();   
		httpUrlConn.setRequestMethod("POST");       
		httpUrlConn.setDoOutput(true);         
		httpUrlConn.setDoInput(true);         
		httpUrlConn.setUseCaches(false);       
		// 将返回的输入流转换成字符串       
		InputStream inputStream = httpUrlConn.getInputStream();   
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);    
		String str = null;           
		while ((str = bufferedReader.readLine()) != null) 
		{  
			buffer.append(str);            
			}     
		bufferedReader.close();         
		inputStreamReader.close();                // 释放资源  
		inputStream.close();          
		inputStream = null;    
		httpUrlConn.disconnect();   
	
//		System.out.println(buffer.toString());
		
		String str2 = new String(buffer);
		jsonObject = JSONObject.parseObject(str2);	
//		System.out.println(jsonObject.toString());
//		System.out.println("logout public static JSONObject getOpenid(String code) throws IOException{");
		return jsonObject;	
		}
	
	
	
		public static JSONObject getUserGroup(String openId)throws IOException{	
		System.out.println("into public static JSONObject getOpenid(String code) throws IOException{");
//		System.out.println("getOpenid===code==="+code);
//		System.out.println("TokenTest.jgcAppID==="+TokenTest.jgcAppID);
//		System.out.println("TokenTest.jgcAppsecret==="+TokenTest.jgcAppsecret);
		JSONObject jsonObject = null;	
		String path = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=31_NmjxhctUXxtnF0Ad3-jbQE3LwKn7mEDCpzdxppKFzvOujOlH4esri53AII_iPnxjukwbJm4SY9RNpeypf4UfSftKPDCLoMYDT1kg9pGm1RNTcNN6ennTFqk6S-y_NglkDdxN9LbKt7TH0hw_HDAdAJAMIF"+"&openid="+openId;
//		System.out.println("path==="+path);
		StringBuffer buffer = new StringBuffer(); 
		URL url = new URL(path);               
		HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();   
		httpUrlConn.setRequestMethod("POST");       
		httpUrlConn.setDoOutput(true);         
		httpUrlConn.setDoInput(true);         
		httpUrlConn.setUseCaches(false);       
		// 将返回的输入流转换成字符串       
		InputStream inputStream = httpUrlConn.getInputStream();   
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);    
		String str = null;           
		while ((str = bufferedReader.readLine()) != null) 
		{  
			buffer.append(str);            
			}     
		bufferedReader.close();         
		inputStreamReader.close();                // 释放资源  
		inputStream.close();          
		inputStream = null;    
		httpUrlConn.disconnect();   
	
//		System.out.println(buffer.toString());
		
		String str2 = new String(buffer);
		jsonObject = JSONObject.parseObject(str2);	
		System.out.println(jsonObject.toString());
		System.out.println("logout public static JSONObject getOpenid(String code) throws IOException{");
		return jsonObject;	
		}
	}
