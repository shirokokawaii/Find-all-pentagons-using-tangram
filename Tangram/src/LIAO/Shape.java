package LIAO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Shape {
    public CircleList<Point> point;
    Queue<Shape> shapesSet = new LinkedList();
    Queue<String> orderSet = new LinkedList();

    public Shape() {
    }

    public Shape(CircleList<Point> point) {
        this.point = point;
    }

    public int size() {
        return point.size();
    }

    public Point getPoint(int n) {
        return point.get(n);
    }

    public void addPoint(Point point) {
        this.point.add(point);
    }

    public Double getLength(int n) {
        return point.get(n).getLength();
    }

    public boolean contains(Shape shape) {
        return this.shapesSet.contains(shape);
    }

    public void add(Shape shape, String edge) {
        this.shapesSet.add(shape);
        this.orderSet.add(edge);
    }

}
