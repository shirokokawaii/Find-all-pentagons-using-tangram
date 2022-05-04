package LIAO;

import static LIAO.entity.Tangram.*;

import java.util.LinkedList;
import javax.swing.*;

public class Test {
	public static void main(String[] args) {
    	
         JFrame jf = new JFrame("图形可视化工具");
         JPanel jp = new JPanel();
         jf.add(jp);
         jp.setSize(600, 600);
         jf.setSize(600, 600);
         jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         jf.setVisible(true);
         int X = 250;
         int Y = 300;
         int size = 50;
         
         Algorithm algorithm = new Algorithm(S0, S1, S2, S3, S4, S5, S6, S7);
         algorithm.displayWhileCalculating(jf, jp, X, Y, size);
         LinkedList<LinkedList<Shape>> answerList = new LinkedList<>();
         //algorithm.bfsSearch();
         algorithm.dfsSearch();
         //algorithm.aStarSearch();
         answerList = algorithm.getAnswerList();
         
         for(int i=0;i<answerList.size();i++) {
        	 for(int j=0;j<answerList.get(i).size();j++) {
                 Pen pen = new Pen(jf, jp);
                 pen.beforeDraw(answerList.get(i).get(j), 300, 300, 50);
                 pen.draw("C:/Users/Dalao/Desktop/pics/pic"+i+"-"+j+".jpg");
                 jp.repaint();
                 System.out.println(i+" "+j);
        	 }
         }
        Shape shape = new Shape();
        LinkedList<Shape> s= Connector.connectAll(S5, S3);
       Pen pen = new Pen(jf, jp);
       pen.beforeDraw(s.get(0), 300, 300, 50);
       pen.draw();

    }
}