package LIAO;

import static LIAO.entity.Tangram.*;
import java.util.LinkedList;
import javax.swing.*;

public class Test {
	public static void main(String[] args) {
    	
         JFrame jf = new JFrame("图形可视化工具");
         JPanel jpanel = new JPanel();
         jf.add(jpanel);
         jpanel.setSize(600, 600);
         jf.setSize(600, 600); //设置窗口大小
         jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//意思就是设置一个默认的关闭操作，也就是你的JFrame窗口的关闭按钮，点击它时，退出程序。
         jf.setVisible(true);// 可视化 显示在屏幕上
         
         Algorithm algorithm = new Algorithm(S0, S1, S2, S3, S4, S5, S6, S7);
         LinkedList<LinkedList<Shape>> answerList = new LinkedList<>();
         algorithm.bfsSearch();
         //algorithm.dfsSearch();
         //algorithm.aStarSearch();
         answerList = algorithm.getAnswerList();
         
         for(int i=0;i<answerList.size();i++) {
        	 for(int j=0;j<answerList.get(i).size();j++) {
                 Pen pen = new Pen(jf, jpanel);
                 pen.beforeDraw(answerList.get(i).get(j), 300, 300, 50);
                 pen.draw("C:/Users/Dalao/Desktop/pics/pic"+i+"-"+j+".jpg");
                 jpanel.repaint();
                 System.out.println(i+" "+j);
        	 }
         }
    }
}