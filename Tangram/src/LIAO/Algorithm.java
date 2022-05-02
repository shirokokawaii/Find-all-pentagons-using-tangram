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
	LinkedList<Shape> answerSet = new LinkedList<Shape>();
	int count = 0;
	int countSame = 0;
	HashMap<String, Integer> hs = new HashMap<>();
	private void displayAnswer(Shape shape) {
		ArrayList<Integer> angleSet = checkAngle(shape);
		if(shape.points.size() == 5) {
			String angleSetTem=getAngleList(shape);
			if (!hs.containsKey(angleSetTem)) {// angle list is not same
				hs.put(angleSetTem, 0);
			} 
			else {
				countSame++;
			}
			count++;
			answerSet.add(shape);
			System.out.println(angleSet);
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

	public LinkedList<Shape> bfsSearch(Shape shapeIn) {
//		int index = 1;
		LinkedList<Shape> set1 = new LinkedList<Shape>();
		set1.offer(shapeIn);
		for (int i = 0; i < 6; i++) {
			HashMap<String, Integer> angleSetMap = new HashMap<>();
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
					if (shape.contains(s[5]) && shape.points.size() > 11) {
						continue;
					}
					if (!shape.contains(s[5]) && shape.points.size() > 12) {
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
//				if(i==5){
//					if (shape.points.size() !=5) {
//						continue;
//					}
//				}
				String angleSetTem = getAngleList(shape);
				if (!angleSetMap.containsKey(angleSetTem)) {// angle list is not same
////					LinkedList<Shape> tem = new LinkedList<>();
//					tem.add(shape);
//					angleSetMap.put(angleSetTem, tem);
					angleSetMap.put(angleSetTem, 0);
					set1.offer(shape);
				} 
//				else {
//					LinkedList<Shape> tem = angleSetMap.get(angleSetTem);// tem:The shape that has same angleSet
//					int len = tem.size();
//					boolean flag = false;
//					for (int j = 0; j < len; j++) {
//						if (elementsEquals(shape, tem.get(j))) {
//							if(i==5 && shape.skip == tem.get(j).skip){
//								flag = true;
//							}
//							if ((int)shape.skip != (int)tem.get(j).skip) {
//								flag = true;
//							}
//							if (shape.skip == 0) {
//								flag = true;
//							}
//						}
//					}
//					if (flag) {
//						continue;
//					}
//					if (len == 1) {
//						shape.skip = index;
//						tem.get(0).skip = index;
//						index++;
//					}
//					if (len > 1) {
//						double indexTem = index;
//						while(indexTem >1){
//							indexTem *= 0.1;
//						}
//						shape.skip = tem.get(0).skip + indexTem;
//					}
//					tem.add(shape);
//					angleSetMap.put(angleSetTem, tem);
//					set1.offer(shape);
//				}
			}
			Long time3 = System.currentTimeMillis();
			System.out.println("Prune time:" + (time3 - time2) + "ms");
		}
		while (!set1.isEmpty()) {
			displayAnswer(set1.poll());
		}
		System.out.println(count + "different answers");
		System.out.println(countSame+ "same shape answers");
		return answerSet;
	}

	public void dfsSearch() {
		HashMap<ArrayList<Integer>, Integer> angleSetMap = new HashMap<>();
		dfsAlgorithm(s[6], angleSetMap);
		dfsAlgorithm(s[7], angleSetMap);
	}

	private void dfsAlgorithm(Shape shape, HashMap<ArrayList<Integer>, Integer> angleSetMap) {
		if (shape.shapesSet.size() == 7) {
			displayAnswer(shape);
			return;
		}
		for (int j = 0; j < 6; j++) {
			if (!shape.contains(s[j])) {
				Queue<String> edgeSet = getAllEdgePossibility(shape, s[j]);
				while (!edgeSet.isEmpty()) {
					String edge = edgeSet.poll();
					Shape result = new Shape();
					// here needs to get all the possibility edge and connect shapes with specified
					// edge*****
					if (!angleSetMap.containsKey(getAngleList(result))) {
						dfsAlgorithm(result, angleSetMap);
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
		if (shape.shapesSet.size() == 7) {
			displayAnswer(shape);
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

//	private boolean elementsEquals(Shape shape1, Shape shape2) {
//		int count1 = 0;
//		int count2 = 0;
//		if (shape1.contains(s[0])) {
//			count1++;
//		}
//		if (shape1.contains(s[1])) {
//			count1++;
//		}
//		if (shape2.contains(s[0])) {
//			count2++;
//		}
//		if (shape2.contains(s[1])) {
//			count2++;
//		}
//		if (count1 != count2) {
//			return false;
//		}
//		count1 = 0;
//		count2 = 0;
//		if (shape1.contains(s[2])) {
//			count1++;
//		}
//		if (shape2.contains(s[2])) {
//			count2++;
//		}
//		if (count1 != count2) {
//			return false;
//		}
//		count1 = 0;
//		count2 = 0;
//		if (shape1.contains(s[3])) {
//			count1++;
//		}
//		if (shape1.contains(s[4])) {
//			count1++;
//		}
//		if (shape2.contains(s[3])) {
//			count2++;
//		}
//		if (shape2.contains(s[4])) {
//			count2++;
//		}
//		if (count1 != count2) {
//			return false;
//		}
//		count1 = 0;
//		count2 = 0;
//		if (shape1.contains(s[5])) {
//			count1++;
//		}
//		if (shape2.contains(s[5])) {
//			count2++;
//		}
//		if (count1 != count2) {
//			return false;
//		}
//		return true;
//
//	}

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
			angleList[i] += shape.getAngel(j%len)*(5-j+i);
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
	for(int tem:angleList) {
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
}
