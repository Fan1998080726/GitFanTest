package com.sdkj.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * @author wxb
 * @date 2011-01-14 
 * @versions 1.0 图片压缩工具类 提供的方法中可以设定生成的
 * 缩略图片的大小尺寸等
 */
public class PictureChangeSize {
	
	
	public static void main(String args[]) {
		try {
			Map<Integer, String> map = readfile("D:/temp/", null);
			for (int i = 0; i < map.size(); i++) {
				String name = map.get(i);
				name=name.substring(name.lastIndexOf("\\")+1,name.indexOf("."));
				
//				System.out.println(map.get(i) + " ==" + i);
//				System.out.println();
				String oldpath = map.get(i);

				compressImage(map.get(i), "D:/temp_1/"+name+"_1.jpg", 1500, 2000);
			}
		} catch (Exception ex) {
		}
//		System.out.println("ok");
	}
	
	
	/** 
	 * 图片文件读取 
	 * @param srcImgPath 
	 * @return 
	 */
	private static BufferedImage InputImage(String srcImgPath) {
		BufferedImage srcImage = null;
		try {
			FileInputStream in = new FileInputStream(srcImgPath);
			srcImage = javax.imageio.ImageIO.read(in);
		} catch (IOException e) {
			System.out.println("读取图片文件出错！" + e.getMessage());
			e.printStackTrace();
		}
		return srcImage;
	}

	/**
	 * * 将图片按照指定的图片尺寸压缩
	 * @param srcImgPath :源图片路径
	 * @param outImgPath  :输出的压缩图片的路径 
	 * @param new_w :压缩后的图片宽
	 * @param new_h :压缩后的图片高
	 */
	public static void compressImage(String srcImgPath, String outImgPath,
			int new_w, int new_h) {
		BufferedImage src = InputImage(srcImgPath);
		disposeImage(src, outImgPath, new_w, new_h);
	}

	/**
	 * 指定长或者宽的最大值来压缩图片 
	 * @param srcImgPath :源图片路径 
	 * @param outImgPath :输出的压缩图片的路径 
	 * @param maxLength :长或者宽的最大值
	 */
	public static void compressImage(String srcImgPath, String outImgPath,
			int maxLength) {
		// 得到图片
		BufferedImage src = InputImage(srcImgPath);
		if (null != src) {
			int old_w = src.getWidth();
			// 得到源图宽
			int old_h = src.getHeight();
			// 得到源图长
			int new_w = 0;
			// 新图的宽
			int new_h = 0;
			// 新图的长
			// 根据图片尺寸压缩比得到新图的尺寸
			if (old_w > old_h) {
				// 图片要缩放的比例
				new_w = maxLength;
				new_h = (int) Math.round(old_h * ((float) maxLength / old_w));
			} else {
				new_w = (int) Math.round(old_w * ((float) maxLength / old_h));
				new_h = maxLength;
			}
			disposeImage(src, outImgPath, new_w, new_h);
		}
	}

	/** 
	 * 处理图片
	 * @param src 
	 * @param outImgPath 
	 * @param new_w 
	 * @param new_h 
	 */
	private synchronized static void disposeImage(BufferedImage src,
			String outImgPath, int new_w, int new_h) {
		// 得到图片
		
		try {
			System.out.println("  private synchronized static void disposeImage(    aaaaaaaaa");
			int old_w = src.getWidth();
			
			// 得到源图宽
			int old_h = src.getHeight();
			// 得到源图长
			BufferedImage newImg = null;
			// 判断输入图片的类型
			switch (src.getType()) {
			case 13:
				// png,gifnewImg = new BufferedImage(new_w, new_h,
				// BufferedImage.TYPE_4BYTE_ABGR);
				break;
			default:
				newImg = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
				break;
			}
			Graphics2D g = newImg.createGraphics();
			// 从原图上取颜色绘制新图
			g.drawImage(src, 0, 0, old_w, old_h, null);
			g.dispose();
			// 根据图片尺寸压缩比得到新图的尺寸
			newImg.getGraphics().drawImage(
					src.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0, 0,
					null);
			// 调用方法输出图片文件
			OutImage(outImgPath, newImg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("  private synchronized static void disposeImage(   bbbbbbbbbbbb");
		}
	}

	/**
	 * 将图片文件输出到指定的路径，并可设定压缩质量 
	 * @param outImgPath 
	 * @param newImg 
	 * @param per
	 */
	private static void OutImage(String outImgPath, BufferedImage newImg) {
		// 判断输出的文件夹路径是否存在，不存在则创建
		File file = new File(outImgPath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}// 输出到文件流
		try {
			ImageIO.write(newImg,
					outImgPath.substring(outImgPath.lastIndexOf(".") + 1),
					new File(outImgPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Map<Integer, String> readfile(String filepath,
			Map<Integer, String> pathMap) throws Exception {
		if (pathMap == null) {
			pathMap = new HashMap<Integer, String>();
		}

		File file = new File(filepath);
		// 文件
		if (!file.isDirectory()) {
			pathMap.put(pathMap.size(), file.getPath());

		} else if (file.isDirectory()) { // 如果是目录， 遍历所有子目录取出所有文件名
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(filepath + "/" + filelist[i]);
				if (!readfile.isDirectory()) {
					pathMap.put(pathMap.size(), readfile.getPath());

				} else if (readfile.isDirectory()) { // 子目录的目录
					readfile(filepath + "/" + filelist[i], pathMap);
				}
			}
		}
		return pathMap;
	}
}