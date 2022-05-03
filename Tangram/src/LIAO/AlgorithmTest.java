package LIAO;

import LIAO.entity.DrawOutline;
import LIAO.entity.Point;
import com.alibaba.fastjson.JSON;

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
			this.s[6] = s6;// s6 is the base
			this.s[7] = s7;// s7 is another base
		}
		Shape[] s = new Shape[8];
		LinkedList<Shape> answerSet = new LinkedList<Shape>();
		int count = 0;
		private void displayAnswer(Shape shape) {
			if(shape.points.size() == 5) {
				ArrayList<Integer> angleSet = checkAngle(shape);
				count++;
				answerSet.add(shape);
				System.out.println((count-1)+":"+angleSet);
			}
			// here needs to draw and display the answer shape*****
		}
		public LinkedList<Shape> getAnswerList() {
			return answerSet;
		}
		public ArrayList<Integer> checkAngle(Shape shape) {
			int len = shape.size();
			ArrayList<Integer> angleSet = new ArrayList<Integer>();
			for (int i = 0; i < len; i++) {
				angleSet.add(shape.points.get(i).getAngle());
			}
			return angleSet;
		}

		public void bfsSearch(Shape shapeIn) {
			int acuracy = 2;//0:highest
			int index = 1;
			LinkedList<Shape> set1 = new LinkedList<Shape>();
			set1.offer(shapeIn);
			for (int i = 0; i < 6; i++) {
				HashMap<String, LinkedList<Shape>> angleSetMap = new HashMap<>();
				System.out.println("Adding " + (i + 1) + "st shape");
				LinkedList<Shape> set2 = new LinkedList<Shape>();
				Long time1 = System.currentTimeMillis();
				while (!set1.isEmpty()) {
					Shape order = set1.poll();
					for (int j = 0; j < 6; j++) {
						if (!order.contains(s[j])) {
							set2.addAll(Connector.connectAll(order, s[j]));
						}
					}
				}
				Long time2 = System.currentTimeMillis();
				System.out.println("Connect time:" + (time2 - time1) + "ms");

				while (!set2.isEmpty()) {
					Shape shape = set2.poll();
					if (shape == null) {
						continue;
					}
					if (i == 3) {
						if (shape.contains(s[5]) && shape.points.size() > 11-acuracy) {
							continue;
						}
						if (!shape.contains(s[5]) && shape.points.size() > 12-acuracy) {
							continue;
						}
					}
					if (i == 4) {
						if (shape.contains(s[5]) && shape.points.size() > 8) {
							continue;
						}
						if (!shape.contains(s[5]) && shape.points.size() > 9) {
							continue;
						}
					}
					if(i==5){
						if (shape.points.size() !=5) {
							continue;
						}
					}
					String angleSetTem = getAngleList(shape);
					if (!angleSetMap.containsKey(angleSetTem)) {// angle list is not same
						LinkedList<Shape> tem = new LinkedList<>();
						tem.add(shape);
						angleSetMap.put(angleSetTem, tem);
//					angleSetMap.put(angleSetTem, 0);
						set1.offer(shape);
					}
					else {
						LinkedList<Shape> tem = angleSetMap.get(angleSetTem);// tem:The shape that has same angleSet
						int len = tem.size();
						boolean flag = false;
						for (int j = 0; j < len; j++) {
							if (elementsEquals(shape, tem.get(j))) {
								if(i==5 && shape.skip == tem.get(j).skip){
									flag = true;
								}
								if ((int)shape.skip != (int)tem.get(j).skip) {
									flag = true;
								}
								if (shape.skip == 0) {
									flag = true;
								}
							}
						}
						if (flag) {
							continue;
						}
						if (len == 1) {
							shape.skip = index;
							tem.get(0).skip = index;
							index++;
						}
						if (len > 1) {
							double indexTem = index;
							while(indexTem >1){
								indexTem *= 0.1;
							}
							shape.skip = tem.get(0).skip + indexTem;
						}
						tem.add(shape);
						angleSetMap.put(angleSetTem, tem);
						set1.offer(shape);
					}
				}
				Long time3 = System.currentTimeMillis();
				System.out.println("Prune time:" + (time3 - time2) + "ms");
			}
			while (!set1.isEmpty()) {
				displayAnswer(set1.poll());
			}
			System.out.println(count + "different answers");
		}

		public void dfsSearch() {
			HashMap<String, Integer> angleSetMap = new HashMap<>();
			dfsAlgorithm(s[6], angleSetMap);
			System.out.println("50%");
			dfsAlgorithm(s[7], angleSetMap);
		}

		private void dfsAlgorithm(Shape shape, HashMap<String, Integer> angleSetMap) {
			if(shape == null) {
				return;
			}
			if (shape.shapeList.size() == 4) {
				if (shape.contains(s[5]) && shape.points.size() > 11) {
					return;
				}
				if (!shape.contains(s[5]) && shape.points.size() > 12) {
					return;
				}
			}
			if (shape.shapeList.size() == 5) {
				if (shape.contains(s[5]) && shape.points.size() > 8) {
					return;
				}
				if (!shape.contains(s[5]) && shape.points.size() > 9) {
					return;
				}
			}

			if (shape.shapeList.size() == 6) {
				String angleList = getAngleList(shape);
				if(!angleSetMap.containsKey(angleList)) {
					angleSetMap.put(angleList, 0);
					displayAnswer(shape);
				}
				return;
			}

			for (int j = 0; j < 6; j++) {
				if (!shape.contains(s[j])) {
					int len1 = shape.size();
					int len2 = s[j].size();
					for(int h=0;h<len1;h++) {
						for(int k=0;k<len2;k++) {
							Shape result1 = Connector.connect(shape, s[j], h, k, true);
							dfsAlgorithm(result1, angleSetMap);
							System.out.println("shapeA.size:"+shape.shapeList.size()+",j: "+j+",h: "+h+",k:"+k);
							Shape result2 = Connector.connect(shape, s[j], h, k, false);
							dfsAlgorithm(result2, angleSetMap);
						}
					}

				}
			}
		}

		public void aStarSearch() {
			aStarAlgorithm(s[6]);
			aStarAlgorithm(s[7]);
		}

		private void aStarAlgorithm(Shape shape) {
			if (shape.shapeList.size() == 6) {
				displayAnswer(shape);
				System.out.println(shape.size());
				return;
			}
			ArrayList<ArrayList<Shape>> costSet = new ArrayList<ArrayList<Shape>>();
			Queue<Shape> set = new LinkedList<Shape>();
			for (int i = 0; i < 7; i++) {// up to decagon
				costSet.add(new ArrayList<Shape>());
			}
			for (int i = 0; i < 6; i++) {// calculate all the cost in a row
				if (!shape.contains(s[i])) {
					set = Connector.connectAll(shape, s[i]);
					while (!set.isEmpty()) {
						Shape shapeTem = set.poll();
						int cost = 5 - shapeTem.points.size();
						if (cost > 5) {
							costSet.get(6).add(shapeTem);
						} else {
							costSet.get(cost).add(shapeTem);
						}
					}
				}
			}
			for (int i = 0; i < 7; i++) {// connect the shape from the lowest cost
				int len = costSet.get(i).size();
				for (int j = 0; j < len; j++) {
					Shape shapeTem = costSet.get(i).get(j);
					aStarAlgorithm(shapeTem);
				}
			}
		}

		private boolean elementsEquals(Shape shape1, Shape shape2) {
			int count1 = 0;
			int count2 = 0;
			if (shape1.contains(s[0])) {
				count1++;
			}
			if (shape1.contains(s[1])) {
				count1++;
			}
			if (shape2.contains(s[0])) {
				count2++;
			}
			if (shape2.contains(s[1])) {
				count2++;
			}
			if (count1 != count2) {
				return false;
			}
			count1 = 0;
			count2 = 0;
			if (shape1.contains(s[2])) {
				count1++;
			}
			if (shape2.contains(s[2])) {
				count2++;
			}
			if (count1 != count2) {
				return false;
			}
			count1 = 0;
			count2 = 0;
			if (shape1.contains(s[3])) {
				count1++;
			}
			if (shape1.contains(s[4])) {
				count1++;
			}
			if (shape2.contains(s[3])) {
				count2++;
			}
			if (shape2.contains(s[4])) {
				count2++;
			}
			if (count1 != count2) {
				return false;
			}
			count1 = 0;
			count2 = 0;
			if (shape1.contains(s[5])) {
				count1++;
			}
			if (shape2.contains(s[5])) {
				count2++;
			}
			if (count1 != count2) {
				return false;
			}
			return true;

		}

