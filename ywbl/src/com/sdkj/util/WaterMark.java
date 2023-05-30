package com.sdkj.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.sdkj.util.context.Util;

/*水印工具类*/
public class WaterMark {
	private static String waterMarkPath;

	private static Point getStringWidthAndHeight(String str, Graphics2D g2d) {
		FontMetrics fontMetrics = g2d.getFontMetrics();
		int stringWidth = fontMetrics.stringWidth(str);
		int stringAscent = fontMetrics.getAscent();
		return new Point(stringWidth, stringAscent);
	}

	private static Point calculate(Point p, double imgWidth, double imgHeight) {
		int x = 0, y = 0;
		double hypotenuse = Math.sqrt(Math.pow(imgWidth, 2) + Math.pow(imgHeight, 2));
		double tempX = (p.x / 2) * imgWidth / hypotenuse;
		double tempY = (p.x / 2) * imgHeight / hypotenuse;
		x = (int) (imgWidth / 2 - tempX);
		y = (int) (imgHeight / 2 + tempY);
		return new Point(x, y);
	}

	private static void printWatemark(String projectName,String KcName, String supervisor, String printDate, Graphics2D g2d, int x, int y) {
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.5f)); /* alpha值控制字体变淡 */
		g2d.drawString(projectName, x - 50, y - 50);
		g2d.drawString(KcName, x-25, y-25);
		g2d.drawString(supervisor, x, y);
		g2d.drawString(printDate, x + 35, y + 35);
	}

	private static RenderingHints getMyRenderingHints() {
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB);
		rh.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		rh.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
		rh.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		return rh;
	}

	public static boolean watermark(File file, Font font, String projectName,String KcName,
			String supervisor, String printDate, String addDate) {
		System.out.println("file"+file);
		Image image = null;
		BufferedImage buffImg;
		try {
			image = ImageIO.read(file);
			buffImg = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = buffImg.createGraphics();
		g2d.setRenderingHints(getMyRenderingHints());
		g2d.setFont(font);
		g2d.setColor(Color.RED);
		g2d.fillRect(0, 0, width, height);
		g2d.drawImage(image, 0, 0, width, height, null);

		double imgWidth = buffImg.getWidth();
		double imgHeight = buffImg.getHeight();
System.out.println("supervisor="+supervisor);
System.out.println("g2d="+KcName);
		Point p = getStringWidthAndHeight(supervisor, g2d);
		Point p1 = calculate(p, imgWidth, imgHeight);
		g2d.rotate(-Math.atan(imgHeight / imgWidth), p1.x, p1.y);
		printWatemark(projectName, KcName,supervisor, printDate, g2d, p1.x, p1.y);
		g2d.dispose();
		try {
			ImageIO.write(buffImg, "jpg", new File(waterMarkPath + addDate + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	/* createWaterMark为水印入口方法，addDate是生成的水印图片名 */
	public static void createWaterMark(String projectName, String KcName,String supervisor, String printDate, String addDate) {
		projectName=Util.nullToString(projectName);
		KcName=Util.nullToString(KcName);
		supervisor=Util.nullToString(supervisor);
		Font font = new Font("宋体", Font.PLAIN, 22);
		System.out.println("1111"+waterMarkPath);
		System.out.println("new File(waterMarkPath=="+new File(waterMarkPath));
		WaterMark.watermark(new File(waterMarkPath + "blank500.jpg"), font, projectName, KcName,supervisor, printDate, addDate);
	}

	public String getWaterMarkPath() {
		return waterMarkPath;
	}

	public void setWaterMarkPath(String waterMarkPath) {
		WaterMark.waterMarkPath = waterMarkPath;
	}

}
