package com.sdkj.common.action;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sdkj.util.PictureChangeSize;
import com.sdkj.util.context.CommonFunc;
import com.sdkj.util.context.Util;
@Results( 
        value={ 
        		@Result(name="uploadFile",type="dispatcher",location="/jsp/project/uploadFile.jsp"),
        		/*2017-11-17yby 新加 uploadFile1.jsp*/
        		@Result(name="uploadFile1",type="dispatcher",location="/jsp/project/uploadFile1.jsp"),
        		/*2018115 fcl */
        		@Result(name="uploadFile3",type="dispatcher",location="/jsp/project/uploadFile3.jsp"),
        		/*2017-11-17yby 新加 uploadFile2.jsp*/
        		@Result(name="uploadFile2",type="dispatcher",location="/jsp/project/uploadFile2.jsp"),
        		//杨傲鹏 监理合同
        		@Result(name="uploadFileJLHT",type="dispatcher",location="/jsp/project/uploadFileJLHT.jsp"),
        		//杨傲鹏 填报人员
        		@Result(name="uploadFileTBRY",type="dispatcher",location="/jsp/project/uploadFileTBRY.jsp"),
        		//资质添加
        		@Result(name="uploadFileZZ",type="dispatcher",location="/jsp/project/uploadFileZZ.jsp"),
        }   
)
@Action("filesUpload")
public class FilesUploadAction {

