package LIAO.utils;

import LIAO.Point;
import LIAO.Shape;

import java.util.LinkedList;

public class Connector {

    public static Shape connect(Shape shapeA, Shape shapeB, int pointA, int pointB, Boolean direction) {
        Shape result = new Shape();
        String flag = "";
        //for temporary use:
        //System.out.println(shapeA.getLength(pointA) + "+" + shapeB.getLength(pointB + shapeB.size() - 1));
        double abs = Math.abs(shapeA.getLength(pointA) - shapeB.getLength(pointB + shapeB.size() - 1));
        if (direction && (abs <= 0.0000001d)) {
            for (int i = pointA; i < shapeA.size() + pointA; i++) {

                    //at the pointA
                if (i == pointA) {
                    int added = shapeA.getAngel(pointA) + shapeB.getAngel(pointB);
                    if (added > 8) {
                        flag = "failed";
                        break;
                    } else {
                        if (added == 4) {
                            flag = "out4";
                            result.addPoint(new Point(shapeA.getAngel(pointA + shapeA.size() - 1), shapeA.getLength(pointA + shapeA.size() - 1) + shapeB.getLength(pointB)));
                        } else if (added == 8){
                            flag = "deleted";
                        }
                        //flag = "connect";
                        for (int j = pointB; j < shapeB.size() + pointB; j++) {
                            // add the pointB
                            if (j == pointB)
                                if(flag.equals("out4"))
                                    j++;
                            if (j == pointB) {
                                result.addPoint(new Point(added, shapeB.getLength(j)));
                            // add the last point
                            } else if (j == shapeB.size() + pointB - 1 ) {
                                int addedEnd = shapeB.getAngel(j) + shapeA.getAngel(i + 1);
                                if (addedEnd > 8) {
                                    flag = "failed";
                                    break;
                                } else if (addedEnd == 4 || addedEnd == 8) {
                                    flag = "deleted";
                                } else {
                                    i++;
                                    result.addPoint(new Point(addedEnd, shapeA.getLength(i)));
                                }
                            // other point
                            } else {
                                result.addPoint(shapeB.getPoint(j));
                            }
                        }
                    }
                // add the rest shapeA points
                } else if (flag.equals("out4") && i == shapeA.size() + pointA - 1){
                    break;
                } else {
                    result.addPoint(shapeA.getPoint(i));
                }

            }
        } else
            flag = "failed";

        if (flag.equals("failed") || flag.equals("deleted"))
            return null;
        else
            System.out.println("pointA:"+pointA+"  pointB:"+pointB);
            return result;
    }
    public static LinkedList<Shape> connectAll(Shape shapeA, Shape shapeB) {
        LinkedList<Shape> shapes = new LinkedList<>();

        for (int i = 0; i < shapeA.size(); i++) {
            for (int j = 0; j < shapeB.size(); j++) {
                for (int k = 0; k <= 1; k++){
                    boolean b = k == 1 ? true : false;
                    Shape newShape = connect(shapeA, shapeB, i, j, b);
//                    if (newShape != null)
                        shapes.add(newShape);
                }
            }
        }
        return shapes;
    }
}
