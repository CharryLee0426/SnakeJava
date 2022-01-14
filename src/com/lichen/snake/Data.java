package com.lichen.snake;

import javax.swing.*;
import java.net.URL;

public class Data {
    // 宣传栏图片 URL类定位图片地址  ImageIcon: 图片
    public static URL headerURL = Data.class.getResource("/statics/header.png");
    public static ImageIcon header = new ImageIcon(headerURL);

    // 贪吃蛇头部, 有上下左右四种朝向
    public static URL upURL = Data.class.getResource("/statics/up.png");
    public static URL downURL = Data.class.getResource("/statics/down.png");
    public static URL leftURL = Data.class.getResource("/statics/left.png");
    public static URL rightURL = Data.class.getResource("/statics/right.png");
    public static ImageIcon up = new ImageIcon(upURL);
    public static ImageIcon down = new ImageIcon(downURL);
    public static ImageIcon left = new ImageIcon(leftURL);
    public static ImageIcon right = new ImageIcon(rightURL);

    // 身体
    public static URL bodyURL = Data.class.getResource("/statics/body.png");
    public static ImageIcon body = new ImageIcon(bodyURL);

    // 食物
    public static URL foodURL = Data.class.getResource("/statics/food.png");
    public static ImageIcon food = new ImageIcon(foodURL);
}
