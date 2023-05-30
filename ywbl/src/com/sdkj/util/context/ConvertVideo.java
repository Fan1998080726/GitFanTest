package com.sdkj.util.context;

import java.io.File;
import java.util.List;

public class ConvertVideo {

	private static String PATH = "";

	public static void main(String[] args) {
//		if (!checkfile(PATH)) {
//			//////System.out.println(PATH + " is not file");
//			return;
//		}
//		if (process()) {
//			//////System.out.println("ok");
//		}
	}
	
	
	
	public  static  String fileConvert(String filepath , String profilename) throws Exception{
		

		String flag = "";
		
		if (process(filepath , profilename)) {
			flag = "ok";
			//File file = new File(filepath);
			//file.delete();
		}else{
			flag = "no";
		}
		
		return flag;
	}

	private static boolean process( String filepath ,  String profilename) throws Exception {
		
		
		int type = checkContentType(filepath);
		boolean status = false;
		if (type == 0) {
			status = processFLV(filepath , profilename);// 直接将文件转为flv文件
		} else if (type == 1) {
			String avifilepath = processAVI(type);
			if (avifilepath == null)
				return false;// avi文件没有得到
			status = processFLV(avifilepath , profilename);// 将avi转为flv
		}
		return status;
	}

	private static int checkContentType(String filepath) {
		String type = filepath.substring(filepath.lastIndexOf(".") + 1, filepath.length())
				.toLowerCase();
		// ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
		if (type.equals("avi")) {
			return 0;
		} else if (type.equals("mpg")) {
			return 0;
		} else if (type.equals("wmv")) {
			return 0;
		} else if (type.equals("3gp")) {
			return 0;
		} else if (type.equals("mov")) {
			return 0;
		} else if (type.equals("mp4")) {
			return 0;
		} else if (type.equals("asf")) {
			return 0;
		} else if (type.equals("asx")) {
			return 0;
		} else if (type.equals("flv")) {
			return 0;
		}
		// 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),
		// 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
		else if (type.equals("wmv9")) {
			return 1;
		} else if (type.equals("rm")) {
			return 1;
		} else if (type.equals("rmvb")) {
			return 1;
		}
		return 9;
	}

	private static boolean checkfile(String path) {
		File file = new File(path);
		if (!file.isFile()) {
			return false;
		}
		return true;
	}

	// 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等), 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
	private static String processAVI(int type) {
		List<String> commend = new java.util.ArrayList<String>();
		/*
		 * commend.add("f:\\mencoder"); commend.add(PATH); commend.add("-oac");
		 * commend.add("lavc"); commend.add("-lavcopts");
		 * commend.add("acodec=mp3:abitrate=64"); commend.add("-ovc");
		 * commend.add("xvid"); commend.add("-xvidencopts");
		 * commend.add("bitrate=600"); commend.add("-of"); commend.add("avi");
		 * commend.add("-o"); commend.add("f:\\rmvb.avi");
		 */
		commend.add("E:\\mencoder");
		commend.add(PATH);
		commend.add("-oac");
		commend.add("mp3lame");
		commend.add("-lameopts");
		commend.add("preset=64");
		commend.add("-ovc");
		commend.add("xvid");
		commend.add("-xvidencopts");
		commend.add("bitrate=600");
		commend.add("-of");
		commend.add("avi");
		commend.add("-o");
		commend.add("E:\\rmvb.avi");
		// 命令类型：mencoder 1.rmvb -oac mp3lame -lameopts preset=64 -ovc xvid
		// -xvidencopts bitrate=600 -of avi -o rmvb.avi
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			builder.start();
			return "E:\\rmvb.avi";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
	private static boolean processFLV(String oldfilepath ,   String profilename) throws Exception{

		if (!checkfile(oldfilepath)) {
			//////System.out.println(oldfilepath + " is not file");
			return false;
		}

	    String fileflvpath = oldfilepath.substring(0,oldfilepath.lastIndexOf(".")+1) + "flv";
		
		List<String> commend = new java.util.ArrayList<String>();
		commend.add(profilename + "move\\ffmpeg.exe");
		commend.add("-i");
		commend.add(oldfilepath);
		commend.add("-ab");
		commend.add("64");
		commend.add("-acodec");
		commend.add("mp3");
		commend.add("-ac");
		commend.add("2");
		commend.add("-ar");
		commend.add("22050");
		commend.add("-b");
		commend.add("230");
		commend.add("-r");
		commend.add("24");
		commend.add("-y");
		commend.add(fileflvpath);
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			builder.start();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
