package com.sdkj.util.context;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public class JsonToBean {
	public static Object toBean(HttpServletRequest request,Class beanClass){
		Map parameterMap = request.getParameterMap();
		JSONObject json = new JSONObject();
		  Iterator paIter = parameterMap.keySet().iterator();
		  while (paIter.hasNext()) {
		   String key = paIter.next().toString();
		   String[] values = (String[])parameterMap.get(key);
		   json.put(key, values[0]);
		  }
		  return JSONObject.toBean(json,beanClass);
	}
}
