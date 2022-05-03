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
        jpanel.setSize(600, 600);
        jf.setSize(600, 600); //设置窗口大小
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//意思就是设置一个默认的关闭操作，也就是你的JFrame窗口的关闭按钮，点击它时，退出程序。
        jf.setVisible(true);// 可视化 显示在屏幕上

//        AlgorithmTest algorithm = new AlgorithmTest(S0, S1, S2, S3, S4, S5, S6, S7);
//        LinkedList<Shape> answerList = new LinkedList<>();
//        algorithm.bfsSearch(S6);
//        algorithm.bfsSearch(S7);
//        //algorithm.dfsSearch();

        Algorithm algorithm = new Algorithm(S0, S1, S2, S3, S4, S5, S6, S7);
        LinkedList<LinkedList<Shape>> answerList = new LinkedList<>();
        //algorithm.bfsSearch();
        algorithm.dfsSearch();
        //algorithm.aStarSearch();
        answerList = algorithm.getAnswerList();

        int count = 1;

        for(int i=0;i<answerList.size();i++) {
            for(int j=0;j<answerList.get(i).size();j++) {
                if(!IDA.hasSame(answerList.get(i).get(j))){
                    Pen pen = new Pen(jf, jpanel);
                    pen.beforeDraw(answerList.get(i).get(j), 300, 300, 50);
                    pen.draw("X:/javaProject/Find-all-pentagons-using-tangram/Tangram/ima_output/dfs/pic"+count+"-"+j+".jpg");
                    jpanel.repaint();
                    System.out.println(i+" "+j);
                    count++;
                }
            }
        }

//        answerList = algorithm.getAnswerList();
//        ArrayList<Shape> unilist = new ArrayList<>();
//        for (int i = 0; i < answerList.size(); i++) {
//            if (!IDA.hasSame(answerList.get(i))) {
//                unilist.add(answerList.get(i));
//                System.out.println("i:"+i+"\n"+IDA.points2String(answerList.get(i).points));
//            } else {
//                System.out.println("same"+i +"\n"+IDA.points2String(answerList.get(i).points));
//            }
//            System.out.println(answerList.get(i).points);
//            System.out.println("finished:"+i);
//
//        }


//            for (int j = 0; j < unilist.size(); j++) {
//                Pen4idea pen = new Pen4idea(jf, jpanel);
//                pen.beforeDraw(unilist.get(j), 300, 300, 50);
//                pen.draw("X:/javaProject/Find-all-pentagons-using-tangram/Tangram/ima_output/dfs/pic" + j + ".jpg");
//                jpanel.repaint();
//                System.out.println(j);
//            }
//


//            Shape test = answerList.get(51);


//        Shape s1 = Connector.connect(S7, S2, 0, 1, true);
//        Shape s2 = Connector.connect(s1, S1, 3, 0, false);
//		Shape s3 = Connector.connect(s2, S4, 5, 0, false);
//		Shape s4 = Connector.connect(s3, S4, 3, 0, true);
//		Shape s5 = Connector.connect(s4, S5, 3, 1, true);
//		Shape s6 = Connector.connect(s5, S1, 4, 1, false);
//
//            Shape test = s6;
//            System.out.println("---------" + test + "----------");
//            System.out.println("ShapeList:  " + test.shapeList);
//            System.out.println("order1:  " + test.debugPointOrderA);
//            System.out.println("order2:  " + test.debugPointOrderB);
//            System.out.println("direction:  " + test.debugDirection);
//            System.out.println("ShapeSet:  " + test);
//            System.out.println("order:  " + test.pointOrder);
//            System.out.println("skip:  " + test.skip);

//        int i = 1;
//        for (Shape s:test.shapesSet){
//            System.out.println("skip :"+ i + " " + s.skip);
//            i++;
//        }

//        Pen4idea pen = new Pen4idea(jf, jpanel);
//        pen.beforeDraw(test, 300, 300, 50);
//        pen.draw("X:/javaProject/Find-all-pentagons-using-tangram/Tangram/pic.jpg");
//        jpanel.repaint();

//        Pen pen = new Pen(jpanel);
//
//        pen.beforeDraw(answerList.get(0), 500, 500, 50);
//
//        pen.draw("C:/Users/Dalao/Desktop/pic.jpg");
            //pen.draw(true);
        }
    }
