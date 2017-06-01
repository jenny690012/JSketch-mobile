package com.example.jenny.jsketch;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Jenny on 09/07/2016.
 *
 * this website teaches how to deep copy
 * http://stackoverflow.com/questions/4199429/proper-way-to-deep-copy-with-copy-constructor-instead-of-object-clone
 */
public class UndoCommand {
    private ArrayList< ArrayList<Shape> >undo_list;
    private static UndoCommand undo;
    private static final String UNDO="undo";

    int pointer;
    int size;

    public UndoCommand(){
        undo_list = new ArrayList< ArrayList<Shape> >();
        undo_list.add(new ArrayList<Shape>());
        pointer=1;
        size=1;
    }

    public static UndoCommand get(){
        if(undo==null){
            undo = new UndoCommand();
        }
        return undo;
    }



    public ArrayList<Shape> deepCopy (ArrayList<Shape> shapes) throws CloneNotSupportedException{

        int shapes_size=0;
        ArrayList<Shape> copy= new ArrayList<Shape>();
        for(Shape s: shapes){
            copy.add((Shape)s.clone());
            shapes_size++;
        }
        Log.i(UNDO,"the size of copied shapes is : "+shapes_size);
        return copy;
    }

    public void addAction (ArrayList<Shape> shapes){
        try {
            ArrayList<Shape> copy = deepCopy(shapes);
            int i=size-1-pointer;
            if(i>0){
                undo_list.remove(size-1);
                size--;
            }
            undo_list.add(copy);
            size++;
            pointer = size;
        }catch(CloneNotSupportedException e){
            Log.i(UNDO,"trust me, my AddAction will work without throwing exception");
        }
    }

    public boolean ifUndoPossible(){return pointer>0;}
    public boolean ifRedoPossible(){return pointer<size-2;}
    public ArrayList<Shape> removeLastedAction(){
        Log.i(UNDO,"removeLastedAction is called in undoCommand.java");
        pointer--;
        ArrayList<Shape> lasted_copy;
        try {
             lasted_copy= deepCopy(undo_list.get(pointer));
        }catch (CloneNotSupportedException e){
            lasted_copy=null;
            Log.i(UNDO,"I dont want to see my removeLastedAction failed");
        }
        Log.i(UNDO,"removeLastedAction is exiting in undoCommand.java, size="+size);
       return lasted_copy;
    }

    public ArrayList<Shape> redoLastedAction(){
        Log.i(UNDO,"removeLastedAction is called in undoCommand.java");
        ArrayList<Shape> lasted_copy=null;
        pointer++;
        try {
             lasted_copy= deepCopy(undo_list.get(pointer));
        }catch (CloneNotSupportedException e){
            Log.i(UNDO,"I dont want to see my redoLastedAction failed");
        }
            Log.i(UNDO, "removeLastedAction is exiting in undoCommand.java, size=" + size);
            return lasted_copy;

    }

}
