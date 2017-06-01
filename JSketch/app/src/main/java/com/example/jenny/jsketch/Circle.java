package com.example.jenny.jsketch;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;

/**
 * Created by Jenny on 07/07/2016.
 */
public class Circle extends Shape implements Cloneable{



    public Circle(PointF origin, int thick, int c ){
        setOrigin(origin);
        setThicknessLine(thick);
        setLineColor(c);
    }


    @Override
    protected void drawShape( Paint p, Canvas c) {

        float length = findLength(getOrigin(),getEnd());
         c.drawCircle(getOrigin().x,getOrigin().y,length,p);

        if(getFilledColor()!=0){
            p.setColor(getColor(getFilledColor()));
            p.setStrokeWidth(0);
            p.setStyle(Paint.Style.FILL);
            c.drawCircle(getOrigin().x,getOrigin().y,length-getThickness()/2,p);

        }

        if(selected){
            Paint drawSeleted = seletedPaint();
            c.drawCircle(getOrigin().x,getOrigin().y,length+getThickness(),drawSeleted);
        }
    }

    @Override
    public boolean isSelected(PointF p) {


        float len1 = findLength(getOrigin(),p);
        float len2 = findLength(getOrigin(),getEnd());

        return len1<=len2;
    }
}
