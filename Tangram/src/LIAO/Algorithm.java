package LIAO;

import static LIAO.entity.Tangram.S6;
import static LIAO.entity.Tangram.S7;

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

	Long time = System.currentTimeMillis();
	Shape[] s = new Shape[8];
	int answerIndex = 0;
	LinkedList<LinkedList<Shape>> answerSet = new LinkedList<>();
	HashMap<String, Integer> answerSetNotEqual = new HashMap<>();
	HashMap<String, Integer> hs = new HashMap<>();
	HashMap<Integer, Integer> hasSameMap = new HashMap<>();
	int count = 0;
	int different = 0;

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
				answerSet.add(tem);
				answerIndex++;
			} else {
				int tem = hs.get(angleList);
				answerSet.get(tem).add(shape);
			}
			ArrayList<Integer> angleSet = checkAngle(shape);
			count++;
			long timeNow = System.currentTimeMillis();
			System.out.println((count - 1) + ":" + angleSet + " " + (timeNow - time) / 1000 + "s");
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

	public void bfsSearch() {
		time = System.currentTimeMillis();
		bfsAlgorithm(S6);
		System.out.println("50%");
		bfsAlgorithm(S7);
		System.out.println("End:" + ((System.currentTimeMillis() - time) / 1000) + "s");
	}

	public void bfsAlgorithm(Shape shapeIn) {
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
		time = System.currentTimeMillis();
		//dfsAlgorithm(s[7], answerSetNotEqual);
		//System.out.println("50%");
		dfsAlgorithm(s[6], answerSetNotEqual);
		System.out.println("End:" + (System.currentTimeMillis() - time) / 1000);
	}

	public void dfsAlgorithm(Shape shape, HashMap<String, Integer> answerSetNotEqual) {
		if (shape == null) {
			return;
		}
		HashMap<String, LinkedList<Shape>> angleSetMapLocal = new HashMap<>();
		if (shape.shapesSet.size() == 7) {
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
					for (int n = 0; n < Connector.symCheck(s[j]); n++) {
						for (int k = 0; k < 2; k++) {
							Shape newShape = null;
							newShape = Connector.connect(shape, s[j], b, n, k == 0);
							if (newShape != null) {
								newShape.skip = shape.skip;
								newShape = Connector.delete4(newShape);
								int acuracy = 2;
								if(newShape.points.size() <3){
									continue;
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
									if (newShape.contains(s[5]) && newShape.points.size() > 8) {
										continue;
									}
									if (!newShape.contains(s[5]) && newShape.points.size() > 9) {
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
									LinkedList<Shape> tem = angleSetMapLocal.get(angleSetTem);// tem:The shape that has
																								// same angleSet
									int len = tem.size();
									boolean flag = false;
									for (int h = 0; h < len; h++) {
										if (elementsEquals(newShape, tem.get(h))) {
											if (newShape.skip == tem.get(h).skip) {
												flag = true;
												continue;
											}
											if ((int) newShape.skip != (int) tem.get(h).skip) {
												flag = true;
											}
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
	}

	int index = 0;

	public void aStarSearch() {
		time = System.currentTimeMillis();
		aStarAlgorithm(s[6]);
		System.out.println("End:" + (System.currentTimeMillis() - time)/1000 +"s");
	}



	private void aStarAlgorithm(Shape shape) {
		if (shape.shapesSet.size() == 7) {
			String tem = getAngleList(shape);
			System.out.println(checkAngle(shape));
			if (!answerSetNotEqual.containsKey(tem)) {
				answerSetNotEqual.put(tem, 0);
				displayAnswer(shape);
			}
			return;
		}
		ArrayList<ArrayList<Shape>> costSet = new ArrayList<ArrayList<Shape>>();
		LinkedList<Shape> set = new LinkedList<Shape>();
		for (int i = 0; i < 7; i++) {// up to decagon
			costSet.add(new ArrayList<Shape>());
		}
		for (int i = 0; i < 6; i++) {// 5,6,7,8,9,10,>11
			if (!shape.contains(s[i])) {
				set = Connector.connectAll(shape, s[i]);
				HashMap<String, LinkedList<Shape>> angleSetMap = new HashMap<>();
				while (!set.isEmpty()) {
					Shape shape1 = set.poll();
					if (shape1 == null) {
						continue;
					}
					if(shape1.points.size() <3){
						continue;
					}
					if (i == 3) {
						if (shape1.contains(s[5]) && shape1.points.size() > 11 - 3) {
							continue;
						}
						if (!shape1.contains(s[5]) && shape1.points.size() > 12 - 3) {
							continue;
						}
					}
					if (i == 4) {
						if (shape1.contains(s[5]) && shape1.points.size() > 8) {
							continue;
						}
						if (!shape1.contains(s[5]) && shape1.points.size() > 9) {
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
						if (cost > 5) {
							costSet.get(6).add(shape1);
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
						if (cost > 5) {
							costSet.get(6).add(shape1);
						} else {
							costSet.get(cost).add(shape1);
						}
					}
				}

				// while (!set1.isEmpty()) {
				// 	Shape shapeTem = set1.poll();
				// 	int cost = Math.abs(5 - shapeTem.points.size());
				// 	if (cost > 5) {
				// 		costSet.get(6).add(shapeTem);
				// 	} else {
				// 		costSet.get(cost).add(shapeTem);
				// 	}
				// }
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

	public boolean hasSame(Shape shape) {
		int angle = 0;
		for (int j = 4; j >= 0; j--) {
			angle += ((shape.points.get(j).getAngle()) * Math.pow(10, j))+ shape.getLength(j);
		}
		int[] angleList = new int[5];
		if (hasSameMap.containsKey(angle)) {
			return true;
		} else {
			for (int i = 0; i < 5; i++) {
				for (int j = i; j < 5 + i; j++) {
					angleList[i] += ((shape.points.get(j % 5).getAngle()) * Math.pow(10, 5 - j + i))+ shape.getLength(j);
				}
			}
			for (int i = 0; i < 5; i++) {
				hasSameMap.put(angleList[i], 0);
			}
			return false;
		}
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
    public static void main(String[] args) {

//        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
//        int[] temp = new int[arr.length];
//        mergeSort(arr, 0, 7, temp);
//        System.out.println(Arrays.toString(arr));

        int[] arr = new int[80000];
        int[] temp = new int[arr.length];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random()*80000);
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date = new Date();
        String dateString = format.format(date);
        System.out.println("排序前的时间：" + dateString);

        mergeSort(arr, 0, 79999, temp);

        Date date1 = new Date();
        String dateString1 = format.format(date1);
        System.out.println("排序前的时间：" + dateString1);
    }

    //分治排序
    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        //递归，将数组逐步分成单个数，再将逐步其合并排序
        //在数组内部将数组分成单个数的数组
        if (left < right){
            int mid = (left + right) / 2;//中间位置的索引

            //向左递归分解
            mergeSort(arr, left, mid, temp);
            //向右递归分解
            mergeSort(arr, mid + 1, right, temp);

            //每次分完过后，将其合并排序
            merge(arr, left, mid, right, temp);
        }

    }


    /**
     * 合并方法
     * @param arr 排序数组
     * @param left 左边数组的起始索引
     * @param mid  左边数组的结束索引
     * @param right 右边数组的结束索引
     * @param temp 辅助数组 用来储存合并的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp){
        int i = left;//左边数组的起始下标
        int j = mid + 1;//右边数组的起始索引
        int t = 0;//temp数组的下标

        //1、比较两个数组中值，将较小的数放入temp数组
        while (i <= mid && j <= right){
            if (arr[i] <= arr[j]){
                temp[t] = arr[i];
                t++;
                i++;
            }else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        //2、将剩余数依次添加到数组中
        while (i <= mid){
            temp[t] = arr[i];
            t++;
            i++;
        }

        while (j <= right){
            temp[t] = arr[j];
            t++;
            j++;
        }

        //3、将合并好的数组复制回原数组中
        t = 0;//从temp数组的开头开始复制
        int tempLeft = left;//需要复制回的原数组的下标

        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }




}
