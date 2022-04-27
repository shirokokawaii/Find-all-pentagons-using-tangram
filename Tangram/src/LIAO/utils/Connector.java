package LIAO.utils;

import LIAO.Point;
import LIAO.Shape;

import java.util.LinkedList;

public class Connector {

    public static Shape connect(Shape shapeA, Shape shapeB, int pointA, int pointB, Boolean direction) {
        Shape result = new Shape();
        //for temporary use:
        if (direction && shapeA.getLength(pointA) == shapeB.getLength(pointB)) {

        } else
            return null;
        return null;
    }
    public static LinkedList<Shape> connectAll(Shape shapeA, Shape shapeB) {
        LinkedList<Shape> shapes = new LinkedList<>();

        for (int i = 0; i < shapeA.size(); i++) {
            for (int j = 0; j < shapeB.size(); j++) {
                for (int k = 0; k <= 1; k++){
                    boolean b = k == 1 ? true : false;
                    Shape newShape = connect(shapeA, shapeB, i, j, b);
//                    for (Shape s : shapes) {
//                        if (!IsSame.IsSameAll(newShape, shapes)) {
//                            shapes.add(newShape);
//                        }
//                    }
                    shapes.add(newShape);
                }
            }
        }
        return shapes;
    }
}
