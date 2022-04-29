package LIAO.utils;

import LIAO.entity.Point;
import LIAO.Shape;

import java.util.LinkedList;

public class Connector {

    public static Shape connect(Shape shape, Shape shapeB, int point, int pointB, Boolean direction) {
        Shape result = new Shape();
        Shape shapeA = (Shape) shape.clone();
        int pointA = point;
        String flag = "";
        //for temporary use:
        //System.out.println(shapeA.getLength(pointA) + "+" + shapeB.getLength(pointB + shapeB.size() - 1));
        if(!direction) {
            shapeA.reverse();
            pointA = shape.size() - point - 1;
        }

        double abs = Math.abs(shapeA.getLength(pointA) - shapeB.getLength(pointB + shapeB.size() - 1));
        if (abs <= 0.0000001d) {
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
        if (!direction){
            result.reverse();
            shapeA.reverse();
        }
        System.out.println("pointA:"+pointA+"  pointB:"+pointB+"  direction:"+direction);
            return result;
    }

    private static int symDectect(Shape shape) {
        int result = 2;
        if (shape.size() == 4) {
            result = 1;
        }
        return shape.size();
    }
    public static LinkedList<Shape> connectAll(Shape shapeA, Shape shapeB) {
        LinkedList<Shape> shapes = new LinkedList<>();
        boolean b = true;
        for (int i = 0; i < shapeA.size(); i++) {
            for (int j = 0; j < symDectect(shapeB); j++) {
                for (int k = 0; k <= 1; k++){
                    Shape newShape = null;
                    if(k == 0){
                        newShape = connect(shapeA, shapeB, i, j, true);
                    }
                    shapes.add(newShape);
                    System.out.println(shapes.size());
                    System.out.println(newShape+"\n  i:"+ i+"  j:"+j+"\n"+"direction");
                    //System.out.println(shapeA);
                    //System.out.println(shapeB);
                }
            }
        }
        return shapes;
    }

    public static LinkedList<Shape> connectAllRe(Shape shapeA, Shape shapeB) {
        LinkedList<Shape> shapes = new LinkedList<>();

        for (int i = 0; i < shapeA.size(); i++) {
            for (int j = 0; j < shapeB.size(); j++) {
                for (int k = 0; k <= 1; k++){
                    boolean b = k == 0;
                    Shape newShape = connect(shapeA, shapeB, i, j, b);
                    shapes.add(newShape);
                    System.out.println(shapes.size());
                    System.out.println(newShape+"\n  i:"+ i+"  j:"+j+"\n"+"direction"+b);
                    System.out.println(shapeA);
                    System.out.println(shapeB);




                }
            }
        }

        return shapes;
    }
}
