package LIAO;

import LIAO.entity.DrawOutline;

import javax.swing.*;
import java.util.*;

import static LIAO.entity.Tangram.*;
import static LIAO.entity.Tangram.S7;


public class AlgorithmTest {
	public AlgorithmTest(Shape s0, Shape s1, Shape s2, Shape s3, Shape s4, Shape s5, Shape s6, Shape s7) {
		this.s[0] = s0;
		this.s[1] = s1;
		this.s[2] = s2;
		this.s[3] = s3;
		this.s[4] = s4;
		this.s[5] = s5;
		this.s[6] = s6;//s6 is the base
		this.s[7] = s7;//s7 is another base
	}
	Shape[] s = new Shape[8];
	ArrayList<Shape> answerSet = new ArrayList<Shape>();

	private void displayAnswer(Shape shape) {
		ArrayList<Integer> angleSet = checkAngle(shape);
		if(shape.points.size()==5) {//This shape is a pentagon, draw and display it
			answerSet.add(shape);
			System.out.println(shape.size());
			System.out.println(angleSet);
			//here needs to draw and display the answer shape*****
		}
		// else {//This shape is not a pentagon, only output its angleSet on the terminal
		// 	System.out.print(angleSet.size() );
		// 	System.out.println(angleSet);
		// }
	}

	public ArrayList<Integer> checkAngle(Shape shape) {
		int len = shape.size();
		ArrayList<Integer> angleSet = new ArrayList<Integer>();
		for(int i=0;i<len;i++) {
			angleSet.add(shape.points.get(i).getAngle());
		}
		return angleSet;
	}

	public ArrayList<Shape> bfsSearch(int level, int max) {
		Queue<Shape> set1 = new LinkedList<Shape>();
		set1.offer(s[6]);
		set1.offer(s[7]);
		for(int i=0;i<level;i++) {
			HashMap<ArrayList<Integer>,Integer> angleSetMap = new HashMap<>();
			System.out.println("Adding "+(i+1)+"st shape");
			Queue<Shape> set2 = new LinkedList<Shape>();
			while(!set1.isEmpty()) {
				Shape order = new Shape();
				order = set1.poll();
				for(int j=0;j<6;j++) {
					if(!order.contains(s[j])) {
						set2.addAll(Connector.connectAll(order,s[j]));
						// set2 = Connector.connectAll(order,s[j]);
					}
				}
			}
			while(!set2.isEmpty()){
				Shape shape = set2.poll();
				if(shape == null || shape.points.size()>=max){
					continue;
				}
				ArrayList<Integer> angleSetTem = getAngleList(shape);
				if(!angleSetMap.containsKey(angleSetTem)){
					angleSetMap.put(angleSetTem, 1);
					set1.offer(shape);
				}
			}
		}
		while(!set1.isEmpty()) {
			displayAnswer(set1.poll());
		}
		return answerSet;
	}



	private ArrayList<Integer> getAngleList(Shape shape){
		ArrayList<Integer> angleSet = new ArrayList<Integer>();
		int len = shape.size();
		int min = 8;
		int index = 0;
		for(int i=0;i<len;i++){
			int tem = shape.points.get(i).getAngle();
			if(tem <= min){
				min = tem;
				index = i;
			}
		}
		for(int i=0;i<len;i++){
			if(index > len-1){
				index = 0;
			}
			angleSet.add(shape.points.get(index).getAngle());
			index++;
		}
		return angleSet;
	}

	private Queue<String> getAllEdgePossibility(Shape shape1, Shape shape2) {
		Queue<String> edgeSet = new LinkedList<String>();
		//here calculate all the possible edge combination and store them into edgeSet.*****
		return edgeSet;
	}

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

		AlgorithmTest algorithm = new AlgorithmTest(S0, S1, S2, S3, S4, S5, S6, S7);
		//algorithm.bfsSearch(4, 9);
		//System.out.println(algorithm.answerSet);
		//System.out.println("aaaaAAaaaaa"+algorithm.answerSet.size());
		//int size = algorithm.answerSet.size() - 119;
		//Shape test = algorithm.answerSet.get(size);

		Shape s1 = Connector.connect(S6, S0, 0, 1, false);
		Shape s2 = Connector.connect(s1, S3, 0, 0, false);
		Shape s3 = Connector.connect(s2, S4, 4, 0, false);
//		Shape s3 = Connector.connect(s2, S4, 4, 0, true);

		Shape s4 = Connector.connect(s3, S1, 4, 1, false);

		Shape test = s3;

		System.out.println("---------" + test + "----------");
		System.out.println("ShapeSet:  " + test.shapeList);
		System.out.println("order1:  " + test.debugPointOrderA);
		System.out.println("order2:  " + test.debugPointOrderB);
		System.out.println("direction:  " + test.debugDirection);
		p.draw(test);
	}

}

