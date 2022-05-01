package LIAO;

import LIAO.entity.DrawOutline;
import LIAO.entity.Point;
import com.alibaba.fastjson.JSON;

import javax.swing.*;
import java.util.*;

import static LIAO.entity.Tangram.*;
import static LIAO.entity.Tangram.S7;


public class AlgorithmTest {

	public static void main(String[] args) {
		JFrame jf = new JFrame("图形可视化工具");
		JPanel jpanel = new JPanel();
		jf.add(jpanel);
		jpanel.setSize(1000, 1000);
		jf.setResizable(false);
		jf.setSize(1000, 1000); //设置窗口大小
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//意思就是设置一个默认的关闭操作，也就是你的JFrame窗口的关闭按钮，点击它时，退出程序。
		jf.setVisible(true);// 可视化 显示在屏幕上
		DrawOutline p = new DrawOutline(jpanel);

		Algorithm algorithm = new Algorithm(S0, S1, S2, S3, S4, S5, S6, S7);

		//algorithm.bfsSearch(S6);
		//algorithm.bfsSearch(S7);

		//algorithm.bfsSearch(4, 9);
		System.out.println(algorithm.answerSet);
		System.out.println("----------------" + algorithm.answerSet.size() + "----------------");
		//int size = algorithm.answerSet.size() - 119;
		//Shape test = algorithm.answerSet.get(3);
		Shape test = S8;

		Shape s1 = Connector.connect(S6, S0, 0, 0, false);
		Shape s2 = Connector.connect(s1, S1, 1, 1, false);
		Shape s3 = Connector.connect(s2, S2, 4, 0, false);
		Shape s4 = Connector.connect(s3, S3, 3, 0, true);
		Shape s5 = Connector.connect(s3, S5, 3, 0, true);
		Shape s6 = Connector.connect(s3, S4, 4, 1, true);


		String jsonOutput= JSON.toJSONString(s4.getPoints());
		System.out.println(jsonOutput);

//		Shape test = JSON.parseObject(jsonOutput, Shape.class);
		//Shape test = new Shape();
//		test.setPoints(JSON.parseObject(jsonOutput, ArrayList<Point>.class));

		System.out.println("---------" + test + "----------");
		System.out.println("ShapeSet:  " + test.shapeList);
		System.out.println("order1:  " + test.debugPointOrderA);
		System.out.println("order2:  " + test.debugPointOrderB);
		System.out.println("direction:  " + test.debugDirection);
		p.draw(S8);
	}

}

