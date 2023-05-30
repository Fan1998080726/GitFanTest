package com.sdkj.util.fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

/**
 * 
 * @date 2013-12-9
 * @author txb
 */
public abstract class BaseFileUpload{
	
	/**
	 * 最多上传的5个文件
	 */
	protected File myFile_1;
	protected File myFile_2;
	protected File myFile_3;
	protected File myFile_4;
	protected File myFile_5;
	/**
	 * 最多上传的5个文件的名称
	 */
	protected String myFile_1FileName;
	protected String myFile_2FileName;
	protected String myFile_3FileName;
	protected String myFile_4FileName;
	protected String myFile_5FileName;

//	private String projectFilePath="d:/projectFile";
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

	public String getMyFile_1FileName() {
		return myFile_1FileName;
	}

	public String getMyFile_2FileName() {
		return myFile_2FileName;
	}

	public void setMyFile_2FileName(String myFile_2FileName) {
		this.myFile_2FileName = myFile_2FileName;
	}

	public void setMyFile_1FileName(String myFile_1FileName) {
		this.myFile_1FileName = myFile_1FileName;
	}

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

	/**
	 * 
	 * describe:文件上传
	 * @return
	 * @throws Exception
	 * 2013-12-9
	 * @author txb
	 * @param request 
	 * @param obj 保存每个文件时传的参数
	 */
	public List fileUpload(String dirPath,Object obj,int nameLength,int fileLength) throws Exception{
			List list=new ArrayList();
			if (myFile_1 != null) {
				Map map=new HashMap();
				map.put("fileName", myFile_1FileName);
				if(myFile_1FileName.length()>nameLength){
					map.put("message", "文件名称过长！");
					list.add(map);
				}
				if (myFile_1.length() <= fileLength){
					copyFile(myFile_1, myFile_1FileName,dirPath,obj);
				}else{
					map.put("message", "上传文件最大为1M！");
					list.add(map);
				}
			}
			if (myFile_2 != null) {
				Map map=new HashMap();
				map.put("fileName", myFile_2FileName);
				if(myFile_2FileName.length()>nameLength){
					map.put("message", "文件名称过长！");
					list.add(map);
				}
				if (myFile_2.length() <= fileLength){
					copyFile(myFile_2, myFile_2FileName,dirPath,obj);
				}else{
					map.put("message", "上传文件最大为1M！");
					list.add(map);
				}
				
			}
			if (myFile_3 != null) {
				Map map=new HashMap();
				map.put("fileName", myFile_3FileName);
				if(myFile_3FileName.length()>nameLength){
					map.put("message", "文件名称过长！");
					list.add(map);
				}
				if (myFile_3.length() <= fileLength){
					copyFile(myFile_3, myFile_3FileName,dirPath,obj);
				}else{
					map.put("message", "上传文件最大为1M！");
					list.add(map);
				}
				
			}
			if (myFile_4 != null) {
				Map map=new HashMap();
				map.put("fileName", myFile_4FileName);
				if(myFile_4FileName.length()>nameLength){
					map.put("message", "文件名称过长！");
					list.add(map);
				}
				if (myFile_4.length() <= fileLength){
					copyFile(myFile_4, myFile_4FileName,dirPath,obj);
				}else{
					map.put("message", "上传文件最大为1M！");
					list.add(map);
				}
				
			}
			if (myFile_5 != null) {
				Map map=new HashMap();
				map.put("fileName", myFile_5FileName);
				if(myFile_5FileName.length()>nameLength){
					map.put("message", "文件名称过长！");
					list.add(map);
				}
				if (myFile_5.length() <= 1048576){
					copyFile(myFile_5, myFile_5FileName,dirPath,obj);
				}else{
					map.put("message", "上传文件最大为1M！");
					list.add(map);
				}
				
			}
			return list;
	}
	/**
	 * describe:异步文件上传
	 * @param dirPath 文件保存在指定文件夹下
	 * @param id 文件对应的
	 * @return
	 * @throws Exception 
	 */
	public Map fileUploadAjax(String dirPath,Object obj,int nameLength,int fileLength) throws Exception{
		Map map=new HashMap();
		if(myFile_1!=null){
			map.put("fileName", myFile_1FileName);
			if(myFile_1FileName.length()>nameLength){
				map.put("message", "文件名称过长！");
			}
			if (myFile_1.length() <= fileLength){
				copyFile(myFile_1, myFile_1FileName,dirPath,obj);
			}else{
				map.put("message", "上传文件最大为1M！");
			}
		}else if(myFile_2!=null){
			map.put("fileName", myFile_2FileName);
			if(myFile_2FileName.length()>nameLength){
				map.put("message", "文件名称过长！");
			}
			if (myFile_2.length() <= fileLength){
				copyFile(myFile_2, myFile_2FileName,dirPath,obj);
			}else{
				map.put("message", "上传文件最大为1M！");
			}
		}else if(myFile_3!=null){
			map.put("fileName", myFile_3FileName);
			
			if(myFile_3FileName.length()>nameLength){
				map.put("message", "文件名称过长！");
			}
			if (myFile_3.length() <= fileLength){
				copyFile(myFile_3, myFile_3FileName,dirPath,obj);
			}else{
				map.put("message", "上传文件最大为1M！");
			}
		}else if(myFile_4!=null){
			map.put("fileName", myFile_4FileName);
			
			if(myFile_4FileName.length()>nameLength){
				map.put("message", "文件名称过长！");
			}
			if (myFile_4.length() <= fileLength){
				copyFile(myFile_4, myFile_4FileName,dirPath,obj);
			}else{
				map.put("message", "上传文件最大为1M！");
			}
		}else if(myFile_5!=null){
			map.put("fileName", myFile_5FileName);
			
			if(myFile_5FileName.length()>nameLength){
				map.put("message", "文件名称过长！");
			}
			if (myFile_5.length() <= fileLength){
				copyFile(myFile_5, myFile_5FileName,dirPath,obj);
			}else{
				map.put("message", "上传文件最大为1M！");
			}
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
	public void copyFile(File myFile, String myFileFileName,String dirPath,Object obj) throws Exception {

			myFileFileName = System.currentTimeMillis() + "_" + myFileFileName; // 增加时间戳的文件名

			File savedir = new File(dirPath);
			if (!savedir.exists()) {
				savedir.mkdirs();
			}

			File savefile = new File(savedir, myFileFileName);
			FileUtils.copyFile(myFile, savefile);
			saveFilePath(myFileFileName,obj);
	}
	/**
	 * 文件存储 需子类实现
	 * 2013-03-10
	 * @author txb
	 * @param myFileFileName 文件名
	 * @param Object obj 参数
	 */
	public abstract void saveFilePath(String myFileFileName,Object obj) throws Exception;

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
	public void downLoadFile(HttpServletRequest request,HttpServletResponse response,String fileName,String realFileName) throws IOException {

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
			    
			    File file = new File(realFileName); //文件路径
			    
			    
			    
			if (file.exists()) {
				in = new FileInputStream(realFileName);
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
            //////System.out.println("压缩完成.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
	
}


