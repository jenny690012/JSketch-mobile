package com.example.jenny.jsketch;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;

/**
 * Created by Jenny on 07/07/2016.
 */
public class Rectangle extends Shape implements Cloneable{


    public Rectangle(PointF origin, int thick,int c){
        setOrigin(origin);
        setThicknessLine(thick);
        setFillColor(0);
        setLineColor(c);
    }

    @Override
    protected void drawShape(Paint p,Canvas c) {

        float left = Math.min(getOrigin().x, getEnd().x);
        float right = Math.max(getOrigin().x,getEnd().x);
        float top = Math.min(getOrigin().y,getEnd().y);
        float bottom=Math.max(getOrigin().y,getEnd().y);

        c.drawRect(left,top,right,bottom,p);

        if(getFilledColor()!=0){
            p.setColor(getColor(getFilledColor()));
            p.setStrokeWidth(0);
            p.setStyle(Paint.Style.FILL);
            c.drawRect(left+getThickness()/2,
                       top+getThickness()/2,
                       right-getThickness()/2,
                       bottom-getThickness()/2,p);

        }

        if(selected){
            Paint drawSeleted = seletedPaint();
            left= left-getThickness();
            right=right+getThickness();
            top=top-getThickness();
            bottom=bottom+getThickness();
            c.drawRect(left,top,right,bottom,drawSeleted);
        }
    }

    @Override
    public boolean isSelected(PointF p) {
        float left = Math.min(getOrigin().x, getEnd().x);
        float right = Math.max(getOrigin().x,getEnd().x);
        float top = Math.min(getOrigin().y,getEnd().y);
        float bottom=Math.max(getOrigin().y,getEnd().y);
        Rect r = new Rect(Math.round(left),
                          Math.round(top),
                          Math.round(right),
                          Math.round(bottom));
        return r.contains(Math.round(p.x),Math.round(p.y));
    }
}
