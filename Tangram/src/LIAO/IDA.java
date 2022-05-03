package LIAO;

import LIAO.entity.CircleList;
import LIAO.entity.Point;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import static LIAO.entity.Tangram.*;

public class IDA {
    static DecimalFormat df = new DecimalFormat("#0.00");
    public static HashMap<String, String> map = new HashMap<>();


    public static Shape reArrange(Shape shape) {
        CircleList<Point> points = new CircleList<>();
        long angle = count(shape.points);
        points = shape.points.rearrange(findMin(angle), shape.points);
        shape.points = points;
        return shape;
    }

    public static CircleList<Point> reArrangePoints(CircleList<Point> points) {
        long angle = count(points);
        return points.rearrange(findMin(angle), points);
    }

    public static long count(CircleList<Point> points) {
        long result = 0;
        for (int i = 0; i < points.size(); i++){
            result *= 10;
            result += points.get(i).getAngle();
        }
        return result;
    }


    private static int findMin(long n) {
        int length = (int)(Math.log10(n)+1);
        long firstOne;
        int index = 0;
        long ini = 99999999999999999L;
        long min;
        min = ini;
        for (int i = 0; i < length; i++) {
            firstOne = n/(int)Math.pow(10, length-1);
            //System.out.println(firstOne);
            n %= (int)Math.pow(10, length-1);
            n *= 10;
            n += firstOne;
            //System.out.println(n);
            if(n<min) {
                min = n;
                index = i + 1;
            }
        }
        return index;
    }

    public static CircleList<Point> reverse(CircleList<Point> points) {
        CircleList<Point> result = new CircleList<>();
        for (int i = points.size() - 1; i >= 0; i--) {
            result.add(points.get(i));
        }
        return result;
    }


    public static String points2String(CircleList<Point> points) {
        String result = "";
        for (int i = 0; i < points.size(); i++) {
            result += points.get(i).getAngle();
            result += df.format(points.get(i).getLength());
            result += ",";
        }
        return result;
    }

    public static boolean hasSame(Shape shape) {
        String feature = points2String(reArrange(shape).points);
        if(map == null){
            String key = points2String(reArrange(shape).points);
            String value = points2String(reArrangePoints(shape.points));
            map.put(key, value);
            return false;
        }
        if(map.containsKey(feature) || map.containsValue(feature)) {
            return true;
        } else {
            String key = points2String(reArrange(shape).points);
            String value = points2String(reArrangePoints(reverse(shape.points)));
            map.put(key, value);
            return false;
        }
    }

    public static void main(String[] args) {
        Shape shape = Connector.connect(S0, S7, 0, 0, true);
        System.out.println(shape);
        System.out.println(reArrange(shape));
        //System.out.println(count(shape));

        Shape shape2 = Connector.connect(S0, S2, 2, 0, true);
        System.out.println(shape2);
        System.out.println(reArrange(shape2));
        System.out.println(reverse(shape2.points));
        System.out.println("reA1:"+reArrange(shape2));

        Shape rever = new Shape();
        rever.points = reverse(shape2.points);

        System.out.println(hasSame(shape));
        System.out.println(map);
        System.out.println(hasSame(shape2));
        System.out.println(map);
        System.out.println(hasSame(rever));

//        System.out.println("reA1:"+reArrange(rever));
//        System.out.println("string:"+points2String(rever.points));
//        String s = String.valueOf(1.4);
//        s += 34;
//        System.out.println(s);
    }

//    public void dfsSearch() {
//        HashMap<String, Integer> answerSetNotEqual = new HashMap<>();
//        Long time1 = System.currentTimeMillis();
//        dfsAlgorithm(s[6], answerSetNotEqual);
//        System.out.println("50%");
//        dfsAlgorithm(s[7], answerSetNotEqual);
//        Long time2 = System.currentTimeMillis();
//        System.out.println("time" + (time2 - time1));
//    }

    public ArrayList<Shape> dfs() {
        ArrayList<Shape> result = null;

        return result;
    }

    public ArrayList<Shape> dfs(Shape shapeA, Shape shapeB) {
        ArrayList<Shape> result = null;
        return result;
    }

//    public Shape dfs(Shape shape) {
//        if(shape.shapeList.size() == 7) {
//            if(hasSame(shape)){
//                return shape;
//            }
//        }
//
//    }

//    public void dfsAlgorithm(Shape shape, HashMap<String, Integer> answerSetNotEqual) {
//        if(shape == null) {
//            return;
//        }
//        HashMap<String, LinkedList<Shape>> angleSetMapLocal = new HashMap<>();
//        if (shape.shapesSet.size() == 7) {
//            String tem = getAngleList(shape);
//            if (!answerSetNotEqual.containsKey(tem)) {
//                answerSetNotEqual.put(tem, 0);
//                displayAnswer(shape);
//            }
//            return;
//        }
//        for (int j = 0; j < 6; j++) {
//            if (!shape.contains(s[j])) {
//                for (int b = 0; b < shape.size(); b++) {
//                    for (int n = 0; n < symCheck(s[j]); n++) {
//                        for (int k = 0; k < 2; k++) {
//                            Shape newShape = null;
//                            newShape = Connector.connect(shape, s[j], b, n, k == 0);
//                            if (newShape != null) {
//                                newShape.skip = shape.skip;
//                                newShape = check(newShape, j, 0, angleSetMapLocal);
//                                dfsAlgorithm(newShape, answerSetNotEqual);
//                            }
//                        }
//                    }
//                }
//            }



}
