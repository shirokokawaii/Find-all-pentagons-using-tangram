package LIAO.utils;

import LIAO.Shape;
import LIAO.entity.CircleList;
import LIAO.entity.Point;

import java.util.LinkedList;
import java.util.Stack;

public class NewConnector {
    public static Shape connect(Shape shapeA, Shape shapeB, int pointA, int pointB, Boolean direction) {
        Shape result = new Shape();
        String flag = "";
        Stack<binPoint> checkStack = new Stack<>();

        int angleSum = shapeA.getAngel(pointA) + shapeB.getAngel(pointB);

        if(!direction) {
            flag = "reverse";
        }
        if (angleSum > 8) {
            flag = "Failed";
        } else {
            int i = pointA;
            int j = pointB;
            while (i < shapeA.size() + pointA && j < shapeB.size() + pointB) {


                if((i == pointA && j == pointB)){
                    switch (angleSum){
                        case 8:

                        case 4:

                        default:

                    }

                    i++;
                    j++;

                }




            }
        }

        return null;
    }

    private class binPoint{
        private boolean neg;
        private Point point;
        binPoint(boolean neg, Point point){
            this.neg = neg;
            this.point = point;
        }
    }

    private Shape reverse(Shape shape) {


        return null;
    }




}