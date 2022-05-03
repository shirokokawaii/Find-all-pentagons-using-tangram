package LIAO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Pen extends Thread{// usage:First create an object of pen, then call the method "draw".
    int x1, x2, y1, y2 = 0;
    Graphics graphics;
    JPanel jpanel = new JPanel();
    JFrame jframe = new JFrame();
    boolean drawInfinitly;
    ArrayList<ArrayList<Double>> xList = new ArrayList<ArrayList<Double>>();
    ArrayList<ArrayList<Double>> yList = new ArrayList<ArrayList<Double>>();
    public Pen(JFrame jframe, JPanel jpanel) {
        this.graphics = jpanel.getGraphics();
        this.jpanel = jpanel;
        this.jframe = jframe;
    }

    public void beforeDraw(Shape shape, int X, int Y, int size) {
    	xList.clear();
    	yList.clear();
        double originalX = X;
        double originalY = Y;
        double nextX = X;
        double nextY = Y;
        double nextAngle = 0;
        int originalPoint = 0;
        double originalAngle = 0;
        char nextPoint;
        while (!shape.shapesSet.isEmpty()) {
            Shape shapeFirst = shape.shapesSet.poll();
            if(!shape.pointOrder.isEmpty()) {
            	nextPoint = shape.pointOrder.poll();
            }
            else {
            	nextPoint = 'A';
            }
            int len = shapeFirst.size();
            for(int i=0;i<len;i++){
                if(shapeFirst.getName(i) == 'A'){
                    originalPoint = i;
                }
            }
            ArrayList<Double> x = new ArrayList<>();
            ArrayList<Double> y = new ArrayList<>();
            x.add(originalX);
            y.add(originalY);
            originalAngle -= 180;
            for (int i = 0; i < len; i++) {
                originalAngle += 180;
                double length = shapeFirst.getLength(originalPoint) * size;
                double diffAngle = 45 * shapeFirst.getAngel(originalPoint);
                if(shapeFirst.getName(originalPoint) == nextPoint) {
                	nextX = originalX;
                	nextY = originalY;
                	nextAngle = originalAngle;
                	
                }
                originalAngle -= diffAngle;
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
            originalX = nextX;
            originalY = nextY;
            originalAngle = nextAngle;
        }
    }

    public void draw(boolean drawInfinitly) {
    	this.drawInfinitly = drawInfinitly;
        int index = 0;
        for(int count=0;count<10;count++) {
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
    
    public void draw(String path) {
        int index = 0;
        for(int count=0;count<100;count++) {
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
        try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        savePic(path);
    }
    
    public void savePic(String path){
		BufferedImage myImage = null;
		try {
			myImage = new Robot().createScreenCapture(
					new Rectangle(jframe.getX()+8, jframe.getY()+31, jpanel.getWidth(), jpanel.getHeight()));
			ImageIO.write(myImage, "jpg", new File(path));
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    public void savePanelAsJpg(String path) {

    	BufferedImage image = new BufferedImage(jpanel.getWidth(),jpanel.getHeight(), BufferedImage.TYPE_INT_RGB);
    	Graphics2D g2 = image.createGraphics();
    	jpanel.print(g2);
    	try {
			ImageIO.write(image, "jpg", new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}