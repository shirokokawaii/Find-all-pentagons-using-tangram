package LIAO;

import LIAO.Shape;
import LIAO.entity.*;


import java.util.LinkedList;

import static LIAO.entity.Tangram.*;

public class Connector {
    public static Shape connect(Shape shapeA, Shape shapeB, int A, int B, Boolean d) {
        Shape result = new Shape();
        String flag = "";

        final double THRESHOLD = .0001;

        int checkAngle = shapeA.getAngel(A) + shapeB.getAngel(B);

        if (checkAngle > 8)
            flag = "failed";
        else if (checkAngle == 8)
            flag = "absorb";
        else {
            int firstSize;
            CircleList<Point> checkList;
            if (d) {
                checkList = add(shapeA, shapeB, A, B);
                firstSize = shapeA.size();
            } else {
                checkList = add(shapeB, shapeA, B, A);
                firstSize = shapeB.size();
            }

            double length1 = checkList.get(firstSize - 1).getLength();
            double length2 = checkList.get(checkList.size() - 1).getLength();

            for (int i = 0; i < checkList.size() - 1; i++) {

                if (i == 0) {
                    result.addPoint(new Point(checkAngle, checkList.get(i).getLength()));
                } else if (i == firstSize - 1) {
                    if (Math.abs(length1 - length2) < THRESHOLD) {
                        //System.out.println("\nChecked:");
                        int checkAngleEnd = checkList.get(firstSize - 1).getAngle() + checkList.get(firstSize).getAngle();
                        if (checkAngleEnd >= 8) {
                            flag = "failed";
                        } else {
                            result.addPoint(new Point(checkAngleEnd, checkList.get(firstSize).getLength()));
                        }
                    } else if (length1 > length2) {
                        //System.out.println("\nunfinished:");
                        result.addPoint(new Point(checkList.get(firstSize - 1).getAngle(), Math.abs(length1 - length2)));
                        if (checkList.get(firstSize).getAngle() + 4 >= 8) {
                            flag = "failed";
                        }
                        result.addPoint(new Point(checkList.get(firstSize).getAngle() + 4, checkList.get(firstSize).getLength()));
                    } else if (length1 < length2) {
                        //System.out.println("\nunfinished:");
                        result.addPoint(new Point(checkList.get(firstSize - 1).getAngle() + 4, Math.abs(length1 - length2)));
                        if (checkList.get(firstSize - 1).getAngle() + 4 >= 8) {
                            flag = "failed";
                        }
                        result.addPoint(new Point(checkList.get(firstSize)));
                    }
                    i++;
                } else {
                    Point p = new Point(checkList.get(i));
                    result.addPoint(p);
                }
            }
        }
        if (flag.equals("failed") || flag.equals("absorb")){
            flag = "";
            return null;
        }
        else
            return delete4(result);
    }

    private static CircleList<Point> add(Shape shapeA, Shape shapeB, int A, int B) {
        CircleList<Point> result = new CircleList<>();
        for (int i = A; i < A + shapeA.size(); i++) {
            result.add(shapeA.getPoint(i));
        }
        for (int i = B + 1; i < B + shapeB.size() + 1; i++) {
            result.add(shapeB.getPoint(i));
        }
        return result;
    }

    private static Shape delete4(Shape shape) {
        for (int i = 0; i < shape.size(); i++) {
            if(shape.getAngel(i) == 4) {
                double tempL = shape.getLength(i);
                shape.getPoint(i - 1).addLength(tempL);
                shape.delect(i);
            }
        }
        return shape;
    }

    public static LinkedList<Shape> connectAll(Shape shapeA, Shape shapeB) {
        LinkedList<Shape> shapes = new LinkedList<>();
        for (int i = 0; i < shapeA.size(); i++) {
            for (int j = 0; j < shapeB.size(); j++) {
                for (int k = 0; k < 2; k++){
                    Shape newShape = null;
                    newShape = connect(shapeA, shapeB, i, j, k == 0);
                    shapes.add(newShape);
                    //序号
                    System.out.println("-------"+shapes.size()+"-------");

                    System.out.println(newShape+"\n  i:"+ i+"  j:"+j+"\n"+"direction" + k);


                }
            }
        }
        return shapes;

    }

    public static void main(String[] args) {
        LinkedList<Shape> shape = connectAll(S1, S6);
        LinkedList<Shape> shape2 = connectAll(S1, S3);
        //Shape s = AnotherConnector.connect(S0, S1, 1, 2, false);
        //System.out.println(s);

    }

}
