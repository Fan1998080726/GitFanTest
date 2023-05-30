package com.wx.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.DocumentException;
import com.thoughtworks.xstream.XStream;
import com.wx.servlet.TokenThread;
import com.wx.vo.TextMessage;

/*
 * xml处理工具类
 */
public class XmlUtil {
	/*
	 * xml转map
	 */
	 private static Logger log = LoggerFactory.getLogger(XmlUtil.class);
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException{
		HashMap<String, String> map = new HashMap<String,String>();
		SAXReader reader = new SAXReader();
 
		InputStream ins = request.getInputStream();
		Document doc;
		try {
			doc = reader.read(ins);
		
 
		Element root = doc.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> list = (List<Element>)root.elements();
 
		for(Element e:list){
			map.put(e.getName(), e.getText());
		}
		ins.close();
		} catch (org.dom4j.DocumentException e1) {
			// TODO Auto-generated catch block
			  log.info(CommonFunc.CurrentTime()+" XmlUtil class org.dom4j.DocumentException:", e1.getMessage());
              
		//	e1.printStackTrace();
		}
		return map;
	}
	/*
	 * 文本消息对象转xml
	 */
	public static String textMsgToxml(TextMessage textMessage){
		System.out.println("into textMsgToxml     文本消息对象转xml");
		
		XStream xstream = new XStream();
		try{
			xstream.alias("xml", textMessage.getClass());
		}catch(Exception ex){ex.printStackTrace();}
		return xstream.toXML(textMessage);
	}
}