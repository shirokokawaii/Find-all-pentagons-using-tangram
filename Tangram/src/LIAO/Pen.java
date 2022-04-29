package LIAO;

import java.awt.*;

public class Pen{
    int x1, x2, y1, y2 = 0;
    Graphics graphics;
    public Pen(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    public void drawLine(){
        graphics.drawLine(x1,y1,x2,y2);
    }
}