	/**
	 * 最多上传的5个文件
	 */
	public File myFile_1;
	public File myFile_2;
	public File myFile_3;
	public File myFile_4;
	public File myFile_5;
	/**
	 * 最多上传的5个文件的名称
	 */
	public String myFile_1FileName;
	public String myFile_2FileName;
	public String myFile_3FileName;
	public String myFile_4FileName;
	public String myFile_5FileName;
	
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();

	
	public File getMyFile_1() {
		return myFile_1;
	}
	public void setMyFile_1(File myFile_1) {
		this.myFile_1 = myFile_1;
	}
	public File getMyFile_2() {
		return myFile_2;
	}
	public void setMyFile_2(File myFile_2) {
		this.myFile_2 = myFile_2;
	}
	public File getMyFile_3() {
		return myFile_3;
	}
	public void setMyFile_3(File myFile_3) {
		this.myFile_3 = myFile_3;
	}
	public File getMyFile_4() {
		return myFile_4;
	}
	public void setMyFile_4(File myFile_4) {
		this.myFile_4 = myFile_4;
	}
	public File getMyFile_5() {
		return myFile_5;
	}
	public void setMyFile_5(File myFile_5) {
		this.myFile_5 = myFile_5;
	}
	public String getMyFile_1FileName() {
		return myFile_1FileName;
	}
	public void setMyFile_1FileName(String myFile_1FileName) {
		this.myFile_1FileName = myFile_1FileName;
	}
	public String getMyFile_2FileName() {
		return myFile_2FileName;
	}
	public void setMyFile_2FileName(String myFile_2FileName) {
		this.myFile_2FileName = myFile_2FileName;
	}
	public String getMyFile_3FileName() {
		return myFile_3FileName;
	}
	public void setMyFile_3FileName(String myFile_3FileName) {
		this.myFile_3FileName = myFile_3FileName;
	}
	public String getMyFile_4FileName() {
		return myFile_4FileName;
	}
	public void setMyFile_4FileName(String myFile_4FileName) {
		this.myFile_4FileName = myFile_4FileName;
	}
	public String getMyFile_5FileName() {
		return myFile_5FileName;
	}
	public void setMyFile_5FileName(String myFile_5FileName) {
		this.myFile_5FileName = myFile_5FileName;
	}
	/**
	 * 
	 * describe: 多文件上传
	 * @throws IOException
	 * 2014-4-17
	 * @author txb
	 */
	public void uploadFiles() throws IOException{
		String filePath = request.getParameter("filePath");
		filePath=Util.nullToString(filePath);
		Map map = null;
		try {
			map = fileUpload(filePath, 50, 10485760);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().print(json.toString());
		
	}
	public String uploadFile(){
		return "uploadFile";
	}
	/*2017-11-17yby 开始*/
	public String uploadFile1(){
	String flage;
	try {
		flage =request.getParameter("flage");
		request.setAttribute("flage", flage);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
		return "uploadFile1";
	}
	
	public String uploadFile3(){
		String flage;
		try {
			flage =request.getParameter("flage");
			request.setAttribute("flage", flage);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
			return "uploadFile3";
		}
	
	
	
	
	public String uploadFile2(){
		String flage;
		try {
			flage = request.getParameter("flage");
			request.setAttribute("flage", flage);
		} catch (Exception e) {
			e.printStackTrace();
		}
			return "uploadFile2";
		}
	
	
	// 资质文件
	public String uploadFileZZ(){
		String flage;
		try {
			flage = request.getParameter("flage");
			request.setAttribute("flage", flage);
		} catch (Exception e) {
			e.printStackTrace();
		}
			return "uploadFileZZ";
		}
	
	
	//杨傲鹏 监理合同
	public String uploadFileJLHT(){
		String flage;
		try {
			flage = request.getParameter("flage");
			request.setAttribute("flage", flage);
		} catch (Exception e) {
			e.printStackTrace();
		}
			return "uploadFileJLHT";
		}
	//杨傲鹏 监理合同
	
	
	
	//杨傲鹏 监理合同
	public String uploadFileTBRY(){
		String flage;
		try {
			flage = request.getParameter("flage");
			request.setAttribute("flage", flage);
		} catch (Exception e) {
			e.printStackTrace();
		}
			return "uploadFileTBRY";
		}
	//杨傲鹏 监理合同
	/*2017-11-17yby结束*/
	/**
	 * 
	 * describe:文件上传
	 * @return Map 
	 * @throws Exception
	 * 2013-12-9
	 * @author txb
	 * @param request 
	 * @param obj 保存每个文件时传的参数
	 */
	public Map fileUpload(String dirPath,int nameLength,int fileLength){
			Map map=new HashMap();
			String temp="0";
			map.put("flag", "0"); //0:表示上传失败 1:表示成功
			map.put("message", "没有上传的文件！");
			////System.out.println(CommonFunc.getNowTime()+"  myFile_1FileName==="+myFile_1FileName);
			//2017-11-17yby修改
			//myFile_1FileName = CommonFunc.getGUID()+myFile_1FileName.substring(myFile_1FileName.lastIndexOf("."),myFile_1FileName.length());
			
			
			if (myFile_1 != null) {
				//2017-11-17yby修改
				myFile_1FileName = CommonFunc.getGUID()+myFile_1FileName.substring(myFile_1FileName.lastIndexOf("."),myFile_1FileName.length());
				temp="1";
				if(myFile_1FileName.length()>nameLength){
					map.put("message", myFile_1FileName+"文件名称过长！");
					return map;
				}else if (myFile_1.length() > fileLength){
					map.put("message", myFile_1FileName+"文件过大，最大为10M！");
					return map;
				}
					
			}
			if (myFile_2 != null) {
				//2017-11-17yby修改
				myFile_2FileName = CommonFunc.getGUID()+myFile_2FileName.substring(myFile_2FileName.lastIndexOf("."),myFile_2FileName.length());
				temp="1";
				if(myFile_2FileName.length()>nameLength){
					map.put("message", myFile_2FileName+"文件名称过长！");
					return map;
				}else if (myFile_2.length() > fileLength){
					map.put("message", myFile_2FileName+"文件过大，最大为10M！");
					return map;
				}
				
			}
			if (myFile_3 != null) {
				//2017-11-17yby修改
				myFile_3FileName = CommonFunc.getGUID()+myFile_3FileName.substring(myFile_3FileName.lastIndexOf("."),myFile_3FileName.length());
				temp="1";
				if(myFile_3FileName.length()>nameLength){
					map.put("message", myFile_3FileName+"文件名称过长！");
					return map;
				}else if (myFile_3.length() > fileLength){
					map.put("message", myFile_3FileName+"文件过大，最大为10M！");
					return map;
				}
				
			}
			if (myFile_4 != null) {
				//2017-11-17yby修改
				myFile_4FileName = CommonFunc.getGUID()+myFile_4FileName.substring(myFile_4FileName.lastIndexOf("."),myFile_4FileName.length());
				temp="1";
				if(myFile_4FileName.length()>nameLength){
					map.put("message", myFile_4FileName+"文件名称过长！");
					return map;
				}else if (myFile_4.length() > fileLength){
					map.put("message", myFile_4FileName+"文件过大，最大为10M！");
					return map;
				}
				
			}
			if (myFile_5 != null) {
				//2017-11-17yby修改
				myFile_5FileName = CommonFunc.getGUID()+myFile_5FileName.substring(myFile_5FileName.lastIndexOf("."),myFile_5FileName.length());
				temp="1";
				if(myFile_5FileName.length()>nameLength){
					map.put("message", myFile_5FileName+"文件名称过长！");
					return map;
				}else if (myFile_5.length() > fileLength){
					map.put("message", myFile_5FileName+"文件过大，最大为10M！");
					return map;
				}
				
			}
				try {
					List list = new ArrayList();
					if(myFile_1 != null){
						copyFile(myFile_1, myFile_1FileName,dirPath,list);
					}
					if(myFile_2 != null){
						copyFile(myFile_2, myFile_2FileName,dirPath,list);
					}
					if(myFile_3 != null){
						copyFile(myFile_3, myFile_3FileName,dirPath,list);
					}
					if(myFile_4 != null){
						copyFile(myFile_4, myFile_4FileName,dirPath,list);
					}
					if(myFile_5 != null){
						copyFile(myFile_5, myFile_5FileName,dirPath,list);
					}
					map.put("flag", "1"); 
					map.put("files", list);
					
				} catch (Exception e) {
					map.put("flag", "0");
					map.put("message", "上传文件出错！");
					e.printStackTrace();
				}
			
			return map;
	}
	/**
	 * 
	 * describe:文件保存
	 * @param myFile 文件
	 * @param myFileFileName 文件名称
	 * @param realpath 文件路径
	 * @param id 文件存储外键
	 * @author txb
	 * 2013-03-10 修改 txb 添加参数obj
	 * @throws Exception 
	 */
	public void copyFile(File myFile, String myFileFileName,String dirPath,List list) throws Exception {
			String name = myFileFileName;
			//20160808 myFile_1FileName的文件名称已经是有时间元素了
			//myFileFileName = System.currentTimeMillis() + "_" + myFileFileName; // 增加时间戳的文件名

			File savedir = new File(dirPath);
			if (!savedir.exists()) {
				savedir.mkdirs();
			}

			File savefile = new File(savedir, myFileFileName);
			FileUtils.copyFile(myFile, savefile);
			
		    //zipWidthHeightImageFile(myFile,new File("D:\\sgqyxypjdownload\\"+myFileFileName),1500,2000,0.7f);
			Map map = new HashMap();
			map.put("realPath", dirPath+myFileFileName);
			map.put("name", name);
			list.add(map);
	}

	/**
	 * 
	 * describe:下载文件
	 * 2013-12-9
	 * @author txb
	 * @throws IOException 
	 * fileName 显示到页面的文件名称
	 * filePath 文件路径
	 * request 请求
	 * response 响应
	 * dirPath 文件所在文件夹
	 */
	public void downLoadFile(HttpServletRequest request,HttpServletResponse response,String fileName,String realFileName,String dirPath) throws IOException {

		FileInputStream in = null;
		OutputStream out = null;
		try {
//			String filePath = "e:/";  //文件包路径
//			String realFileName = "1387939274500_打包下载.zip";  //真实名称
//			String fileName = "打包下载.zip"; //返回客户端名称
			
			 if(request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
				 fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");//firefox浏览器
			 }
			 else {
				 fileName = URLEncoder.encode(fileName, "UTF-8");//IE浏览器
			 }
			 	
			 //头信息
			 	response.reset();
			    response.setContentType("application/octet-stream");
			    response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
			    response.setHeader("Connection", "close");
			    
			    File file = new File(dirPath + "/" + realFileName); //文件路径
			    
			    
			    
			if (file.exists()) {
				in = new FileInputStream(dirPath + "/" + realFileName);
				out = response.getOutputStream();
				byte[] b = new byte[1024];
				for (int len = 0; (len = in.read(b)) != -1;) {
					out.write(b, 0, len);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
		}

	
		
	}
	/* fcl 20190902 上传图片*/
	public void uploadFiles1() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		System.out.println("SSSSSSSSSSSSSSSSSSSS");
		
		String filePath = request.getParameter("filePath");
		System.out.println("SSSSSSSSSSSSSSSSSSSS"+filePath);
		Map map = null;
		try {
			map = fileUpload(filePath, 50, 10485760);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().print(json.toString());
	}






	
	public void yasuo() throws IOException{
		try {
			String list=request.getParameter("list1");
			String[] list1=list.split(",");
			for(int j = 0 ; j<list1.length ; j++){
				Map<Integer, String> map = PictureChangeSize.readfile(list1[j], null);
				for (int i = 0; i < map.size(); i++) {
					String name = map.get(i);
					if(name.length()>1){
						
						name=name.substring(name.lastIndexOf("\\")+1,name.indexOf("."));
					}
					String oldpath = map.get(i);
					PictureChangeSize.compressImage(map.get(i), "D:/sgqyxypjdownload/"+name+"_1.jpg", 1500, 2000);
				}
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		PrintWriter out;
		out=response.getWriter();
		out.print("SUCCESS");
		out.close();
	}
	
	  public static String zipWidthHeightImageFile(File oldFile,File newFile, int width, int height,float quality) {    
	        if (oldFile == null) {    
	            return null;    
	        }    
	        String newImage = null;    
	        try {    
	            /** 对服务器上的临时文件进行处理 */    
	            Image srcFile = ImageIO.read(oldFile);    
	              
	            String srcImgPath = newFile.getAbsoluteFile().toString();  
	            String subfix = "jpg";  
	            subfix = srcImgPath.substring(srcImgPath.lastIndexOf(".")+1,srcImgPath.length());  
	  
	            BufferedImage buffImg = null;   
	            if(subfix.equals("png")){  
	                buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);  
	            }else{  
	                buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
	            }  
	  
	            Graphics2D graphics = buffImg.createGraphics();  
	            graphics.setBackground(new Color(255,255,255));  
	            graphics.setColor(new Color(255,255,255));  
	            graphics.fillRect(0, 0, width, height);  
	            graphics.drawImage(srcFile.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);    
	  
	            ImageIO.write(buffImg, subfix, new File(srcImgPath));    
	        } catch (FileNotFoundException e) {    
	            e.printStackTrace();    
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        }    
	        return newImage;    
	    }    

	
	/**
	 * 
	 * describe:多个文件打包下载
	 * @throws IOException
	 * 2014-2-12
	 * @author txb
	 */
	public void mulDownload() throws IOException {
//		FileInputStream in = null;
//		OutputStream out = null;
//		
//		try {
//			String fileIds = request.getParameter("fileIds"); //前台传过来的多个文件id
//			fileIds = fileIds.substring(1);
//			
//			
//			List<Map> maps = service.getFileUrls(fileIds); //查询多个文件内容
//			
//			List<File> files = new ArrayList<File>();
//			
//			for(Map map:maps){
//				String fileName = (String) map.get("fileName"); //文件名称  1387939274500_测试3.pdf
//				String filePath = (String) map.get("filePath"); //文件路径
//				
//				File file = new File(filePath + "/" + fileName); //文件路径
//				files.add(file);
//			}
//			String path="E:/打包下载.zip";  //压缩到指定目录
//			File zipfile=new File(path);
//			zipFiles(files,zipfile);
//			
//			String fileName="打包下载.zip";  //下载的文件名
//			
//			//解决下载的文件名乱码问题 begin
//			 if(request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
//				 fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");//firefox浏览器
//			 }
//			 else {
//				 fileName = URLEncoder.encode(fileName, "UTF-8");//IE浏览器
//			 }
//			//解决下载的文件名乱码问题 end
//			 //头信息
//			 	response.reset();
//			    response.setContentType("application/octet-stream");
//			    response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
//			    response.setHeader("Connection", "close");
//			    
//			    File file = new File(path); //文件路径
//			    
//			    
//			if (file.exists()) {
//				in = new FileInputStream(path);
//				out = response.getOutputStream();
//				byte[] b = new byte[1024];
//				for (int len = 0; (len = in.read(b)) != -1;) {
//					out.write(b, 0, len);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//				if (in != null) {
//					in.close();
//				}
//				if (out != null) {
//					out.close();
//				}
//		}
	}
	/**
	 * 
	 * describe:
	 * @param srcfile 多个需要压缩的文件
	 * @param zipfile 压缩后的文件
	 * 2014-2-13
	 * @author txb
	 */
	public void zipFiles(List<File> srcfile,File zipfile){
//        byte[] buf=new byte[1024];
//        try {
//            //ZipOutputStream类：完成文件或文件夹的压缩
//            ZipOutputStream out=new ZipOutputStream(new FileOutputStream(zipfile));
//            for(int i=0;i<srcfile.size();i++){
//                FileInputStream in=new FileInputStream(srcfile.get(i));
//                /**
//                 * 若多个需要压缩的文件中有文件名重复的就将覆盖，我这里用的是简单的方法来解决文件名重复问题
//                 */
//                out.putNextEntry(new ZipEntry("("+(i+1)+")"+srcfile.get(i).getName().substring(14)));
//                int len;
//                while((len=in.read(buf))>0){
//                    out.write(buf,0,len);
//                }
//                out.closeEntry();
//                in.close();
//            }
//            out.close();
            ////////System.out.println("压缩完成.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
	
}
