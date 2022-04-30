package LIAO;

import LIAO.entity.CircleList;
import LIAO.entity.Point;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Shape {
    CircleList<Point> points = new CircleList<>();
    Queue<Shape> shapesSet = new LinkedList<Shape>();
    Queue<Character> pointOrder = new LinkedList<Character>();
    Queue<Shape> shapeList = new LinkedList<>();

    public Shape() {
    }

    public Shape(CircleList<Point> point) {
        this.points = point;
    }

    public void addShape(Shape shape) {
        shapesSet.offer(shape);
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

    public int getAngel(int n) {
        return points.get(n).getAngle();
    }

    public char getName(int n){
        return points.get(n).getName();
    }

    public void delete(int i) {
        points.remove(i);
    }

    public boolean contains(Shape shape) {
        return this.shapeList.contains(shape);
    }

    public CircleList<Point> reverse() {
        Collections.reverse(points);
        return points;
    }

    public void add(Shape shape, int pointA, int pointB, Boolean direction) {
        this.shapesSet.add(shape);

    }

    @Override
    public String toString() {
        return "Shape{" +
                "size=" + size() + "\n" +
                "points=" + points +
                "}";
    }


}
