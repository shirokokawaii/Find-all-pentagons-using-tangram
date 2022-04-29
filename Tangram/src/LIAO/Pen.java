package LIAO;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import LIAO.Tangram;

public class Pen{
    int x1, x2, y1, y2 = 0;
    Graphics graphics;
    Shape shape = new Shape();
    JPanel jpanel = new JPanel();
    public Pen(Shape shape, JPanel jpanel){
        this.shape = shape;
        this.jpanel = jpanel;
    }

    public void calculatePoint(Shape shape, ArrayList<Double> x, ArrayList<Double> y){
        double originalX = 0;
        double originalY = 0;
        int originalPoint = 0;
        double orginalAngle = -180;
        while(!shape.shapesSet.isEmpty()){
            Shape shapeTem = shape.shapesSet.poll();
            int len = shapeTem.size();
            for(int i=0;i<len;i++){
                int index = originalPoint;
                double length = shapeTem.getLength(i);
                double angle = 45*shapeTem.getAngel(i);
                double diffX = length*Math.cos(angle);
                double diffY = length*Math.sin(angle);
                originalX += diffX;
                originalY += diffY;
                x.add(originalX);
                y.add(originalY);
            }
        }
    }

    public void drawLine(ArrayList<Integer> x, ArrayList<Integer> y){
        int len = x.size();
        for(int i=0;i<=len;i++){
            int x1 = x.get(i);
            int y1 = y.get(i);
            if(i+1 == len){
                i = 0;
            }
            int x2 = x.get(i+1);
            int y2 = y.get(i+1);
            graphics.drawLine(x1, y1, x2, y2);
        }
    }
}
