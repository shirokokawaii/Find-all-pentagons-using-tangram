package LIAO.entity;


import LIAO.Shape;
import LIAO.entity.CircleList;
import LIAO.entity.Point;

public class TestTang {

    public static final Point A = new Point('A', 1, 2d);
    public static final Point B = new Point('B', 2, 2d);
    public static final Point C = new Point('C', 1, 2*Math.sqrt(2));


    public static final CircleList<Point> p0 = new CircleList<Point>(){
        {
            add(A);
            add(B);
            add(C);
        }
    };

    public static final CircleList<Point> p1 = new CircleList<Point>(){
        {
            add(new Point(1, 2d));
            add(new Point(2, 2d));
            add(new Point(1, 2*Math.sqrt(2)));
        }
    };

    public static final Point D = new Point('D', 1, 2d);
    public static final Point E = new Point('E', 1, Math.sqrt(2));
    public static final Point F = new Point('F', 2, Math.sqrt(2));

    public static final CircleList<Point> p2 = new CircleList<Point>(){
        {
            add(D);
            add(E);
            add(F);
        }
    };

    public static final Point G = new Point('G', 1, 1d);
    public static final Point H = new Point('H', 2, 1d);
    public static final Point I = new Point('I', 1, Math.sqrt(2));

    public static final CircleList<Point> p3 = new CircleList<Point>(){
        {
            add(G);
            add(H);
            add(I);
        }
    };

    public static final CircleList<Point> p4 = new CircleList<Point>(){
        {
            add(new Point(1, 1d));
            add(new Point(2, 1d));
            add(new Point(1, Math.sqrt(2)));
        }
    };

    public static final Point s = new Point('J', 2, 1d);
    public static final Point J = new Point('J', 2, 1d);
    public static final Point K = new Point('K', 2, 1d);
    public static final Point L = new Point('L', 2, 1d);
    public static final Point M = new Point('M', 2, 1d);

    public static final CircleList<Point> p5 = new CircleList<Point>(){
        {
            add(J);
            add(K);
            add(L);
            add(M);
        }
    };

    public static final Point N = new Point('N', 1, 1d);
    public static final Point O = new Point('O', 3, Math.sqrt(2));
    public static final Point P = new Point('P', 1, 1d);
    public static final Point Q = new Point('Q', 3, Math.sqrt(2));

    public static final CircleList<Point> p6 = new CircleList<Point>(){
        {
            add(N);
            add(O);
            add(P);
            add(Q);
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

    public static final CircleList<Point> p8 = new CircleList<Point>(){
        {
            add(new Point(3, 1d));
            add(new Point(1, Math.sqrt(2)));
            add(new Point(3, 1d));
            add(new Point(2, Math.sqrt(2)));
        }
    };

    public static final Shape S0 = new Shape(p0, 'A');
    public static final Shape S1 = new Shape(p0, 'A');
    public static final Shape S2 = new Shape(p2, 'B');
    public static final Shape S3 = new Shape(p3, 'C');
    public static final Shape S4 = new Shape(p3, 'C');
    public static final Shape S5 = new Shape(p5, 'D');
    public static final Shape S6 = new Shape(p6, 'E');
    public static final Shape S7 = new Shape(p7, 'F');
    public static final Shape S8 = new Shape(p8);
}
