package com.example.jenny.jsketch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Jenny on 08/07/2016.
 */
public class SketchBoard extends View{

    private static final String TAG = "select";
    private static final String UNDO="undo";


    private Shape myShape;
    boolean shape_found=false;

    public SketchBoard(Context c){ this(c, null);}
    public SketchBoard(Context c, AttributeSet attrs){
        super(c,attrs);
    }



    public boolean onTouchEvent(MotionEvent event){
        int toolpick = Model.get(getContext()).getToolpick();
        int thickness = Model.get(getContext()).getThickness();
        int color = Model.get(getContext()).getColor();
        PointF curr = new PointF(event.getX(),event.getY());

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

                if(toolpick==1) {//select
                    Log.i(TAG,"on touch even is DOWN,select");
                   shape_found=Model.get(getContext()).moveShape(curr);
                    invalidate();
                }else if(toolpick==2) {//fill
                   Model.get(getContext()).fillShape(curr,color);
                    invalidate();
                } else if (toolpick == 3) {
                    Model.get(getContext()).deleteShape(curr);
                    invalidate();
                }else if(toolpick==4){//circle
                    myShape = new Circle(curr,thickness,color);
                    Model.get(getContext()).addShape(myShape);
                }else if(toolpick==5){//rectangle
                    myShape= new Rectangle(curr,thickness,color);
                    Model.get(getContext()).addShape(myShape);
                }else if(toolpick==6){//line
                    myShape = new Line(curr,thickness,color);
                    Model.get(getContext()).addShape(myShape);
                }else if(toolpick==7){
                    Log.i(UNDO,"button is pressed,next call undo");
                    Model.get(getContext()).undo();
                    Log.i(UNDO,"finished undo, next redraw");
                    invalidate();
                }else{
                    Model.get(getContext()).redo();
                    invalidate();
                }

                if(toolpick!=7 && toolpick!=8){
                    Model.get(getContext()).addAction();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(toolpick==1){//select
                    if(shape_found) Model.get(getContext()).dragLastShape(curr);
                }else if(toolpick==4 || toolpick==5 //circle,rectangle
                                     || toolpick==6){//line
                    if(myShape!=null){
                        myShape.setEnd(curr);
                    }
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                if(toolpick==1){
                    shape_found=false;
                    Model.get(getContext()).doneDraging();
                }
                myShape=null;

                break;
            case MotionEvent.ACTION_CANCEL:
                if(toolpick==1){
                    Model.get(getContext()).doneDraging();
                    shape_found=false;
                }
                myShape=null;
                break;
        }//end of switch

     return true;
    }//end of touch

    @Override
    public void setOnLongClickListener(OnLongClickListener l) {
        super.setOnLongClickListener(l);
        int toolpick = Model.get(getContext()).getToolpick();
        if(toolpick==3){
            Model.get(getContext()).clear();
        }
    }

    protected void onDraw(Canvas c){

        ArrayList<Shape> shapes = Model.get(getContext()).getShapes();
         Log.i(UNDO,"onDraw called, shapes has size "+shapes.size());
           for(Shape s: shapes){
               s.draw(c);
           }
    }//end of Draw

/*
    public void Undo() {

            Log.i(UNDO,"button is pressed,next call undo");
            Model.get(getContext()).undo();
            Log.i(UNDO,"finished undo, next redraw");
            invalidate();
        Log.i(UNDO,"finish Undo in SketchBoard");
    }*/
}
