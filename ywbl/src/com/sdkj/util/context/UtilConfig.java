package com.sdkj.util.context;


import java.util.ArrayList;
import java.util.List;

import util.ConfigFile;

public class UtilConfig {
	
	private final static String CONFIG = "IP-config.properties";
	
	private static ConfigFile getUtilConfig(){
		
		ConfigFile configfile = ConfigFile.getInstance(CONFIG);
		
		return configfile;
		
	}
	
	/**
	 * 直接获取某一属性文件中特定属性值
	 * @param value   获取属性的name
	 * @param configFileUrl    属性properties名称   sms-config.properties
	 * @return
	 */
	public static String getValue(String value , String configFileUrl){
		
		ConfigFile tempconfigfile = ConfigFile.getInstance(configFileUrl);
		
		String rvalue = String.valueOf(getUtilConfig().getkeyvalue(value));
		
		return rvalue ;
	}
	
	
	/**
	 * 获取属性文件中值
	 * @param value
	 * @return
	 */
	public static String getValue(String value){
		
		String rvalue = String.valueOf(getUtilConfig().getkeyvalue(value));
		
		return rvalue ;
	}
	
	
	
}
