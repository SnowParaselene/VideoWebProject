package com.cyzy.system.utils;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * version:v1.0
 * className:CodePicture
 * packageName:utils
 * description:生成验证码
 **/
public class CodePicture {

    // 验证码字符集
    private static final char[] chars = ("123456789"
            + "ABCDEFGHJKMNPQRSTUVWXYZ"
            + "abcdefghjkmnpqrstuvwxyz").toCharArray();
    // 字符数量
    private static final int SIZE = 4;
    // 干扰线数量
    private static final int LINES = 6;
    // 字体大小
    private static final int FONT_SIZE = 30;


    /**
     * 生成随机验证码及图片
     * 0-随机验证码
     * 1-生成的图片
     */
    public static Object[] createImage(int width,int height) {
        StringBuilder sb = new StringBuilder();
        // 1.创建空白图片
        BufferedImage image = new BufferedImage(
                width, height, BufferedImage.TYPE_INT_RGB);
        // 2.获取图片画笔
        Graphics graphic = image.getGraphics();
        // 3.设置画笔颜色
        graphic.setColor(Color.WHITE);
        // 4.绘制矩形背景
        graphic.fillRect(0, 0, width, height);
        // 5.画随机字符
        Random ran = new Random();
        for (int i = 0; i < SIZE; i++) {
            // 取随机字符索引
            int n = ran.nextInt(chars.length);
            // 设置随机颜色
            graphic.setColor(getRandomColor());
            // 设置字体大小
            graphic.setFont(new Font(
                    null, Font.BOLD + Font.ITALIC, FONT_SIZE));
            // 画字符
            graphic.drawString(
                    chars[n] + "", i * width / SIZE, height / 2 + 10);
            // 记录字符
            sb.append(chars[n]);
        }
        // 6.画干扰线
        for (int i = 0; i < LINES; i++) {
            // 设置随机颜色
            graphic.setColor(getRandomColor());
            // 随机画线
            graphic.drawLine(ran.nextInt(width), ran.nextInt(height),
                    ran.nextInt(width), ran.nextInt(height));
        }
        // 7.返回验证码和图片
        return new Object[]{sb.toString(), image};
    }

    /**
     * 随机取色
     */
    public static Color getRandomColor() {
        Random ran = new Random();
        return new Color(ran.nextInt(256),
                ran.nextInt(256), ran.nextInt(256));
    }


}
