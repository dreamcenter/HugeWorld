package top.dreamcenter.hw.frame;

import javax.swing.*;
import java.awt.*;

public class RadarPanel {
    private int width;

    private final int x0,y0;
    private double w;
    private final double R;
    private double x,y;
    private int t;

    private int basicX,basicY;

    public RadarPanel(int x, int y, int width){
        this.width = width;
        R = Math.sqrt(2)*width/2;
        w = -0.08;
        this.x =x + width*1.0/2;
        this.y = y + width;
        basicX = x + (int)(R-width/2) +15;
        basicY = y + (int)(R-width/2) +15;
        x0 = width/2 + basicX;
        y0 = width/2 + basicY;
    }

    private void next() {
        t++;
        x = x0+R*Math.sin(w*t);
        y = y0+R*Math.cos(w*t);
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(basicX-(int)(R-width/2),basicY-(int)(R-width/2),(int)R*2,(int)R*2);
        g.setColor(Color.GREEN);
        int temp = 0;
        for (int i = 0; i < 5; i++) {
            g.drawOval(basicX+temp,basicY+temp,width-2*temp,width-2*temp);
//            g.drawOval(x+temp+1,y+temp+1,width-2*temp-2,width-2*temp-2);
            g.drawLine(basicX+width/2,basicY-(int)(R-width/2),basicX+width/2,basicY+width+(int)(R-width/2));
            g.drawLine(basicX-(int)(R-width/2),basicY+width/2,basicX+width+(int)(R-width/2),basicY+width/2);
            g.drawLine(basicX,basicY,basicX+width,basicY+width);
            g.drawLine(basicX+width, basicY, basicX,basicY+width);
            temp += (4-i)*(width/20);
        }
        g.drawLine(x0,y0,(int)x,(int)y);
        g.drawLine(x0,y0,(int)x+2,(int)y+1);
        next();
    }
}
