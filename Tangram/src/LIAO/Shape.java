package LIAO;

import java.util.*;

public class Shape {
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
