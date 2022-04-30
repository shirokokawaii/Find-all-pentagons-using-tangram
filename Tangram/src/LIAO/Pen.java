package LIAO;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Pen {// usage:First create an object of pen, then call the method "draw".
    int x1, x2, y1, y2 = 0;
    Graphics graphics;
    JPanel jpanel = new JPanel();
    boolean drawInfinitly = true;
    public Pen(JPanel jpanel) {
        this.graphics = jpanel.getGraphics();
        // this.graphics.setColor(Color.red);
    }

    public void draw(Shape shape) {
        int size = 50;
        ArrayList<ArrayList<Double>> xList = new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> yList = new ArrayList<ArrayList<Double>>();
        double originalX = 500;// start painting from x=0, y=0
        double originalY = 500;
        double nextX = 0;
        double nextY = 0;
        double nextAngle = 0;
        int originalPoint = 0;
        double originalAngle = -180;
        Shape shapeFirst = shape.shapesSet.poll();
        Shape shapeSecond;
        double nextPoint = 0;
        boolean nextDirection;
        while (!shape.shapesSet.isEmpty()) {
            shapeSecond = shape.shapesSet.poll();
            nextPoint = shape.pointOrder1.poll();
            nextDirection = shape.orderDirection.poll();
            int len = shapeFirst.size();
            ArrayList<Double> x = new ArrayList<>();
            ArrayList<Double> y = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                originalAngle += 180;
                if (originalAngle >= 360) {
                    originalAngle -= 360;
                }
                double length = shapeFirst.getLength(originalPoint) * size;
                double diffAngle = 45 * shapeFirst.getAngel(originalPoint);
                originalAngle += diffAngle;
                if (i == nextPoint) {
                    nextX = originalX;
                    nextY = originalY;
                    if (nextDirection == true) {
                        nextAngle = originalAngle;// clockwise
                    } else {
                        nextAngle = originalAngle - diffAngle; // anti-clockwise
                    }
                }
                double diffX = length * Math.cos(Math.PI * originalAngle / 180);
                double diffY = length * Math.sin(Math.PI * originalAngle / 180);
                originalX += diffX;
                originalY += diffY;
                x.add(originalX);
                y.add(originalY);
                originalPoint++;
                if (originalPoint == len) {
                    originalPoint = 0;
                }
            }
            xList.add(x);
            yList.add(y);
            shapeFirst = shapeSecond;
            originalX = nextX;
            originalY = nextY;
            originalAngle = nextAngle;
        }

        int len = shapeFirst.size();
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        for (int i = 0; i < len; i++) {// The last shape
            originalAngle += 180;
            double length = shapeFirst.getLength(originalPoint) * size;
            double angle = originalAngle + 45 * shapeFirst.getAngel(originalPoint);
            originalAngle = angle;
            double diffX = length * Math.cos(Math.PI * angle / 180);
            double diffY = length * Math.sin(Math.PI * angle / 180);
            originalX += diffX;
            originalY += diffY;
            x.add(originalX);
            y.add(originalY);
            originalPoint++;
            if (originalPoint == len) {
                originalPoint = 0;
            }
        }
        xList.add(x);
        yList.add(y);
        drawLine(xList, yList);
    }

    public void drawLine(ArrayList<ArrayList<Double>> xList, ArrayList<ArrayList<Double>> yList) {
        int index = 0;
        drawInfinitly = true;
        while(drawInfinitly==true) {
            ArrayList<Double> x = xList.get(index);
            ArrayList<Double> y = yList.get(index);
            int len = x.size();
            boolean flag = false;
            for (int i = 0; i < len; i++) {
                int x1 = (int) Math.round(x.get(i));
                int y1 = (int) Math.round(y.get(i));
                if (i + 1 == len) {
                    i = -1;
                    flag = true;
                }
                int x2 = (int) Math.round(x.get(i + 1));
                int y2 = (int) Math.round(y.get(i + 1));
                graphics.drawLine(x1, y1, x2, y2);
                if (flag == true) {
                    break;
                }
            }
            index++;
            if(index ==xList.size()){
                index =0;
            }
        }
    }

    public void stop(){
        drawInfinitly = false;
    }
}