package LIAO;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Pen{//usage:First create an object of pen, then call the method "draw".
    int x1, x2, y1, y2 = 0;
    Graphics graphics;
    JPanel jpanel = new JPanel();

    public void draw(Shape shape, JPanel jpanel){
        this.jpanel = jpanel;
        this.graphics = jpanel.getGraphics();
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        double originalX = 0;//start painting from x=0, y=0
        double originalY = 0;
        double nextX = 0;
        double nextY = 0;
        double nextAngle = 0;
        int originalPoint = 0;
        double originalAngle = 0;
        x.add(originalX);
        y.add(originalY);
        Shape shapeFirst = shape.shapesSet.poll();
        Shape shapeSecond;
        double nextPoint = 0;
        boolean nextDirection;
        while(!shape.shapesSet.isEmpty()){
            shapeSecond = shape.shapesSet.poll();
            nextPoint = shape.pointOrder1.poll();
            nextDirection = shape.orderDirection.poll();
            int len = shapeFirst.size();

            for(int i=0;i<len;i++){
                originalAngle += 180;
                if(originalAngle >=360){
                    originalAngle -= 360;
                }
                double length = shapeFirst.getLength(originalPoint);
                double diffAngle = 45*shapeFirst.getAngel(originalPoint);
                originalAngle += diffAngle;
                if(i==nextPoint){
                    nextX = originalX;
                    nextY = originalY;
                    if(nextDirection==true){
                        nextAngle = originalAngle;//clockwise
                    }
                    else{
                        nextAngle = originalAngle-diffAngle; //anti-clockwise
                    }
                }
                double diffX = length*Math.cos(originalAngle);
                double diffY = length*Math.sin(originalAngle);
                originalX += diffX;
                originalY += diffY;
                x.add(originalX);
                y.add(originalY);
                originalPoint++;
                if(originalPoint == len){
                    originalPoint = 0;
                }
            }
            drawLine(x, y);
            x.clear();
            y.clear();
            shapeFirst = shapeSecond;
            originalX = nextX;
            originalY = nextY;
            originalAngle = nextAngle;
        }

        int len = shapeFirst.size();
        for(int i=0;i<len;i++){//The last shape
            originalAngle += 180;
            if(originalAngle >=360){
                originalAngle -= 360;
            }
            double length = shapeFirst.getLength(originalPoint);
            double angle = originalAngle + 45*shapeFirst.getAngel(originalPoint);
            originalAngle = angle;
            double diffX = length*Math.cos(angle);
            double diffY = length*Math.sin(angle);
            originalX += diffX;
            originalY += diffY;
            x.add(originalX);
            y.add(originalY);
            originalPoint++;
            if(originalPoint == len){
                originalPoint = 0;
            }
        }
        drawLine(x, y);
    }


    public void drawLine(ArrayList<Double> x, ArrayList<Double> y){
        int len = x.size();
        for(int i=0;i<len;i++){
            int x1 = (int)Math.round(x.get(i));
            int y1 = (int) Math.round(y.get(i));
            if(i+1 == len){
                i = -1;
            }
            int x2 = (int)Math.round(x.get(i+1));
            int y2 = (int) Math.round(y.get(i+1));
            graphics.drawLine(x1, y1, x2, y2);
        }
    }
}
