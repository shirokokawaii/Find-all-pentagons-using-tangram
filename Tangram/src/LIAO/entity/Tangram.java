package LIAO.entity;


import LIAO.Shape;
import LIAO.entity.CircleList;
import LIAO.entity.Point;

public class Tangram {
    public static final Point s = new Point(2, 1d);

    public static final CircleList<Point> p0 = new CircleList<Point>(){
        {
            add(new Point(1, 2d));
            add(new Point(2, 2d));
            add(new Point(1, 2*Math.sqrt(2)));
        }
    };

    public static final CircleList<Point> p2 = new CircleList<Point>(){
        {
            add(new Point(1, 2d));
            add(new Point(1, Math.sqrt(2)));
            add(new Point(2, Math.sqrt(2)));
        }
    };

    public static final CircleList<Point> p3 = new CircleList<Point>(){
        {
            add(new Point(1, 1d));
            add(new Point(2, 1d));
            add(new Point(1, Math.sqrt(2)));
        }
    };

    public static final CircleList<Point> p5 = new CircleList<Point>(){
        {
            add(s);
            add(s);
            add(s);
            add(s);
        }
    };

    public static final CircleList<Point> p6 = new CircleList<Point>(){
        {
            add(new Point(1, 1d));
            add(new Point(3, Math.sqrt(2)));
            add(new Point(1, 1d));
            add(new Point(3, Math.sqrt(2)));
        }
    };

    public static final CircleList<Point> p7 = new CircleList<Point>(){
        {
            add(new Point(3, 1d));
            add(new Point(1, Math.sqrt(2)));
            add(new Point(3, 1d));
            add(new Point(1, Math.sqrt(2)));
        }
    };

    public static final Shape S0 = new Shape(p0);
    public static final Shape S1 = new Shape(p0);
    public static final Shape S2 = new Shape(p2);
    public static final Shape S3 = new Shape(p3);
    public static final Shape S4 = new Shape(p3);
    public static final Shape S5 = new Shape(p5);
    public static final Shape S6 = new Shape(p6);
    public static final Shape S7 = new Shape(p7);

}
