package com.sdkj.util;

import com.google.zxing.LuminanceSource;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
 
/**
 * @program: universities
 * @description: ${description}
 * @author: ShiDunKai
 * @create: 2019-04-29 19:05
 **/
public class BufferedImageLuminanceSource extends LuminanceSource {
 
    private BufferedImage image;
    private int left;
    private int top;
 
    public BufferedImageLuminanceSource(BufferedImage image) {
        this(image, 0, 0, image.getWidth(), image.getHeight());
    }
    protected BufferedImageLuminanceSource(int width, int height) {
        super(width, height);
    }
 
    public LuminanceSource rotateCounterClockwise() {
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();
        AffineTransform transform = new AffineTransform(0.0, -1.0, 1.0,
                0.0, 0.0, sourceWidth);
        BufferedImage rotatedImage = new BufferedImage(sourceHeight,
                sourceWidth, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g = rotatedImage.createGraphics();
        g.drawImage(image, transform, null);
        g.dispose();
        int width = getWidth();
        return new BufferedImageLuminanceSource(rotatedImage, top,
                sourceWidth - (left + width), getHeight(), width);
    }
    //这个方法用来返回判断结果
    public boolean isCropSupported() {
        return true;
    }
    public LuminanceSource crop(int left, int top, int width, int height) {
        return new BufferedImageLuminanceSource(image, this.left + left,
                this.top + top, width, height);
    }
 
 
    public boolean isRotateSupported() {
        return true;
    }
    public BufferedImageLuminanceSource(BufferedImage image, int left,
                                        int top, int width, int height) {
        super(width, height);
 
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();
        if (left + width > sourceWidth || top + height > sourceHeight) {
            throw new IllegalArgumentException(
                    "Crop rectangle does not fit within image data.");
        }
 
        for (int y = top; y < top + height; y++) {
            for (int x = left; x < left + width; x++) {
                if ((image.getRGB(x, y) & 0xFF000000) == 0) {
                    image.setRGB(x, y, 0xFFFFFFFF); // = white
                }
            }
        }
 
        this.image = new BufferedImage(sourceWidth, sourceHeight,
                BufferedImage.TYPE_BYTE_GRAY);
        this.image.getGraphics().drawImage(image, 0, 0, null);
        this.left = left;
        this.top = top;
    }
 
    @Override
    public byte[] getMatrix() {
        int width = getWidth();
        int height = getHeight();
        int area = width * height;
        byte[] matrix = new byte[area];
        image.getRaster().getDataElements(left, top, width, height, matrix);
        return matrix;
    }
    @Override
    public byte[] getRow(int y, byte[] row) {
        if (y < 0 || y >= getHeight()) {
            throw new IllegalArgumentException(
                    "Requested row is outside the image: " + y);
        }
        int width = getWidth();
        if (row == null || row.length < width) {
            row = new byte[width];
        }
        image.getRaster().getDataElements(left, top + y, width, 1, row);
        return row;
    }
 
   //继承后需要重写此方法
    /*public byte[] getRow(int i, byte[] bytes) {
        return new byte[0];
    }
    @
    public byte[] getMatrix() {
        return new byte[0];
    }
    */
 
 
}