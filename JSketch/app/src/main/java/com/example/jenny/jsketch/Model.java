package com.example.jenny.jsketch;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Jenny on 07/07/2016.
 */
public class Model {

    private static final String TAG = "select_option";
    private static final String UNDO="undo";

    private static Model model;
    private Context context;
    private ArrayList<Shape> shapes;
    private int shapes_size;
    private PointF last_pos;


    private int color;

    //thick1=1, thick2=2, thick3=3,thick4=4
    private int thickness;

    //select=1,fill=2,delete=3,circle=4,rectangle=5,line=6,undo
    private int toolpick;

    private Model(Context c){
        context =c;
        color=1;
        thickness=1;
        toolpick=1;
        shapes = new ArrayList<Shape>();
        shapes_size=0;
        last_pos=null;
    }

    public static Model get(Context c){
        if(model==null){
            model= new Model(c.getApplicationContext());
        }
        return model;
    }


    public int getColor(){return color;}
    public void setColor(int c){color=c;}
    public int getThickness(){return thickness;}
    public void setThickness(int t){thickness=t;}
    public int getToolpick(){return toolpick;}
    public void setToolpick(int t){toolpick=t;}

    public void addShape(Shape s){
        shapes.add(s);
        shapes_size++;
    }
    public ArrayList<Shape> getShapes(){return shapes;}

    public boolean deleteShape(PointF p){
        if(shapes_size!=0) {
            for (int i = shapes_size - 1; i >= 0; i--) {
                if (shapes.get(i).ifSelected(p)) {
                    shapes.remove(i);
                    shapes_size--;
                    return true;
                }
            }
        }
        return false;
    }


    public void dragLastShape(PointF p){
        Log.i(TAG,"dragLastShape is called");
        float len_x = p.x-last_pos.x;
        float len_y = p.y-last_pos.y;
        PointF ori = shapes.get(shapes_size-1).getOrigin();
        PointF end= shapes.get(shapes_size-1).getEnd();
        ori= new PointF(ori.x+len_x,ori.y+len_y);
        end= new PointF(end.x+len_x,end.y+len_y);
        shapes.get(shapes_size-1).setOrigin(ori);
        shapes.get(shapes_size-1).setEnd(end);
        Log.i(TAG,"exiting dragLastShape. ori="+ori);
        last_pos=p;
    }

    public void doneDraging(){
        last_pos=null;
        shapes.get(shapes_size-1).resetSelected();
    }

    public boolean moveShape(PointF p) {
        Log.i(TAG,"moveShape is called. "+shapes_size+" element in shapes");
        if(shapes_size!=0) {
            int lastIndex = shapes_size - 1;
            for (int i = lastIndex; i >= 0; i--) {
                if (shapes.get(i).ifSelected(p)) {

                        Shape s = shapes.get(i);
                        s.setSelected();
                        shapes.remove(i);
                        shapes.add(s);
                        last_pos = p;
                        Log.i(TAG,"element of index selected is "+i);
                        return true;
                }
            }
        }
        return false;
    }

    public boolean fillShape(PointF p,int color){
        if(shapes_size!=0) {
            int lastIndex = shapes_size - 1;
            for (int i = lastIndex; i >= 0; i--) {
                if (shapes.get(i).ifSelected(p)) {
                    shapes.get(i).setFillColor(color);
                    return true;
                }
            }
        }
        return false;
    }

    public void clear(){
        shapes.clear();
        shapes_size=0;
    }

    public void undo(){
        Log.i(UNDO,"undo in model.java is called, shapes size "+shapes.size());
        if(UndoCommand.get().ifUndoPossible()){
            shapes=null;
            shapes=UndoCommand.get().removeLastedAction();
            shapes_size= shapes.size();
            Log.i(UNDO,"undo in model.java is exiting, shape_size "+shapes_size);
        }
    }

    public void redo(){
        if(UndoCommand.get().ifRedoPossible()){
            shapes=UndoCommand.get().redoLastedAction();
            shapes_size=shapes.size();
        }
    }

    public void addAction(){//remember to call this function again when deleting canvas
            UndoCommand.get().addAction(shapes);
            Log.i(UNDO,"addAction in Model.java throw expection");

    }
}
