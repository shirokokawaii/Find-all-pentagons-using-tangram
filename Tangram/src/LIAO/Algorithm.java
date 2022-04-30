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
	
	public ArrayList<Shape> bfsSearch() {
		Queue<Shape> set1 = new LinkedList<Shape>();
		set1.offer(s[6]);
		set1.offer(s[7]);
		for(int i=0;i<6;i++) {
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
				if(shape == null || shape.points.size()>=9){
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
	
	// private boolean isAngleSetEquals(Shape shape1, Shape shape2){
	// 	int len = shape1.point.size();
	// 	int first = shape1.point.get(0).getAngle();
	// 	int index = -1;
	// 	for(int i=0;i<len;i++){
	// 		if(shape2.point.get(i).getAngle()==first){
	// 			index = i;
	// 		}
	// 	}
	// 	if(index == -1){
	// 		return false;
	// 	}
	// 	for( int i=1;i<5;i++){
	// 		index++;
	// 		if(index > len){
	// 			index = 0;
	// 		}
	// 		if(shape1.point.get(i).getAngle() != shape2.point.get(index).getAngle()){
	// 			return false;
	// 		}
	// 	}
	// 	return true;
	// }

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

	// private Shape connect(Shape shape1, Shape shape2, Point originalPoint, Point laterPoint, int direction) {//connect shape1 and shape2 with specified edge
	// 	Shape result = new Shape();
	// 	// calculate the results and store it into set.*****
	// 	return Connector.connect(shape1, shape2, 0, 0, true);
	// }
	
	private Queue<String> getAllEdgePossibility(Shape shape1, Shape shape2) {
		Queue<String> edgeSet = new LinkedList<String>();
		//here calculate all the possible edge combination and store them into edgeSet.*****
		return edgeSet;
	}
	

	// private Queue<Shape> connectAll(Shape order, Shape shape) {
	// 	Queue<Shape> resultSet = new LinkedList<Shape>();
	// 	//calculate the results and store them into set.*****
	// 	return Connector.connectAll(order, shape);
	// }
}

