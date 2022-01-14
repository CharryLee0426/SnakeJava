package com.lichen.snake;

import javax.swing.*;

/**
 * @author lichen
 * @description 这是程序的入口点，调用面板程序初始化程序。
 */
public class StartGames {
    public static void main(String[] args) {
        // 绘制静态窗口 JFrame
        JFrame frame = new JFrame("贪吃蛇小游戏");

        // 设置界面大小, 窗口大小不可改变, 添加关闭事件, 设置窗口可见
        frame.setBounds(10,10,900,720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 绘制面板 JPanel
        frame.add(new GamePanel());

        frame.setVisible(true);
    }
}
