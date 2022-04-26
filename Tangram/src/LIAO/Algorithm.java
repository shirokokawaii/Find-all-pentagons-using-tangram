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
		if(shape.point.size()==5) {//This shape is a pentagon, draw and display it
			//here needs to draw and display the answer shape
		}
		else {//This shape is not a pentagon, only output its angleSet on the terminal
			int len = shape.point.size();
			int[] angleSet = new int[len];
			for(int i=0;i<len;i++) {
				angleSet[i] = shape.point.get(i).getAngle();
			}
			System.out.println(angleSet);
		}
	}

	
	public void bfsSearch() {
		Queue<Shape> set1 = new LinkedList<Shape>();
		set1.offer(s[6]);
		set1.offer(s[7]);
		for(int i=0;i<6;i++) {
			System.out.println("Calculating "+i+"st shape");
			Queue<Shape> set2 = new LinkedList<Shape>();
			while(!set1.isEmpty()) {
				Shape order = new Shape();
				order = set1.poll();
				for(int j=0;j<6;j++) {
					if(!order.contains(s[j])) {
						connectAll(order,s[j],set2);
					}
				}
			}
			set1 = set2;
		}
		while(!set1.isEmpty()) {
			displayAnswer(set1.poll());
		}
	}

	public void dfsSearch() {
		dfsAlgorithm(s[6]);
		dfsAlgorithm(s[7]);
	}
	private void dfsAlgorithm(Shape shape) {
		if(shape.shapesSet.size()==7) {
			displayAnswer(shape);
			return;
		}
		for(int j=0;j<6;j++) {
			if(!shape.contains(s[j])) {
				Queue<String> edgeSet = getAllEdgePossibility(shape, s[j]);
				while(!edgeSet.isEmpty()) {
					String edge = edgeSet.poll();
					Shape result = connect(shape,s[j],edge);
					dfsAlgorithm(result);
				}
				
			}
		}
	}
	
	private Shape connect(Shape shape1, Shape shape2, String edge) {//connect shape1 and shape2 with specified edge
		Shape result = new Shape();
		// calculate the results and store it into set.
		return result;
		
	}
	
	private Queue<String> getAllEdgePossibility(Shape shape1, Shape shape2) {
		Queue<String> edgeSet = new LinkedList<String>();
		//here calculate all the possible edge combination and store them into edgeSet.
		return edgeSet;
	}
	
	private void connectAll(Shape order, Shape shape, Queue<Shape> set) {
		//calculate the results and store them into set.
		
	}
}

class Shape {
	ArrayList<Point> point;
	Queue<Shape> shapesSet = new LinkedList<Shape>();
	Queue<String> orderSet = new LinkedList<String>();
	
	public boolean contains(Shape shape) {
		if(shapesSet.contains(shape)) {
			return true;
		}
		return false;
	}
	public void add(Shape shape, String edge) {
		shapesSet.add(shape);
		orderSet.add(edge);
	}
}

class Point {
	int angle;
	double length;
	public int getAngle(){
		return angle;
	}
	public double getLength() {
		return length;
	}
}