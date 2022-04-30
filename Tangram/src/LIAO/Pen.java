//package LIAO;
//
//import java.awt.*;
//import java.util.*;
//import javax.swing.*;
//
//public class Pen {// usage:First create an object of pen, then call the method "draw".
//    int x1, x2, y1, y2 = 0;
//    Graphics graphics;
//    JPanel jpanel = new JPanel();
//    boolean drawInfinitly = true;
//    public Pen(JPanel jpanel) {
//        this.graphics = jpanel.getGraphics();
//        //this.graphics.setColor(Color.red);
//    }
//
//    public void draw(Shape shape) {
//        int size = 50;
//        ArrayList<ArrayList<Double>> xList = new ArrayList<ArrayList<Double>>();
//        ArrayList<ArrayList<Double>> yList = new ArrayList<ArrayList<Double>>();
//        HashMap<String, Double> xHashMapList = new HashMap<>();
//        HashMap<String, Double> yHashMapList = new HashMap<>();
//        HashMap<String, Double> directionTrue = new HashMap<>();
//        double originalX = 500;
//        double originalY = 500;
//        int originalPoint = 0;
//        double originalAngle = 0;
//        String nextPoint = "A";
//        while (!shape.shapesSet.isEmpty()) {
//            Shape shapeFirst = shape.shapesSet.poll();
//            int len = shapeFirst.size();
//            for(int i=0;i<len;i++){
//                if(shapeFirst.getName(i).equals(nextPoint)){
//                    originalPoint = i;
//                }
//            }
//            nextPoint = shape.pointOrder.poll();
//            ArrayList<Double> x = new ArrayList<>();
//            ArrayList<Double> y = new ArrayList<>();
//            originalAngle -= 180;
//            for (int i = 0; i < len; i++) {
//                originalAngle += 180;
//                double length = shapeFirst.getLength(originalPoint) * size;
//                double diffAngle = 45 * shapeFirst.getAngel(originalPoint);
//                originalAngle -= diffAngle;
//                xHashMapList.put(shapeFirst.getName(i), originalX);
//                yHashMapList.put(shapeFirst.getName(i), originalY);
//                directionTrue.put(shapeFirst.getName(i), originalAngle);
//                double diffX = length * Math.cos(Math.PI * originalAngle / 180);
//                double diffY = length * Math.sin(Math.PI * originalAngle / 180);
//                originalX += diffX;
//                originalY += diffY;
//                x.add(originalX);
//                y.add(originalY);
//                originalPoint++;
//                if (originalPoint == len) {
//                    originalPoint = 0;
//                }
//            }
//            xList.add(x);
//            yList.add(y);
//            originalX = xHashMapList.get(nextPoint);
//            originalY = yHashMapList.get(nextPoint);
//            originalAngle = directionTrue.get(nextPoint);
//        }
//        drawLine(xList, yList);
//    }
//
//    public void drawLine(ArrayList<ArrayList<Double>> xList, ArrayList<ArrayList<Double>> yList) {
//        int index = 0;
//        drawInfinitly = true;
//        while(drawInfinitly==true) {
//            ArrayList<Double> x = xList.get(index);
//            ArrayList<Double> y = yList.get(index);
//            int len = x.size();
//            boolean flag = false;
//            for (int i = 0; i < len; i++) {
//                int x1 = (int) Math.round(x.get(i));
//                int y1 = (int) Math.round(y.get(i));
//                if (i + 1 == len) {
//                    i = -1;
//                    flag = true;
//                }
//                int x2 = (int) Math.round(x.get(i + 1));
//                int y2 = (int) Math.round(y.get(i + 1));
//                graphics.drawLine(x1, y1, x2, y2);
//                if (flag == true) {
//                    break;
//                }
//            }
//            index++;
//            if(index ==xList.size()){
//                index =0;
//            }
//        }
//    }
//
//    public void stop(){
//        drawInfinitly = false;
//    }
//}