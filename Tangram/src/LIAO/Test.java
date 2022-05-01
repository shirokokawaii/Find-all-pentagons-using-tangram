package LIAO;


import static LIAO.entity.Tangram.*;

import javax.swing.*;

public class Test {
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
        Algorithm algorithm = new Algorithm(S0, S1, S2, S3, S4, S5, S6, S7);

        algorithm.bfsSearch(S6);
        //algorithm.bfsSearch(S7);
        JFrame jf = new JFrame("图形可视化工具");
        JPanel jpanel = new JPanel();
        jf.add(jpanel);
        jpanel.setSize(1000, 1000);
        jf.setResizable(false);
        jf.setSize(1000, 1000); //设置窗口大小
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//意思就是设置一个默认的关闭操作，也就是你的JFrame窗口的关闭按钮，点击它时，退出程序。
        jf.setVisible(true);// 可视化 显示在屏幕上
        Shape shape = S6;
        Shape shape2 = Connector.connect(shape, S3, 2, 0, true);
        Shape shape3 = Connector.connect(shape2, S5, 2, 0, true);

        shape.shapesSet.offer(S6);
        shape.shapesSet.offer(S3);
       shape.pointOrder.offer((char) 3);
       Pen pen = new Pen(jpanel);
       pen.draw(shape);
        // Algorithm algorithm = new Algorithm(S0, S1, S2, S3, S4, S5, S6, S7);

        // algorithm.bfsSearch(S6);
        // algorithm.bfsSearch(S7);

    }
}