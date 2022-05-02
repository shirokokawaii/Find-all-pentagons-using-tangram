package LIAO;


import static LIAO.entity.Tangram.*;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.*;

public class Test2 {
    public static void main(String[] args) {


        // JFrame jf = new JFrame("图形可视化工具");
        // JPanel jpanel = new JPanel();
        // jf.add(jpanel);
        // jpanel.setSize(1000, 1000);
        // jf.setResizable(false);
        // jf.setSize(1000, 1000); //设置窗口大小
        // jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//意思就是设置一个默认的关闭操作，也就是你的JFrame窗口的关闭按钮，点击它时，退出程序。
        // jf.setVisible(true);// 可视化 显示在屏幕上
        // Shape shape = S6;
        // shape.shapesSet.offer(S6);
        // shape.shapesSet.offer(S3);
//        shape.pointOrder1.offer(3);
//        shape.pointOrder2.offer(1);
//        shape.orderDirection.offer(true);
//        Pen pen = new Pen(jpanel);
//        pen.draw(shape);
        JFrame jf = new JFrame("图形可视化工具");
        JPanel jpanel = new JPanel();
        jf.add(jpanel);
        jpanel.setSize(1000, 1000);
        jf.setSize(1000, 1000); //设置窗口大小
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//意思就是设置一个默认的关闭操作，也就是你的JFrame窗口的关闭按钮，点击它时，退出程序。
        jf.setVisible(true);// 可视化 显示在屏幕上

        Algorithm algorithm = new Algorithm(S0, S1, S2, S3, S4, S5, S6, S7);
        LinkedList<Shape> answerList = new LinkedList<>();
        algorithm.bfsSearch(S6);
        algorithm.bfsSearch(S7);
        answerList = algorithm.getAnswerList();
        for(int i=0;i<answerList.size();i++) {
            Pen pen = new Pen(jf, jpanel);
            pen.beforeDraw(answerList.get(i), 500, 500, 50);
            pen.draw("C:/Users/Dalao/Desktop/pics/pic"+i+".jpg");
            jpanel.repaint();
            System.out.println(i);
        }
        System.out.println("skip4"+answerList.get(4).skip);
        System.out.println(algorithm.getAngleList(answerList.get(4)));
        System.out.println("skip5"+answerList.get(5).skip);
        System.out.println(algorithm.getAngleList(answerList.get(5)));
//       pen.beforeDraw(answerList.get(0), 500, 500, 50);
//       pen.draw("C:/Users/Dalao/Desktop/pic.jpg");
        //pen.draw(true);
    }
}