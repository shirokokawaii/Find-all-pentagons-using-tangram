package LIAO;

import LIAO.utils.Connector;

import static LIAO.Tangram.*;
import java.awt.*;
import javax.swing.*;

public class Test {
    public static void main(String[] args) {
//        System.out.println(S0.getPoint(1));
//        System.out.println(S0.getPoint(2));
//        System.out.println(S0.getPoint(5));
        // System.out.println(Connector.connect(S0, S1, 1, 1, true));
        // System.out.println(Connector.connectAll(S2, S1));

        //*********************************************************
            JFrame jf = new JFrame("图形可视化工具");
            JPanel jpanel = new JPanel();
            jf.add(jpanel);
            jpanel.setSize(1000, 1000);
            jf.setResizable(false);
            jf.setSize(1000,1000); //设置窗口大小
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//意思就是设置一个默认的关闭操作，也就是你的JFrame窗口的关闭按钮，点击它时，退出程序。
            jf.setVisible(true);// 可视化 显示在屏幕上 
            Shape shape = S6;
            shape.shapesSet.offer(S6);
            shape.shapesSet.offer(S3);
            shape.pointOrder1.offer(0);
            shape.pointOrder2.offer(0);
            shape.orderDirection.offer(true);
            Pen pen = new Pen(jpanel);
            pen.draw(shape);
    }

}