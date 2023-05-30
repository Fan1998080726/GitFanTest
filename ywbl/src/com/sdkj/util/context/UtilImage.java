package com.sdkj.util.context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class UtilImage {
	
	
	
	public static String getImageStr(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		//imgFile = "E:\\wychPIC\\2013-05-27\\invpic\\0000000011.jpg";
		// String imgFile = "d:\\111.jpg";// 待处理的图片
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
		    in = new FileInputStream(imgFile);
		    data = new byte[in.available()];
		    in.read(data);
		    in.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data).trim();// 返回Base64编码过的字节数组字符串
	    }
    
    /**
     * 将字符串转为图片
     * 
     * @param imgStr  图片字符串
     * @param imgFile 路径+照片.jpg 
     * @param opertype 文件夹.jpg 
     */
    public static boolean generateImage(String imgStr, String imgFile,String path)
    		throws Exception {// 对字节数组字符串进行Base64解码并生成图片
    	if (imgStr == null) // 图像数据为空
    		return false;
       	if (imgStr.length() == 0) // 图像数据为空
    		return false;
       	
    	BASE64Decoder decoder = new BASE64Decoder();
    	try {
    		// Base64解码
    		byte[] b = decoder.decodeBuffer(imgStr);
    		for (int i = 0; i < b.length; ++i) {
    			if (b[i] < 0) {// 调整异常数据
    				b[i] += 256;
    			}
    		}
    		
    		// 生成jpeg图片
    		String imgFilePath = imgFile;// 新生成的图片
    		
   		 	File file = new File(path+"/");
   		 	
    		if (!file.exists() &&  !file.isDirectory()) 
    		{   
    			file.mkdirs();  
    		}
    		
    		OutputStream out = new FileOutputStream(imgFilePath);
    		
    		out.write(b);
    		out.flush();
    		out.close();
    		return true;
    		
    	} catch (Exception e) {
    		throw e;
    	}
    }
   
    public static String jsonStr(Object str){
    	
		JSONObject jsonObj = JSONObject.fromObject(str);
		  
		 return jsonObj.toString();
    	
    }

    public static String jsonListStr(List list){
    	
    	JSONArray jsonObj = JSONArray.fromObject(list);
		  
		 return jsonObj.toString();
    	
    }
    
   public static void main(String[] args) throws Exception {
	   
//	  String aa = getImageStr("D:\\anjianfile\\南溪大桥(定期检测)\\1\\加劲梁1\\加劲梁检测\\构件变形照片 - 副本.jpg");
//	  
//	    //////System.out.println(aa);
//	  
//	   //generateImage(aa, "E:\\wychPIC\\2013-05-27\\invpic\\00088888.jpg","invpic");
//	   
//	 generateImage(aa, "D:\\androidPIC\\2013-12-03\\1_20131203143006.jpg" );
	   
//	   String eventflag = "1";
//	   
//	  int a =  "null".equals(String.valueOf(eventflag)) || "".equals(String.valueOf(eventflag)) ? -1 : Integer.parseInt(eventflag);
//
//	   //////System.out.println(a);
	   
	   aa("");
	   
	
    }
   
			public static void copyFile(List list) throws Exception {
		
				list.add(1);
				
		       }
			
			public static void aa(String a ) throws Exception{
				List list = new ArrayList();
				list.add("2");
				copyFile(list);
				//////System.out.println(list);
			}
}
