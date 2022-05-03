package LIAO;

import java.util.*;

public class Algorithm {
	public Algorithm(Shape s0, Shape s1, Shape s2, Shape s3, Shape s4, Shape s5, Shape s6, Shape s7) {
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
	int answerIndex = 0;
	LinkedList<LinkedList<Shape>> answerSet = new LinkedList<>();
	HashMap<String, Integer> hs = new HashMap<>();
	int count = 0;
	int different = 0;

	private void displayAnswer(Shape shape) {
		if (shape.points.size() == 5) {
			String angleList = getAngleList(shape);
			if (!hs.containsKey(angleList)) {
				different++;
				hs.put(angleList, answerIndex);
				LinkedList<Shape> tem = new LinkedList<>();
				tem.add(shape);
				answerSet.add(tem);
				answerIndex++;
			} else {
				int tem = hs.get(angleList);
				answerSet.get(tem).add(shape);
			}
			ArrayList<Integer> angleSet = checkAngle(shape);
			count++;
			System.out.println((count - 1) + ":" + angleSet + " " + getAngleList(shape));
			if(count == 32) {
				Shape test = shape;
				System.out.println("---------" + test + "----------");
				System.out.println("ShapeList:  " + test.shapeList);
				System.out.println("order1:  " + test.debugPointOrderA);
				System.out.println("order2:  " + test.debugPointOrderB);
				System.out.println("direction:  " + test.debugDirection);
				System.out.println("ShapeSet:  " + test);
				System.out.println("order:  " + test.pointOrder);
				System.out.println("skip:  " + test.skip);
			}
		}
		// here needs to draw and display the answer shape*****
	}

	public LinkedList<LinkedList<Shape>> getAnswerList() {
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
		int acuracy = 3;// 0:highest
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
			while (!set2.isEmpty()) {
				Shape shape = set2.poll();
				if (shape == null) {
					continue;
				}
				if (i == 3) {
					if (shape.contains(s[5]) && shape.points.size() > 11 - acuracy) {
						continue;
					}
					if (!shape.contains(s[5]) && shape.points.size() > 12 - acuracy) {
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
				if (i == 5) {
					if (shape.points.size() != 5) {
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
				} else {
					if (i == 5) {
						continue;
					}
					LinkedList<Shape> tem = angleSetMap.get(angleSetTem);// tem:The shape that has same angleSet
					int len = tem.size();
					boolean flag = false;
					for (int j = 0; j < len; j++) {
						if (elementsEquals(shape, tem.get(j))) {
							if (shape.skip == tem.get(j).skip) {
								flag = true;
								continue;
							}
							if ((int) shape.skip != (int) tem.get(j).skip) {
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
						while (indexTem > 1) {
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
			System.out.println("time:" + (time3 - time1) + "ms");
		}
		while (!set1.isEmpty()) {
			displayAnswer(set1.poll());
		}
		System.out.println(count + "answers");
		System.out.println(different + "different answers");
	}

	public void dfsSearch() {
		HashMap<String, Integer> answerSetNotEqual = new HashMap<>();
		Long time1 = System.currentTimeMillis();
		dfsAlgorithm(s[6], answerSetNotEqual);
		System.out.println("50%");
		dfsAlgorithm(s[7], answerSetNotEqual);
		Long time2 = System.currentTimeMillis();
		System.out.println("time" + (time2 - time1));
	}

	public void dfsAlgorithm(Shape shape, HashMap<String, Integer> answerSetNotEqual) {
		if(shape == null) {
			return;
		}
		HashMap<String, LinkedList<Shape>> angleSetMapLocal = new HashMap<>();
		if (shape.shapesSet.size() == 6) {
			String tem = getAngleList(shape);
			if (!answerSetNotEqual.containsKey(tem)) {
				answerSetNotEqual.put(tem, 0);
				displayAnswer(shape);
			}
			return;
		}
		for (int j = 0; j < 6; j++) {
			if (!shape.contains(s[j])) {
				for (int b = 0; b < shape.size(); b++) {
					for (int n = 0; n < symCheck(s[j]); n++) {
						for (int k = 0; k < 2; k++) {
							Shape newShape = null;
							newShape = Connector.connect(shape, s[j], b, n, k == 0);
							if (newShape != null) {
								newShape.skip = shape.skip;
								newShape = check(newShape, j, 0, angleSetMapLocal);
								dfsAlgorithm(newShape, answerSetNotEqual);
							}
						}
					}
				}
			}
		}
	}

	private static int symCheck(Shape shape) {
		if (shape.size() == 4)
			return 1;
		else
			return 3;
	}

	int index = 0;

	private Shape check(Shape shape, int i, int acuracy, HashMap<String, LinkedList<Shape>> angleSetMap) {
		if (shape == null) {
			return null;
		}
		if (i == 3) {
			if (shape.contains(s[5]) && shape.points.size() > 11 - acuracy) {
				return null;
			}
			if (!shape.contains(s[5]) && shape.points.size() > 12 - acuracy) {
				return null;
			}
		}
		if (i == 4) {
			if (shape.contains(s[5]) && shape.points.size() > 8) {
				return null;
			}
			if (!shape.contains(s[5]) && shape.points.size() > 9) {
				return null;
			}
		}
		if (i == 5) {
			if (shape.points.size() != 5) {
				return null;
			}
		}
		String angleSetTem = getAngleList(shape);
		if (!angleSetMap.containsKey(angleSetTem)) {// angle list is not same
			LinkedList<Shape> tem = new LinkedList<>();
			tem.add(shape);
			angleSetMap.put(angleSetTem, tem);
			return shape;
		} else {
			if (i == 5) {
				return null;
			}
			LinkedList<Shape> tem = angleSetMap.get(angleSetTem);// tem:The shape that has same angleSet
			int len = tem.size();
			boolean flag = false;
			for (int j = 0; j < len; j++) {
				if (elementsEquals(shape, tem.get(j))) {
					if (shape.skip == tem.get(j).skip) {
						flag = true;
						continue;
					}
					if ((int) shape.skip != (int) tem.get(j).skip) {
						flag = true;
					}
				}
			}
			if (flag) {
				return null;
			}
			if (len == 1) {
				shape.skip = index;
				tem.get(0).skip = index;
				index++;
			}
			if (len > 1) {
				double indexTem = index;
				while (indexTem > 1) {
					indexTem *= 0.1;
				}
				shape.skip = tem.get(0).skip + indexTem;
			}
			tem.add(shape);
			angleSetMap.put(angleSetTem, tem);
			return shape;
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

	public String getAngleList(Shape shape) {
		String answer = new String();
		int len = shape.points.size();
		int[] angleList = new int[len];
		for (int i = 0; i < len; i++) {
			for (int j = i; j < len + i; j++) {
				angleList[i] += ((shape.getAngel(j % len)) * Math.pow(10, len - j + i) + shape.getLength(j));
			}
		}
		for (int i = 0; i < len - 1; i++) {
			for (int j = 0; j < len - i - 1; j++) {
				if (angleList[j] > angleList[j + 1]) {
					int tem = angleList[j + 1];
					angleList[j + 1] = angleList[j];
					angleList[j] = tem;
				}
			}
		}
		for (int i = 0; i < len; i++) {
			int tem = angleList[i];
			String str = Integer.toString(tem);
			answer = answer + str;
		}
		return answer;
	}

//	public String getAngleListReverse1(Shape shape) {
//	String answer = new String();
//	int len = shape.points.size();
//	int[] angleList = new int[len];
//	for(int i=0;i<len;i++) {
//		for(int j=i;j<len+i;j++) {
//			angleList[i] += (shape.getAngel(j%len)+shape.getLength(j%len))*Math.pow(10, len-j+i);
//		}
//	}
//	for(int i=0;i<len-1;i++) {
//		for(int j=0;j<len-i-1;j++) {
//			if(angleList[j]>angleList[j+1]) {
//				int tem = angleList[j+1];
//				angleList[j+1] = angleList[j];
//				angleList[j] = tem;
//			}
//		}
//	}
//	for(int i=0;i<len;i++) {
//		int tem = angleList[i];
//		String str = Integer.toString(tem);
//		answer = answer + str ;
//	}
//	return answer;
//}
//	
//	public String getAngleListReverse2(Shape shape) {
//	String answer = new String();
//	int len = shape.points.size();
//	int[] angleList = new int[len];
//	for(int i=0;i<len;i++) {
//		for(int j=len+len-1-i;j>len-i-1;j--) {
//			angleList[i] += (shape.getAngel(j%len)+shape.getLength((j-1)%len))*Math.pow(10, j-len+i);
//		}
//	}
//	for(int i=0;i<len-1;i++) {
//		for(int j=0;j<len-i-1;j++) {
//			if(angleList[j]>angleList[j+1]) {
//				int tem = angleList[j+1];
//				angleList[j+1] = angleList[j];
//				angleList[j] = tem;
//			}
//		}
//	}
//	for(int i=0;i<len;i++) {
//		int tem = angleList[i];
//		answer += Integer.toString(tem);
//	}
//	return answer;
//}
}
