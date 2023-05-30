package com.sdkj.util.context;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class JSONListUtil {

	protected final static Log logger = LogFactory.getLog("JSONListUtil");
	
	public static void getJSONListUtil(List<?> list) throws IOException{
		//将list转化成JSON对象  
        JSONArray jsonArray = JSONArray.fromObject(list);  
        HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);  
        response.setCharacterEncoding("UTF-8");   
        response.getWriter().print(jsonArray); 
	}
}
