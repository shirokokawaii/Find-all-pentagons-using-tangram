package LIAO;

import LIAO.entity.CircleList;
import LIAO.entity.Point;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public class Shape {
    @JSONField(name = "POINTS")
    CircleList<Point> points = new CircleList<>();

    //@JSONField(name = "SHAPESSET")
    LinkedList<Shape> shapesSet = new LinkedList<Shape>();

    //@JSONField(name = "POINTORDER")
    LinkedList<Character> pointOrder = new LinkedList<Character>();


    LinkedList<Shape> shapeList = new LinkedList<>();
    double skip = 0;

    HashSet<String> linkset = new HashSet<>();
    LinkedList<Shape> debugShapeSet = new LinkedList<>();
    LinkedList<Integer> debugPointOrderA = new LinkedList<>();
    LinkedList<Integer> debugPointOrderB = new LinkedList<>();
    LinkedList<Boolean> debugDirection = new LinkedList<>();

    char name;

    public Shape() {
    }


    @JSONField(name = "POINTS")
    public ArrayList<Point> getPoints() {
        return (ArrayList<Point>) points;
    }
    @JSONField(name = "POINTS")
    public void setPoints(ArrayList<Point> points) {
        for (Point p: points){
            this.points.add(p);
        }
    }

    //@JSONField(name = "SHAPESSET")
    @JSONField(serialize = false)
    public LinkedList<Shape> getShapesSet() {return shapesSet;}
    //@JSONField(name = "SHAPESSET")
    @JSONField(serialize = false)
    public void setShapesSet(LinkedList<Shape> shapesSet){this.shapesSet = shapesSet;}



    //@JSONField(name = "POINTORDER")
    public LinkedList<Character> getPointOrder(){return pointOrder;}
    //@JSONField(name = "POINTORDER")
    public void setPointOrder(LinkedList<Character> pointOrder){this.pointOrder = pointOrder;}

    public Shape(CircleList<Point> point) {
        this.points = point;
    }

    public Shape(CircleList<Point> point, char name) {
        this.points = point;
        this.name = name;
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

    public char getShapeName(){return name;}

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
