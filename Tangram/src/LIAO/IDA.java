package LIAO;

import LIAO.entity.CircleList;
import LIAO.entity.Point;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.*;

import static LIAO.entity.Tangram.*;

public class IDA {
    static Long startTime = System.currentTimeMillis();

    static DecimalFormat df = new DecimalFormat("#0.00");
    public static HashMap<String, String> answerMap = new HashMap<>();

    public static HashMap<String, String> relationMap = new HashMap<>();
    //public static MultiValueMap<String, String> containMap = new LinkedMultiValueMap<>();

    public static HashSet<String> level1List = new HashSet<>();

    public static Shape[] S = new Shape[]{S0, S1, S2, S3, S4, S5};
    public static ArrayList<Shape> answer = new ArrayList<>();
    static int DETH = 20;


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
        long min = 99999999999999999L;
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
            //result.add(new Point(points.get(i).getAngle(), points.get(points.size()-1-i).getLength()));;
        }
        return result;
    }

    public static CircleList<Point> putLength(CircleList<Point> points) {
        CircleList<Point> result = new CircleList<>();
        for (int i = 0; i < points.size(); i++ ) {
            result.add(new Point(points.get(i).getAngle(), points.get(i+1).getLength()));
        }
        return result;
    }

    public static String points2String(CircleList<Point> points) {
        String result = new String();
        for (int i = 0; i < points.size(); i++) {
            result += points.get(i).getAngle();
            result += df.format(points.get(i).getLength());
            result += ",";
        }
        return result;
    }

    public static boolean hasSame(Shape shape) {
        String feature = points2String(reArrange(shape).points);
        //System.out.println(feature);
        if(answerMap == null){
            String key = feature;
            String value = points2String(putLength(reArrangePoints(reverse(shape.points))));
            //System.out.println("VAlue:"+value);
            answerMap.put(key, value);
            return false;
        }
        if(answerMap.containsKey(feature) || answerMap.containsValue(feature)) {
//            if (map.containsKey(feature))
//                System.out.println("KEY");
//            else
//                System.out.println("VALUE");
            return true;
        } else {
            String key = feature;
            String value = points2String(putLength(reArrangePoints(reverse(shape.points))));
            //System.out.println("VAlue:"+value);
            answerMap.put(key, value);
            return false;
        }
    }

    public static boolean isSame(Shape shape) {
        String feature = points2String(reArrange(shape).points);
        if(level1List == null||(!level1List.contains(feature))){
            level1List.add(feature);
            return false;
        } else {
            return true;
        }
    }

    public static boolean containSame(Shape shape) {
        String contain = getContain(shape);
        String feature = points2String(reArrange(shape).points);
//        if (containMap == null){
//            containMap.add(contain, feature);
//            return false;
//        } else if ((!containMap.containsKey(contain))||(containMap.containsKey(contain)&&(!containMap.get(contain).contains(feature)))) {
//            containMap.add(contain, feature);
//            return false;
//        }
        return true;
    }

    public static String getContain(Shape shape) {
        ArrayList<Character> charlist = new ArrayList<>();
        for (int i = 0; i < shape.shapeList.size(); i++) {
            charlist.add(shape.shapeList.get(i).getShapeName());
        }
        charlist.sort(Comparator.naturalOrder());
        String result = "";
        for (char c : charlist){
            result += c;
        }
        return result;
    }

    public static void dfs(Shape shape) {
        if (shape == null) {
            return;
        }
        int level = shape.shapeList.size();
        if(level == 2){
            if(isSame(shape)){
                return;
            }
        } else if (level == 4) {
            if (shape.size() >=20)
                return;
        } else if (level == 5) {
            if (shape.size() >= 14)
                return;
        } else if (level == 6) {
            if (shape.size() >=10)
                return;
        } else if (level == 7) {
            if(shape.size() == 5){
                if(!hasSame(shape)){
                    answer.add(shape);
                    long timeNow = System.currentTimeMillis();
                    System.out.println(answer.size()+":"+ (timeNow - startTime) / 1000 + "s");
                }
            } else {
                return;
            }
        }

        for(Shape s: S){
            if(!shape.shapeList.contains(s)){
                for(int i = 0; i < shape.size(); i++){
                    for (int j = 0; j < symCheck(s); j++) {
                        for (int k = 0; k < 2; k++){
                            Shape shape1 = Connector.connect(shape, s, i, j, k == 0);
                            if (shape1 != null)
                                dfs(Connector.delete4(shape1));
                            else
                                return;
                        }
                    }
                }
            }
        }

    }


    public static void ida(Shape shape, int deth, HashSet<String> sameCheck) {
        if (shape == null)
            return;
        String feature = points2String(reArrange(shape).points);
        if(sameCheck == null||(!sameCheck.contains(feature))){
            sameCheck.add(feature);
            for(Shape s: S){
                if(!shape.shapeList.contains(s)){
                    for(int i = 0; i < shape.size(); i++){
                        for (int j = 0; j < symCheck(s); j++) {
                            for (int k = 0; k < 2; k++){
                                Shape shape1 = Connector.connect(shape, s, i, j, k == 0);
                                if (shape1 != null)
                                    ida(Connector.delete4(shape1), deth);
                                else
                                    return;
                            }
                        }
                    }
                }
            }
            return;
        } else {
            return;
        }
    }


    public static void ida(Shape shape, int deth) {
        if (shape == null) {
            return;
        }

        int level = shape.shapeList.size();
        if (level == 2){
            if (isSame(shape)){
                return;
            }
        }
//        if (level < 5 && containSame(shape))
//            return;
        if (level == 4) {
            if (shape.size() >= DETH-deth)
                return;

        } else if (level == 5) {
            if (shape.size() >= DETH-4-deth)
                return;
        } else if (level == 6) {
            if (shape.size() >= DETH-8-deth)
                return;
        }
        if (level == 7) {
            if(shape.size() == 5){
//                if(!hasSame(shape)){
                answer.add(shape);
                long timeNow = System.currentTimeMillis();
                System.out.println(answer.size()+":"+ (timeNow - startTime) / 1000 + "s");
                //}
            } else {
                return;
            }
        }

        if (level >= 5) {
            HashSet<String> feature = new HashSet<>();
            ida(shape, deth, feature);
        }

        for(Shape s: S){
            if(!shape.shapeList.contains(s)){
                for(int i = 0; i < shape.size(); i++){
                    for (int j = 0; j < symCheck(s); j++) {
                        for (int k = 0; k < 2; k++){
                            Shape shape1 = Connector.connect(shape, s, i, j, k == 0);
                            if (shape1 != null)
                                ida(Connector.delete4(shape1), deth);
                            else
                                return;
                        }
                    }
                }
            }
        }

    }



    static int symCheck(Shape shape) {
        if (shape.size()==4)
            return 1;
        else
            return 3;
    }

    public static ArrayList<Shape> deleteOdd(ArrayList<Shape> shapes) {
        ArrayList<Shape> delete = new ArrayList<>();
        for (int i = 0; i < shapes.size(); i+=40){
            delete.add(shapes.get(i));
        }
        return delete;
    }


    public static LinkedList<LinkedList<Shape>> idaStar() {
        LinkedList<LinkedList<Shape>> result = new LinkedList<>();
        dfs(S6);
        dfs(S7);
        for (Shape s:answer){
            LinkedList<Shape> tmp = new LinkedList<>();
            tmp.add(s);
            result.add(tmp);
        }
        return result;
    }


    public static void main(String[] args) {
//        Shape shape = Connector.connect(S0, S7, 0, 0, true);
//        System.out.println(shape);
//        System.out.println(reArrange(shape));
//        //System.out.println(count(shape));
//
//        Shape shape2 = Connector.connect(S0, S2, 2, 0, true);
//        System.out.println(shape2);
//        System.out.println(reArrange(shape2));
//        System.out.println(reverse(shape2.points));
//        System.out.println("reA1:"+reArrange(shape2));
//
//        Shape rever = new Shape();
//        rever.points = reverse(shape.points);
//
//        System.out.println(hasSame(shape));
//        System.out.println(answerMap);
//        System.out.println(hasSame(shape2));
//        System.out.println(answerMap);
//        System.out.println(shape.shapeList.contains(S0));


        //ida(S7, 3);
        dfs(S7);
        dfs(S6);
        for (Shape s:answer){
            s = reArrange(s);
        }

        //answer = deleteOdd(answer);

        answer.sort(new Comparator<Shape>() {
            @Override
            public int compare(Shape o1, Shape o2) {
                String feature1 = points2String(reArrange(o1).points);
                String feature2 = points2String(reArrange(o2).points);

                return feature2.compareTo(feature1);
            }
        });

        //System.out.println(deleteOdd(answer));






        JFrame jf = new JFrame("图形可视化工具");
        JPanel jpanel = new JPanel();
        jf.add(jpanel);
        jpanel.setSize(600, 600);
        jf.setSize(600, 600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);

        for(int i=0;i<answer.size();i++) {
                Pen4idea pen = new Pen4idea(jf, jpanel);
                pen.beforeDraw(answer.get(i), 300, 300, 50);
                pen.draw("X:/javaProject/Find-all-pentagons-using-tangram/Tangram/ima_output/test/pic"+i+"-.jpg");
                jpanel.repaint();
                System.out.println(i+" ");

        }
    }


}



