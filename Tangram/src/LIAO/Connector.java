package LIAO;

import LIAO.entity.*;
import LIAO.entity.DrawOutline;


import javax.swing.*;
import java.util.LinkedList;

import static LIAO.entity.Tangram.*;

public class Connector {
    public static Shape connect(Shape shapeA, Shape shapeB, int A, int B, Boolean d) {
        CircleList<Point> checkList;
        String flag = "";
        final double THRESHOLD = .0001;

        Shape result = new Shape();
        result = clone(shapeA, shapeB);

//        result.pointOrder2.offer(B);
//        result.orderDirection.offer(!d);
        //result.shapesSet.offer(shapeB);
        System.out.println("\nShapeSetSize:"+result.shapesSet.size());
        int checkAngle = shapeA.getAngel(A) + shapeB.getAngel(B);
        System.out.println("angle:!!!!!" + checkAngle);
        if (checkAngle > 8)
            flag = "failed";
        else if (checkAngle == 8) {
            System.out.println("A: " + A + "B: "+ B + "\n");
            flag = "absorb";
            System.out.println("angle:!!!!!" + checkAngle + "!!!!!!!!angle:!!!!!");

            boolean square = false;
            if (shapeB.size() == 4) {
                square = true;
            }
            double[] length1 = new double[2];
            double[] length2 = new double[2];
            //A左 A-1 A
            length1[0] = shapeA.getLength(A - 1);
            //B左 B B+1
            length1[1] = shapeB.getLength(B);
            //A右 A A+1
            length2[0] = shapeA.getLength(A);
            //B右 B-1 B
            length2[1] = shapeB.getLength(B - 1);
            for (int i = A + 2; i < A + shapeA.size() + 2; i++) {
                Point p = new Point(shapeA.getPoint(i));
                //检查被加图形A左侧顶点A+n-1
                if(i == (A + shapeA.size() - 1)){
                    if(Math.abs(length1[0] - length1[1]) < THRESHOLD){
                        result.addPoint(new Point(shapeA.getAngel(i)+shapeB.getAngel(B + 1), shapeB.getLength(B + 1)));
                    } else if (length1[0] > length1 [1]) {
                        result.addPoint(new Point(shapeA.getAngel(i), length1[0] - length1[1]));
                        result.addPoint(new Point(shapeB.getAngel(B + 1) + 4, shapeB.getLength(B + 1)));
                    } else {
                        result.addPoint(new Point(shapeA.getAngel(i) + 4, length1[1] - length1[0]));
                        result.addPoint(new Point(shapeB.getAngel(B + 1), shapeB.getLength(B + 1)));
                    }

                    if (square)
                        result.addPoint(new Point(shapeB.getPoint(B + 2)));

                    //检查B+n-1
                    if(Math.abs(length2[0] - length2[1]) < THRESHOLD){
                        int k = result.size();
                        result.addPoint(new Point(result.getAngel(k - 1) + shapeA.getAngel(A + 1),
                                shapeA.getLength(A + 1)));
                    } else if (length2[0] > length2[1]) {
                        result.addPoint(new Point(shapeA.getAngel(A) + 4, length2[0] - length2[1]));
                        result.addPoint(new Point(shapeA.getAngel(A + 1), shapeA.getLength(A + 1)));
                    } else {
                        result.addPoint(new Point(shapeB.getAngel(B - 1), length2[1] - length2[0]));
                        result.addPoint(new Point(shapeA.getAngel(A + 1) + 4, shapeA.getLength(A + 1)));
                    }
                    break;
                }  else {
                    result.addPoint(p);

                }

            }

        }
        else {
            int firstSize;

            if (d) {
                checkList = add(shapeA, shapeB, A, B);
                firstSize = shapeA.size();
                int n = (A-2+2*shapeA.size())%shapeA.size();
//                result.pointOrder.offer(n);
                //System.out.println(n+"!!!!!!!!!!");
            } else {
                checkList = add(shapeB, shapeA, B, A);
                firstSize = shapeB.size();
//                result.pointOrder.offer((A+2)%shapeA.size());
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

        if (flag.equals("failed")){

        //if (flag.equals("failed") || flag.equals("absorb")){
            flag = "";
            return null;
        }
        else
            named(result);
            result.shapesSet.offer(result);
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
                shape.delete(i);
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
                    if (newShape!=null){
                        shapes.add(newShape);
                    }
                    //序号
                    System.out.println("-------"+(shapes.size()-1)+"-------");
                    System.out.println(newShape+"\n  i:"+ i+"  j:"+j+"\n"+"direction" + k);
                }
            }
        }
        return shapes;

    }

    private static Shape clone(Shape shape, Shape shapeB) {
        Shape result = new Shape();
        for (Shape s : shape.shapesSet) {
//            result.shapesSet.offer(s);
        }
        for (int i : shape.pointOrder) {
//            result.pointOrder.offer(i);
        }
//        for (int i : shape.pointOrder2) {
//            result.pointOrder2.offer(i);
//        }
//        for (boolean b : shape.orderDirection) {
//            result.orderDirection.offer(b);
//        }

        if(result.shapesSet.size() == 0) {
            Shape s = new Shape();
            for (Point point : shape.points) {
                s.addPoint(point);
            }
            result.shapesSet.offer(s);
//            result.pointOrder.offer(0);
            named(result);
            result.shapesSet.offer(result);
        }

        Shape s1 = new Shape();
        for (Point point : shapeB.points) {
            s1.addPoint(point);
        }
        result.shapesSet.offer(s1);
        return result;
    }

    public static void named(Shape shape) {
        char name = 65;
        for(Point point : shape.points) {
            point.setName(name);
            name++;
        }
        name = 65;

    }

    public static void main(String[] args) throws CloneNotSupportedException {

        //Shape s = AnotherConnector.connect(S0, S1, 1, 2, false);
        //System.out.println(s);


        JFrame jf = new JFrame("图形可视化工具");
        JPanel jpanel = new JPanel();
        jf.add(jpanel);
        jpanel.setSize(1000, 1000);
        jf.setResizable(false);
        jf.setSize(1000, 1000); //设置窗口大小
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//意思就是设置一个默认的关闭操作，也就是你的JFrame窗口的关闭按钮，点击它时，退出程序。
        jf.setVisible(true);// 可视化 显示在屏幕上

        DrawOutline p = new DrawOutline(jpanel);

//        LinkedList<Shape> shape = connectAll(S0, S1);
//        LinkedList<Shape> shape1 = connectAll(shape.get(8), S3);
//        LinkedList<Shape> shape3 = connectAll(shape1.get(3), S5);
//        LinkedList<Shape> shape5 = connectAll(shape3.get(19), S4);
        LinkedList<Shape> shape = connectAll(S0, S1);
        LinkedList<Shape> shape1 = connectAll(shape.get(8), S3);
        LinkedList<Shape> shape2 = connectAll(shape1.get(3), S5);
        LinkedList<Shape> shape3 = connectAll(shape2.get(19), S2);
        LinkedList<Shape> shape4 = connectAll(shape3.get(19), S6);
        LinkedList<Shape> shape5 = connectAll(shape4.get(19), S4);
        //Shape test = shape3.get(19);
        //Shape test = shape5.get(20);

        Shape test = shape5.get(8);

        System.out.println(test);
        //System.out.println("ShapeSet:  " + test.shapesSet);
        System.out.println("order1:  " + test.pointOrder);
        for (Point point:test.points){
            System.out.println("\nname:  " + point.getName());

        }
//        System.out.println("Direction:  " + test.orderDirection);
        p.draw(test);


    }

}
