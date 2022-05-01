package LIAO.entity;

import LIAO.Shape;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class DrawOutline {// usage:First create an object of pen, then call the method "draw".
    Graphics graphics;
    JPanel jpanel = new JPanel();
    boolean drawInfinitly = true;
    public DrawOutline(JPanel jpanel) {
        this.graphics = jpanel.getGraphics();
        //this.graphics.setColor(Color.red);

    }

    public void draw(LIAO.Shape shape) {
        int size = 50;
        ArrayList<ArrayList<Double>> xList = new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> yList = new ArrayList<ArrayList<Double>>();
        double originalX = 500;// start painting from x=0, y=0
        double originalY = 500;

        int originalPoint = 0;
        int direction = 1;
        double originalAngle = -180;
        Shape shapeFirst = shape;



            int len = shapeFirst.size();
            ArrayList<Double> x = new ArrayList<>();
            ArrayList<Double> y = new ArrayList<>();
            x.add(originalX);
            y.add(originalY);
            for (int i = 0; i < len; i++) {
                originalAngle += 180;
                double length = shapeFirst.getLength(originalPoint) * size;
                double diffAngle = 45 * shapeFirst.getAngel(originalPoint);
                originalAngle -= diffAngle*direction;
                System.out.println(originalAngle);


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
            for (int i = 0; i < len-1; i++) {
                int x1 = (int) Math.round(x.get(i));
                int y1 = (int) Math.round(y.get(i));
                int x2 = (int) Math.round(x.get(i + 1));
                int y2 = (int) Math.round(y.get(i + 1));
                graphics.drawLine(x1, y1, x2, y2);
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

    public static void main(String[] args) {

    }
}