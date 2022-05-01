package LIAO;

import LIAO.entity.CircleList;
import LIAO.entity.Point;
import com.alibaba.fastjson2.annotation.JSONField;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class Shape {
    @JSONField(name = "POINTS")
    CircleList<Point> points = new CircleList<>();

    LinkedList<Shape> shapesSet = new LinkedList<Shape>();
    LinkedList<Character> pointOrder = new LinkedList<Character>();
    LinkedList<Shape> shapeList = new LinkedList<>();
    boolean skip = false;

    LinkedList<Shape> debugShapeSet = new LinkedList<>();
    LinkedList<Integer> debugPointOrderA = new LinkedList<>();
    LinkedList<Integer> debugPointOrderB = new LinkedList<>();
    LinkedList<Boolean> debugDirection = new LinkedList<>();

    public Shape() {
    }


    public ArrayList<Point> getPoints() {
        return (ArrayList<Point>) points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = (CircleList<Point>) points;
    }

    private void initOwnershipList(int number){
        for(int i=0;i<this.size();i++){
            //this.OwnershipList.put(this.getName(i), number);
        }
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
