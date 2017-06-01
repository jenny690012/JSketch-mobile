package com.example.jenny.jsketch;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;


/**
 * Created by Jenny on 07/07/2016.
 */
public class Line extends Shape implements Cloneable{

    private static final String TAG = "select_option";

    public Line(PointF origin,int thick, int c){
        setOrigin(origin);
        setThicknessLine(thick);
        setFillColor(0);
        setLineColor(c);
    }

    @Override
    protected void drawShape( Paint p, Canvas c) {
        c.drawLine(getOrigin().x,getOrigin().y,getEnd().x,getEnd().y,p);


        if(getFilledColor()!=0){
            p.setColor(getColor(getFilledColor()));
            c.drawLine(getOrigin().x,getOrigin().y,getEnd().x,getEnd().y,p);
            setLineColor(getFilledColor());
            setFillColor(0);
        }

        if(selected){
            Paint drawSeleted = seletedPaint();
            drawSeleted.setStyle(Paint.Style.FILL);
            c.drawCircle(getOrigin().x,getOrigin().y,getThickness()*2,drawSeleted);
            c.drawCircle(getEnd().x,getEnd().y,getThickness()*2,drawSeleted);
        }
    }

    @Override
    public boolean isSelected(PointF p) {

        Log.i(TAG,"isSelected in Circle is called");

        float x = findLength(getOrigin(),p);
        float len2 = findLength(getOrigin(),getEnd());
        float y = findLength(getEnd(),p);

        Log.i(TAG,"x+y-1= "+(x+y-2)+", len2= "+len2);
        return x+y-2< len2;
    }
}
