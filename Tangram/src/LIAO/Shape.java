package LIAO;

import java.util.LinkedList;
import java.util.Queue;

public class Shape {
    CircleList<Point> points;
    Queue<Shape> shapesSet = new LinkedList();
    Queue<String> orderSet = new LinkedList();

    public Shape() {
    }

    public Shape(CircleList<Point> point) {
        this.points = point;
    }

    public int size() {
        return points.size();
    }

    public Point getPoint(int n) {
        return points.get(n);
    }

    public void addPoint(Point point) {
        this.points.add(point);
    }

    public Double getLength(int n) {
        return points.get(n).getLength();
    }

    public boolean contains(Shape shape) {
        return this.shapesSet.contains(shape);
    }

    public void add(Shape shape, String edge) {
        this.shapesSet.add(shape);
        this.orderSet.add(edge);
    }

}
