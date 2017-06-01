package com.example.jenny.jsketch;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class JSketch extends Activity {

    private static final String UNDO="undo";


    ImageButton thick1,thick2,thick3,thick4,red,blue,green,orange,
            select,fill,delete,circle, rectangle,line,photos,redo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hide the window title and other stuff
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.sketch);


        photos = (ImageButton) findViewById(R.id.photos);
        photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  setColorToolButton(7);
            }
        });

        redo = (ImageButton) findViewById(R.id.redo);
        redo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorToolButton(8);
            }
        });


        thick1 = (ImageButton) findViewById(R.id.thick1);
        thick2 = (ImageButton) findViewById(R.id.thick2);
        thick3 = (ImageButton) findViewById(R.id.thick3);
        thick4 = (ImageButton) findViewById(R.id.thick4);
        red = (ImageButton) findViewById(R.id.red);
        orange = (ImageButton) findViewById(R.id.orange);
        green = (ImageButton) findViewById(R.id.green);
        blue = (ImageButton) findViewById(R.id.blue);
        select = (ImageButton) findViewById(R.id.select);
        fill = (ImageButton) findViewById(R.id.fill);
        delete = (ImageButton) findViewById(R.id.delete);
        circle = (ImageButton) findViewById(R.id.circle);
        rectangle = (ImageButton) findViewById(R.id.rectangle);
        line = (ImageButton) findViewById(R.id.line);

        thick1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorThicknessButton(1);
            }
        });

        thick2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorThicknessButton(2);
            }
        });

        thick3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorThicknessButton(3);
            }
        });

        thick4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorThicknessButton(4);
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorToolButton(1);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorToolButton(3);
            }
        });
        delete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                   Model.get(getApplicationContext()).clear();
                Toast.makeText(getApplicationContext(),
                        "Model has changed, Canvas will be redrawn upon next action",
                        Toast.LENGTH_SHORT).show();
                setColorToolButton(3);
                return true;
            }
        });

        fill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorToolButton(2);
            }
        });


        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorToolButton(4);
            }
        });

        rectangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorToolButton(5);
            }
        });

        line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorToolButton(6);
            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorButton(1);
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorButton(2);
            }
        });

        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorButton(3);
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorButton(4);
            }
        });

        setColorButton(Model.get(this.getApplicationContext()).getColor());
        setColorThicknessButton(Model.get(this.getApplicationContext()).getThickness());
        setColorToolButton(Model.get(this.getApplicationContext()).getToolpick());

    }//on create

    void setColorThicknessButton(int code){ //code={1,2,3,4}

        int thickness= Model.get(this.getApplicationContext()).getThickness();

        if(thickness!=code) {

            if (thickness == 1) thick1.setBackgroundColor(Color.WHITE);
            else if (thickness == 2) thick2.setBackgroundColor(Color.WHITE);
            else if (thickness == 3) thick3.setBackgroundColor(Color.WHITE);
            else thick4.setBackgroundColor(Color.WHITE);
        }

            if(code==1) thick1.setBackgroundColor(Color.RED);
            else if(code==2) thick2.setBackgroundColor(Color.RED);
            else if(code==3) thick3.setBackgroundColor(Color.RED);
            else thick4.setBackgroundColor(Color.RED);
            Model.get(this.getApplicationContext()).setThickness(code);

    }

    void setColorButton(int code){ //code={1,2,3,4}
        
        int color = Model.get(this.getApplicationContext()).getColor();
        
        if(color!=code) {

            if (color == 1) red.setBackgroundColor(Color.RED);
            else if (color == 3) orange.setBackgroundColor(Color.YELLOW);//change this later
            else if (color == 2) green.setBackgroundColor(Color.GREEN);
            else blue.setBackgroundColor(Color.BLUE);
        }

            if(code==1) red.setBackgroundColor(Color.BLACK);
            else if(code==2) green.setBackgroundColor(Color.BLACK);
            else if(code==3) orange.setBackgroundColor(Color.BLACK);
            else blue.setBackgroundColor(Color.BLACK);
            
            Model.get(this.getApplicationContext()).setColor(code);

    }

    void setColorToolButton(int code){

        //select=1,fill=2,delete=3,circle=4,rectangle=5,line=6
            int toolpick= Model.get(this.getApplicationContext()).getToolpick();
        
          if(toolpick!=code) {
              if (toolpick == 1) select.setBackgroundColor(Color.WHITE);
              else if (toolpick == 2) fill.setBackgroundColor(Color.WHITE);//change this later
              else if (toolpick == 3) delete.setBackgroundColor(Color.WHITE);
              else if (toolpick == 4) circle.setBackgroundColor(Color.WHITE);
              else if (toolpick == 5) rectangle.setBackgroundColor(Color.WHITE);
              else if (toolpick==6) line.setBackgroundColor(Color.WHITE);
              else if (toolpick==7) photos.setBackgroundColor(Color.WHITE);
              else redo.setBackgroundColor(Color.WHITE);
          }

            if(code==1) select.setBackgroundColor(Color.BLUE);
            else if(code==2) fill.setBackgroundColor(Color.BLUE);//change this later
            else if(code==3) delete.setBackgroundColor(Color.BLUE);
            else if(code==4) circle.setBackgroundColor(Color.BLUE);
            else if(code==5)rectangle.setBackgroundColor(Color.BLUE);
            else if(code==6) line.setBackgroundColor(Color.BLUE);
            else if(code==7)photos.setBackgroundColor(Color.BLUE);
            else if(code==8) redo.setBackgroundColor(Color.BLUE);
            Model.get(this.getApplicationContext()).setToolpick(code);
    }


}
