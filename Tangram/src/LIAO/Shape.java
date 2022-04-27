package LIAO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Shape {
    ArrayList<Point> point;
    Queue<Shape> shapesSet = new LinkedList();
    Queue<String> orderSet = new LinkedList();

    Shape() {
    }

    public int size() {
        return point.size();
    }

    public Point getPoint(int n) {
        return point.get(n);
    }

    public boolean contains(Shape shape) {
        return this.shapesSet.contains(shape);
    }

    public void add(Shape shape, String edge) {
        this.shapesSet.add(shape);
        this.orderSet.add(edge);
    }
}
