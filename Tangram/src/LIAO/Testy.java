package LIAO;

import LIAO.entity.DrawOutline;
import LIAO.entity.Point;

import javax.swing.*;
import java.util.LinkedList;

import static LIAO.entity.Tangram.S0;
import static LIAO.entity.Tangram.S1;

public class Testy {
    public static void main(String[] args) throws CloneNotSupportedException {

        //Shape s = AnotherConnector.connect(S0, S1, 1, 2, false);
        //System.out.println(s);


        JFrame jf = new JFrame("图形可视化工具");
        JPanel jpanel = new JPanel();
        jf.add(jpanel);
        jpanel.setSize(1000, 1000);
        jf.setResizable(false);
        jf.setSize(1000, 1000); //设置窗口大小
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//意思就是设置一个默认的关闭操作，也就是你的JFrame窗口的关闭按钮，点击它时，退出程序。
        jf.setVisible(true);// 可视化 显示在屏幕上

        DrawOutline p = new DrawOutline(jpanel);

        LinkedList<Shape> shape = Connector.connectAll(S0, S1);
//        LinkedList<Shape> shape1 = connectAll(shape.get(8), S3);
//        LinkedList<Shape> shape3 = connectAll(shape1.get(3), S5);
//        LinkedList<Shape> shape5 = connectAll(shape3.get(19), S5);

//        Shape test = shape3.get(19);
        Shape test = shape.get(11);

        System.out.println(test);
        //System.out.println("ShapeSet:  " + test.shapesSet);
        System.out.println("order1:  " + test.pointOrder);
        for (Point point:test.points){
            System.out.println("\nname:  " + point.getName());

        }
//        System.out.println("Direction:  " + test.orderDirection);
        p.draw(test);


    }
}