//	public String getAngleList(Shape shape) {
//		String answer = new String();
//		int len = shape.size();
//		int min = 8;
//		int index = 0;
//		for (int i = 0; i < len; i++) {
//			int tem = shape.points.get(i).getAngle();
//			if (tem <= min) {
//				min = tem;
//				index = i;
//			}
//		}
//		for (int i = 0; i < len; i++) {
//			if (index > len - 1) {
//				index = 0;
//			}
//			answer += Integer.toString(shape.points.get(index).getAngle());
//			index++;
//		}
//
//		return answer;
//	}

		public String getAngleList(Shape shape) {
			String answer = new String();
			int len = shape.size();
			int[] angleList = new int[len];
			for(int i=0;i<len;i++) {
				for(int j=i;j<len+i;j++) {
					angleList[i] += shape.getAngel(j%len)*(len-j+i);
					j++;
				}
			}
			for(int i=0;i<len-1;i++) {
				for(int j=0;j<len-i-1;j++) {
					if(angleList[j]>angleList[j+1]) {
						int tem = angleList[j+1];
						angleList[j+1] = angleList[j];
						angleList[j] = tem;
					}
				}
			}
			for(int i=0;i<len;i++) {
				int tem = angleList[i];
				answer += Integer.toString(tem);
			}
			return answer;
		}

		private Queue<String> getAllEdgePossibility(Shape shape1, Shape shape2) {
			Queue<String> edgeSet = new LinkedList<String>();
			// here calculate all the possible edge combination and store them into
			// edgeSet.*****
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
		//Pen newP = new Pen(jpanel);
		AlgorithmTest algorithm = new AlgorithmTest(S0, S1, S2, S3, S4, S5, S6, S7);

		algorithm.bfsSearch(S6);
		algorithm.bfsSearch(S7);

		//algorithm.dfsSearch();
		System.out.println(algorithm.answerSet);
		System.out.println("----------------" + algorithm.answerSet.size() + "----------------");
		//int size = algorithm.answerSet.size() - 119;
		//Shape test = algorithm.answerSet.get(3);
		//Shape test = algorithm.answerSet.get(3);

		Shape s1 = Connector.connect(S6, S0, 0, 0, true);
		Shape s2 = Connector.connect(s1, S2, 1, 1, false);
		Shape s3 = Connector.connect(s2, S1, 4, 0, false);
//		Shape s4 = Connector.connect(s3, S3, 3, 0, true);
//		Shape s5 = Connector.connect(s3, S5, 3, 0, true);
//		Shape s6 = Connector.connect(s3, S4, 4, 1, true);


		//String jsonOutput= JSON.toJSONString(s4.getPoints());
		//System.out.println(jsonOutput);

//		Shape test = JSON.parseObject(jsonOutput, Shape.class);
		Shape test = s2;
//		test.setPoints(JSON.parseObject(jsonOutput, ArrayList<Point>.class));

		System.out.println("---------" + test + "----------");
//		System.out.println("ShapeSet:  " + test.shapeList);
//		System.out.println("order1:  " + test.debugPointOrderA);
//		System.out.println("order2:  " + test.debugPointOrderB);
//		System.out.println("direction:  " + test.debugDirection);
		System.out.println("ShapeSet:  " + test);


		System.out.println("ShapeSet:  " + test.shapesSet);
		System.out.println("order:  " + test.pointOrder);
		//System.out.println("" + test.debugPointOrderB);
		//System.out.println("direction:  " + test.debugDirection);
//		newP.beforeDraw(test, 500, 500, 50);
//		//         pen.draw(true);
//		//p.draw(test);
//		newP.draw(true);
//
//		newP.draw(false);


	}

}

