package com.lichen.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    // 画板： 画界面, 画蛇, Graphics对象可以想象成一支笔

    // 蛇的长度，蛇的坐标，蛇的方向
    String fx;    // 定义上下左右四个方向。"U" "D" "L" "R"
    int length;
    int[] snakeX = new int[600];
    int[] snakeY = new int[500];
    int score;
    int maxscore = 0;
    boolean isStart;
    boolean isFail;
    int foodX, foodY;   // 食物坐标
    Timer timer = new Timer(100, this);     //定时器
    Random random = new Random();   // 生成随机数

    public void init() {
        fx = "R";
        isStart = isFail = false;
        length = 3;
        snakeX[0] = 100;    snakeY[0] = 100;    // 头部坐标
        snakeX[1] = 75;    snakeY[1] = 100;     // 第一个和第二个身体坐标
        snakeX[2] = 50;    snakeY[2] = 100;

        foodX = 25 + 25 * random.nextInt(34);
        foodY = 75 + 25 * random.nextInt(24);

        score = 0;
    }

    // 构造器
    public GamePanel() {
        init();

        // 获取键盘的焦点和监听事件
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();  // 打开计时器，否则帧不会刷新
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);    //清屏
        this.setBackground(Color.WHITE);    //设置背景颜色

        // 绘制宣传栏
        Data.header.paintIcon(this, g, 305, 5);

        // 绘制游戏区域
        g.fillRect(25,75, 850, 600);

        // 画静态的蛇
        if (fx.equals("R")) {
            Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("U")) {
            Data.up.paintIcon(this, g, snakeX[0],snakeY[0]);
        } else if (fx.equals("L")) {
            Data.left.paintIcon(this, g, snakeX[0],snakeY[0]);
        } else if (fx.equals("D")) {
            Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        // 演示
        // Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
        // Data.body.paintIcon(this, g, snakeX[1], snakeY[1]);
        // Data.body.paintIcon(this, g, snakeX[2], snakeY[2]);

        for (int i = 1; i < length; i++) {
            //Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);
            g.setColor(Color.GREEN);
            g.fillRect(snakeX[i], snakeY[i], 25, 25 );
        }

        g.setColor(Color.RED);
        g.setFont(new Font("微软雅黑", Font.BOLD, 18));
        g.drawString("长度: " + length, 750, 15);
        g.drawString("得分: " + score, 750, 40);
        g.drawString("最高分: " + maxscore, 750, 65);

        // 画食物
        Data.food.paintIcon(this, g, foodX, foodY);

        if (isStart == false) {
            // 画开始文字
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("按下空格开始...", 300, 300);
        }

        if (isFail == true) {
            // 画失败文字
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("我死了，按下空格重新来过...", 200,300);
        }
    }

    // 监听键盘输入, 键盘敲击, 键盘按下, 键盘释放
    @Override
    public void keyPressed(KeyEvent e) {
        // 获取按下的键盘
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            if (isFail) {
                isFail = !isFail;
                init(); // 重新初始化
            } else {
                isStart = !isStart;
            }
            repaint();  // 重新绘制，即刷新界面
        }

        // 键盘控制方向
        if (keyCode == KeyEvent.VK_LEFT && !(snakeX[1] - snakeX[0] == -25) && !(fx.equals("R"))) {
            fx = "L";
        } else if (keyCode == KeyEvent.VK_RIGHT && !(snakeX[1] - snakeX[0] == 25) && !(fx.equals("L"))) {
            fx = "R";
        } else if (keyCode == KeyEvent.VK_UP && !(snakeY[1] - snakeY[0] == -25) && !(fx.equals("D"))) {
            fx = "U";
        } else if (keyCode == KeyEvent.VK_DOWN && !(snakeY[1] - snakeX[0] == 25) && !(fx.equals("U"))) {
            fx = "D";
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    // 定时器, 监听时间, 帧
    @Override
    public void actionPerformed(ActionEvent e) {
        //若游戏开始，则右移
        if (isStart && !isFail) {
            // 除了头部，所有身体都向前移动
            for (int i = length - 1; i > 0; i--) {
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
            }
            // snakeX[0] += 25;    //头部移动

            //通过方向让头部定向移动
            if (fx.equals("R")) {
                if (snakeX[0] < 850) snakeX[0] += 25; else snakeX[0] = 25;
            } else if (fx.equals("L")) {
                if (snakeX[0] > 25) snakeX[0] -= 25; else snakeX[0] = 850;
            } else if (fx.equals("U")) {
                if (snakeY[0] > 75) snakeY[0] -= 25; else snakeY[0] = 650;
            } else if (fx.equals("D")) {
                if (snakeY[0] < 650) snakeY[0] += 25; else snakeY[0] = 75;
            }

            // 若小蛇的头和食物重合，则吃食物，长度增长，重新生成食物
            if (snakeX[0] == foodX && snakeY[0] == foodY) {
                length++;
                // 取消闪烁
                snakeX[length-1] = snakeX[length-2] * 2 - snakeX[length-3];
                snakeY[length-1] = snakeY[length-2] * 2 - snakeY[length-3];
                // 重新生成食物并且加分
                foodX = 25 + 25 * random.nextInt(34);
                foodY = 75 + 25 * random.nextInt(24);
                score += 10;
            }

            // 查找蛇的头部是否与身体重合，若重合就失败。
            for (int i = 1;i < length; i++) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                    isFail = true;
                    maxscore = maxscore > score? maxscore: score;
                }
            }

            // 边界判断
            /*if (snakeX[0] > 850) {
                snakeX[0] = 25;
            }*/

            repaint();  // 刷新页面
        }
        timer.start();      //让计时器开始计时, 则动起来
    }
}
