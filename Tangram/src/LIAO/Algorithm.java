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
		this.s[6] = s6;//s6 is the base
		this.s[7] = s7;//s7 is another base
	}
	Shape[] s = new Shape[8];
	ArrayList<Shape> answerSet = new ArrayList<Shape>(); 
	int count = 0;

	private void displayAnswer(Shape shape) {
		ArrayList<Integer> angleSet = checkAngle(shape);
		if(shape.points.size()==5) {//This shape is a pentagon, draw and display it
			count ++;
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
	
	public ArrayList<Shape> bfsSearch(Shape shapeIn) {
		LinkedList<Shape> set1 = new LinkedList<Shape>();
		set1.offer(shapeIn);
		for(int i=0;i<6;i++) {
			HashMap<String,Integer> angleSetMap = new HashMap<>();
			HashMap<String,Integer> OwnerShipSetMap = new HashMap<>();
			System.out.println("Adding "+(i+1)+"st shape");
			LinkedList<Shape> set2 = new LinkedList<Shape>();
			Long time1 = System.currentTimeMillis();
			while(!set1.isEmpty()) {
				Shape order = new Shape();
				order = set1.poll();
				for(int j=0;j<6;j++) {
					if(!order.contains(s[j])) {
						set2.addAll(Connector.connectAll(order,s[j]));
					}
				}
			}
			Long time2 = System.currentTimeMillis();
			System.out.println("Connect time:" + (time2-time1) +"ms");
			while(!set2.isEmpty()){
				Shape shape = set2.poll();
				if(shape == null){
					continue;
				}
				if(i ==3 && shape.points.size()>10){
					continue;
				}
				if(i ==4 && shape.points.size()>9){
					continue;
				}
				if(i ==5 && shape.points.size()>5){
					continue;
				}
				String angleSetTem = getAngleList(shape);
				// if(angleSetMap.containsKey(angleSetTem) && !shape.skip){//angle list is same
				// 	String  ownerShipSetTem = getOwnershipList(shape, i);
				// 	if(!OwnerShipSetMap.containsKey(ownerShipSetTem)){//edge list is not same
				// 		angleSetMap.put(angleSetTem, 0);
				// 		OwnerShipSetMap.put(ownerShipSetTem, 0);
				// 		shape.skip = true;
				// 		set1.offer(shape);
				// 		continue;
				// 	}
				// }
				// String  ownerShipSetTem = getOwnershipList(shape, i);
				if(!angleSetMap.containsKey(angleSetTem)){//angle list is not same
					angleSetMap.put(angleSetTem, 0);
					// OwnerShipSetMap.put(ownerShipSetTem, 0);
					set1.offer(shape);
				}
			}
			Long time3 = System.currentTimeMillis();
			System.out.println("Prune time:" + (time3-time2) +"ms");
		}
		while(!set1.isEmpty()) {
			displayAnswer(set1.poll());
		}
		System.out.println(count + " answers");
		return answerSet;
	}

	public void dfsSearch() {
		HashMap<ArrayList<Integer>,Integer> angleSetMap = new HashMap<>();
		dfsAlgorithm(s[6], angleSetMap);
		dfsAlgorithm(s[7], angleSetMap);
	}
	private void dfsAlgorithm(Shape shape,HashMap<ArrayList<Integer>,Integer> angleSetMap) {
		if(shape.shapesSet.size()==7) {
			displayAnswer(shape);
			return;
		}
		for(int j=0;j<6;j++) {
			if(!shape.contains(s[j])) {
				Queue<String> edgeSet = getAllEdgePossibility(shape, s[j]);
				while(!edgeSet.isEmpty()) {
					String edge = edgeSet.poll();
					Shape result = new Shape();
					//here needs to get all the possibility edge and connect shapes with specified edge*****
					if(!angleSetMap.containsKey(getAngleList(result))){
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
		if(shape.shapesSet.size()==7) {
			displayAnswer(shape);
			return;
		}
		ArrayList<ArrayList<Shape>> costSet= new ArrayList<ArrayList<Shape>>();
		Queue<Shape> set = new LinkedList<Shape>();
		for(int i=0;i<7;i++){//up to decagon
			costSet.add(new ArrayList<Shape>());
		}
		for(int i=0;i<6;i++) {//calculate all the cost in a row
			if(!shape.contains(s[i])) {
				set = Connector.connectAll(shape, s[i]);
				while(!set.isEmpty()) {
					Shape shapeTem = set.poll();
					int cost = 5 - shapeTem.points.size();
					if(cost > 5){
						costSet.get(6).add(shapeTem);
					}
					else{
						costSet.get(cost).add(shapeTem);
					}
				}
			}
		}
		for(int i=0;i<7;i++){//connect the shape from the lowest cost
			int len = costSet.get(i).size();
			for(int j=0;j<len;j++){
				Shape shapeTem = costSet.get(i).get(j);
				aStarAlgorithm(shapeTem);
			}
		}
	}

		private String getAngleList(Shape shape){
		ArrayList<Integer> angleSet = new ArrayList<Integer>();
		String answer = new String();
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
			answer += Integer.toString(shape.points.get(index).getAngle());
			index++;
		}

		return answer;
	}

	private String getOwnershipList(Shape shape, int index){
		LinkedList<Integer> OwnershipList = new LinkedList<>();
			Shape shapeTem = shape.shapeList.get(index);
			int number = 0;
			for(int i=0;i<6;i++){
				if(shapeTem.equals(s[i])){
					number = i;
				}
			}
			Shape shapeNow = shape.shapesSet.get(index+1);
			char originalPoint = shapeNow.pointOrder.get(index);
			int startPoint = 0;
			for(int i=0;i<shapeNow.size();i++){
				if(shapeNow.getName(i)==originalPoint){
					startPoint = i;
				}
			}
			int previousNumber = shape.OwnershipList.get(originalPoint);
			Character previousName = ' ';
			boolean flag = false;
			for(int i=0;i<shapeNow.size()+1;i++){
				char name = shapeNow.getName(startPoint);
				if(shape.OwnershipList.containsKey(name)){
					if(flag){
						shape.OwnershipList.put(previousName, number);
						flag = false;
						OwnershipList.removeLast();
						OwnershipList.add(previousNumber);
						continue;
					}
					OwnershipList.add(shape.OwnershipList.get(name));
				}
				else{
					shape.OwnershipList.put(name, number);
					OwnershipList.add(number);
					previousName = name;
					flag = true;
				}
			}
			String result = new String();
			for(int i:OwnershipList){
				result+= Integer.toString(i);
			}
			return result;

	}
	
	private Queue<String> getAllEdgePossibility(Shape shape1, Shape shape2) {
		Queue<String> edgeSet = new LinkedList<String>();
		//here calculate all the possible edge combination and store them into edgeSet.*****
		return edgeSet;
	}
}

