package LIAO;

import static LIAO.entity.Tangram.S6;
import static LIAO.entity.Tangram.S7;

import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

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

	Long time = System.currentTimeMillis();
	Shape[] s = new Shape[8];
	int answerIndex = 0;
	LinkedList<LinkedList<Shape>> answerSet = new LinkedList<>();
	HashMap<String, Integer> answerSetNotEqual = new HashMap<>();
	HashMap<String, Integer> hs = new HashMap<>();
	JFrame jf;
	JPanel jp;
	int X = 0;
	int Y = 0;
	int size = 0;
	int count = 0;
	int different = 0;

	void displayWhileCalculating(JFrame jf, JPanel jp, int X, int Y, int size) {
		this.jf = jf;
		this.jp = jp;
		this.X = X;
		this.Y = Y;
		this.size = size;
	}

	private void draw(Shape shape) {
		Pen pen = new Pen(jf, jp);
		pen.beforeDraw(shape, X, Y, size);
		pen.draw();
		jp.repaint();
	}

	private void displayAnswer(Shape shape) {
		if (shape.points.size() == 5) {
			String angleList = getAngleList(shape);
			if (!hs.containsKey(angleList)) {
				if (IDA.hasSame(shape)) {
					return;
				}
				different++;
				hs.put(angleList, answerIndex);
				LinkedList<Shape> tem = new LinkedList<>();
				tem.add(shape);
				if (jp != null) {
					draw(shape);
				}
				answerSet.add(tem);
				answerIndex++;
			} else {
				int tem = hs.get(angleList);
				if (jp != null) {
					draw(shape);
				}
				answerSet.get(tem).add(shape);
			}
			LinkedList<Integer> angleSet = checkAngle(shape);
			count++;
			long timeNow = System.currentTimeMillis();
			System.out.println((count - 1) + ":" + angleSet + " " + (timeNow - time) / 1000 + "s");
		}
	}

	public LinkedList<LinkedList<Shape>> getAnswerList() {
		return answerSet;
	}

	public LinkedList<Integer> checkAngle(Shape shape) {
		int len = shape.size();
		LinkedList<Integer> angleSet = new LinkedList<Integer>();
		for (int i = 0; i < len; i++) {
			angleSet.add(shape.points.get(i).getAngle());
		}
		return angleSet;
	}

	public void bfsSearch() {
		time = System.currentTimeMillis();
		bfsAlgorithm(S6);
		bfsAlgorithm(S7);
		System.out.println("End: " + ((System.currentTimeMillis() - time) / 1000) + "s");
	}

	public void bfsAlgorithm(Shape shapeIn) {
		int acuracy = 4;// 0:highest
		LinkedList<Shape> set1 = new LinkedList<Shape>();
		LinkedList<Shape> set2 = new LinkedList<Shape>();
		set1.offer(shapeIn);
		for (int i = 0; i < 6; i++) {
			HashMap<String, Boolean> angleSetMap = new HashMap<>();
			System.out.println("Adding " + (i + 1) + "st shape");
			for (int q = 0; q < set1.size(); q++) {
				Shape order = set1.get(q);
				for (int j = 0; j < 6; j++) {
					if (!order.contains(s[j])) {
						set2.addAll(Connector.connectAll(order, s[j]));
					}
				}
			}
			set1.clear();
			for (Shape shape : set2) {
				if (shape == null) {
					continue;
				}
				if (i == 1) {
					if (shape.contains(s[5]) && shape.points.size() > 17 - acuracy * 3) {
						continue;
					}
					if (!shape.contains(s[5]) && shape.points.size() > 18 - acuracy * 3) {
						continue;
					}
				}
				if (i == 2) {
					if (shape.contains(s[5]) && shape.points.size() > 14 - acuracy * 2) {
						continue;
					}
					if (!shape.contains(s[5]) && shape.points.size() > 15 - acuracy * 2) {
						continue;
					}
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
					angleSetMap.put(angleSetTem, null);
					set1.offer(shape);
				}
			}
			set2.clear();
		}
		while (!set1.isEmpty()) {
			displayAnswer(set1.poll());
		}
		System.out.println(count + "answers");
		System.out.println(different + "different answers");
	}

	public void dfsSearch() {
		HashMap<String, Boolean> answerSetNotEqual = new HashMap<>();
		time = System.currentTimeMillis();
		dfsAlgorithm(s[6], answerSetNotEqual);
		System.out.println("End: " + (System.currentTimeMillis() - time) / 1000);
	}

	public void dfsAlgorithm(Shape shape, HashMap<String, Boolean> answerSetNotEqual) {
		if (shape == null) {
			return;
		}
		HashMap<String, LinkedList<Shape>> angleSetMapLocal = new HashMap<>();
		if (shape.shapesSet.size() == 7) {
			String tem = getAngleList(shape);
			if (!answerSetNotEqual.containsKey(tem)) {
				answerSetNotEqual.put(tem, null);
				displayAnswer(shape);
			}
			return;
		}
		for (int j = 0; j < 6; j++) {
			if (!shape.contains(s[j])) {
				for (int b = 0; b < shape.size(); b++) {
					for (int n = 0; n < Connector.symCheck(s[j]); n++) {
						for (int k = 0; k < 2; k++) {
							Shape newShape;
							newShape = Connector.connect(shape, s[j], b, n, k == 0);
							if (newShape == null) {
								continue;
							}
							newShape.skip = shape.skip;
							newShape = Connector.delete4(newShape);
							int acuracy = 4;
							if (newShape.points.size() < 3) {
								continue;
							}
							if (j == 1) {
								if (newShape.contains(s[5]) && newShape.points.size() > 17 - (acuracy * 2 + 2)) {
									continue;
								}
								if (!newShape.contains(s[5]) && newShape.points.size() > 18 - (acuracy * 2 + 2)) {
									continue;
								}
							}
							if (j == 2) {
								if (newShape.contains(s[5]) && newShape.points.size() > 14 - (acuracy * 2)) {
									continue;
								}
								if (!newShape.contains(s[5]) && newShape.points.size() > 15 - (acuracy * 2)) {
									continue;
								}
							}
							if (j == 3) {
								if (newShape.contains(s[5]) && newShape.points.size() > 11 - acuracy) {
									continue;
								}
								if (!newShape.contains(s[5]) && newShape.points.size() > 12 - acuracy) {
									continue;
								}
							}
							if (j == 4) {
								if (newShape.contains(s[5]) && newShape.points.size() > 8 - (acuracy / 2)) {
									continue;
								}
								if (!newShape.contains(s[5]) && newShape.points.size() > 9 - (acuracy / 2)) {
									continue;
								}
							}
							if (j == 5) {
								if (newShape.points.size() != 5) {
									continue;
								}
							}
							String angleSetTem = getAngleList(newShape);
							if (!angleSetMapLocal.containsKey(angleSetTem)) {// angle list is not same
								LinkedList<Shape> tem = new LinkedList<>();
								tem.add(newShape);
								angleSetMapLocal.put(angleSetTem, tem);
								dfsAlgorithm(newShape, answerSetNotEqual);
							} else {
								LinkedList<Shape> tem = angleSetMapLocal.get(angleSetTem);// tem:The shape that has same angleSet
								int len = tem.size();
								boolean flag = false;
								for (int h = 0; h < len; h++) {
									if (elementsEquals(newShape, tem.get(h))) {
										if (newShape.skip == tem.get(h).skip) {
											flag = true;
										}
										if ((int) newShape.skip != (int) tem.get(h).skip) {
											flag = true;
										}
										continue;
									}
								}
								if (flag) {
									continue;
								}
								if (len == 1) {
									newShape.skip = index;
									tem.get(0).skip = index;
									index++;
								}
								if (len > 1) {
									double indexTem = index;
									while (indexTem > 1) {
										indexTem *= 0.1;
									}
									newShape.skip = tem.get(0).skip + indexTem;
								}
								tem.add(newShape);
								angleSetMapLocal.put(angleSetTem, tem);
								dfsAlgorithm(newShape, answerSetNotEqual);
							}
						}
					}
				}
			}
		}
	}

	int index = 0;

	public void aStarSearch() {
		time = System.currentTimeMillis();
		aStarAlgorithm(s[6]);
		System.out.println("End: " + (System.currentTimeMillis() - time) / 1000 + "s");
	}

	private void aStarAlgorithm(Shape shape) {
		if (shape.shapesSet.size() == 7) {
			String tem = getAngleList(shape);
			// System.out.println(checkAngle(shape));
			if (!answerSetNotEqual.containsKey(tem)) {
				answerSetNotEqual.put(tem, null);
				displayAnswer(shape);
			}
			return;
		}
		LinkedList<LinkedList<Shape>> costSet = new LinkedList<LinkedList<Shape>>();
		LinkedList<Shape> set = new LinkedList<Shape>();
		for (int i = 0; i < 5; i++) {// up to decagon
			costSet.add(new LinkedList<Shape>());
		}
		for (int i = 0; i < 6; i++) {// 5,6,7,8,>9
			if (!shape.contains(s[i])) {
				set = Connector.connectAll(shape, s[i]);
				HashMap<String, LinkedList<Shape>> angleSetMap = new HashMap<>();
				int acuracy = 4;
				while (!set.isEmpty()) {
					Shape shape1 = set.poll();
					if (shape1 == null) {
						continue;
					}
					if (shape1.points.size() < 3) {
						continue;
					}
					if (i == 1) {
						if (shape1.contains(s[5]) && shape1.points.size() > 17 - (acuracy * 3)) {
							continue;
						}
						if (!shape1.contains(s[5]) && shape1.points.size() > 18 - (acuracy * 3) - 1) {
							continue;
						}
					}
					if (i == 2) {
						if (shape1.contains(s[5]) && shape1.points.size() > 14 - (acuracy * 2) - 1) {
							continue;
						}
						if (!shape1.contains(s[5]) && shape1.points.size() > 15 - (acuracy * 2) - 1) {
							continue;
						}
					}
					if (i == 3) {
						if (shape1.contains(s[5]) && shape1.points.size() > 11 - acuracy) {
							continue;
						}
						if (!shape1.contains(s[5]) && shape1.points.size() > 12 - acuracy) {
							continue;
						}
					}
					if (i == 4) {
						if (shape1.contains(s[5]) && shape1.points.size() > 8 - (acuracy / 2)) {
							continue;
						}
						if (!shape1.contains(s[5]) && shape1.points.size() > 9 - (acuracy / 2)) {
							continue;
						}
					}
					if (i == 5) {
						if (shape1.points.size() != 5) {
							continue;
						}
					}
					String angleSetTem = getAngleList(shape1);
					if (!angleSetMap.containsKey(angleSetTem)) {// angle list is not same
						LinkedList<Shape> tem = new LinkedList<>();
						tem.add(shape1);
						angleSetMap.put(angleSetTem, tem);
						int cost = Math.abs(5 - shape1.points.size());
						if (cost > 3) {
							costSet.get(4).add(shape1);
						} else {
							costSet.get(cost).add(shape1);
						}
					} else {
						if (i == 5) {
							continue;
						}
						LinkedList<Shape> tem = angleSetMap.get(angleSetTem);// tem:The shape that has same angleSet
						int len = tem.size();
						boolean flag = false;
						for (int j = 0; j < len; j++) {
							if (elementsEquals(shape1, tem.get(j))) {
								if (shape1.skip == tem.get(j).skip) {
									flag = true;
									continue;
								}
								if ((int) shape1.skip != (int) tem.get(j).skip) {
									flag = true;
								}
							}
						}
						if (flag) {
							continue;
						}
						if (len == 1) {
							shape1.skip = index;
							tem.get(0).skip = index;
							index++;
						}
						if (len > 1) {
							double indexTem = index;
							while (indexTem > 1) {
								indexTem *= 0.1;
							}
							shape1.skip = tem.get(0).skip + indexTem;
						}
						tem.add(shape1);
						angleSetMap.put(angleSetTem, tem);
						int cost = Math.abs(5 - shape1.points.size());
						if (cost > 3) {
							costSet.get(4).add(shape1);
						} else {
							costSet.get(cost).add(shape1);
						}
					}
				}

				// while (!set1.isEmpty()) {
				// Shape shapeTem = set1.poll();
				// int cost = Math.abs(5 - shapeTem.points.size());
				// if (cost > 5) {
				// costSet.get(6).add(shapeTem);
				// } else {
				// costSet.get(cost).add(shapeTem);
				// }
				// }
			}
		}
		for (int i = 0; i < 5; i++) {// connect the shape from the lowest cost
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
//		int len = shape.points.size();
//		int[] angleList = new int[len];
//		for (int i = 0; i < len; i++) {
//			for (int j = i; j < len + i; j++) {
//				angleList[i] += ((shape.getAngel(j % len)) * Math.pow(10, len - j + i) + shape.getLength(j));
//			}
//		}
//		int[] arr = new int[angleList.length];
//		mergeSort(angleList, 0, angleList.length-1, arr);
//
//		for (int i = 0; i < len; i++) {
//			int tem = angleList[i];
//			String str = Integer.toString(tem);
//			answer = answer + str;
//		}
//		return answer;
//	}

	public String getAngleList(Shape shape) {
		int len = shape.points.size();
		int[] angleList = new int[len];
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < len; i++) {
			for (int j = i; j < len + i; j++) {
				angleList[i] += ((shape.getAngel(j % len)) * Math.pow(10, len - j + i) + shape.getLength(j));
			}
			if (angleList[i] < min) {
				min = angleList[i];
			}
		}
		String answer = Integer.toString(min);
		return answer;
	}

	public static void mergeSort(int[] arr, int left, int right, int[] temp) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSort(arr, left, mid, temp);
			mergeSort(arr, mid + 1, right, temp);
			merge(arr, left, mid, right, temp);
		}

	}

	public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
		int i = left;
		int j = mid + 1;
		int t = 0;
		while (i <= mid && j <= right) {
			if (arr[i] <= arr[j]) {
				temp[t] = arr[i];
				t++;
				i++;
			} else {
				temp[t] = arr[j];
				t++;
				j++;
			}
		}
		while (i <= mid) {
			temp[t] = arr[i];
			t++;
			i++;
		}

		while (j <= right) {
			temp[t] = arr[j];
			t++;
			j++;
		}

		t = 0;
		int tempLeft = left;

		while (tempLeft <= right) {
			arr[tempLeft] = temp[t];
			t++;
			tempLeft++;
		}
	}
}
