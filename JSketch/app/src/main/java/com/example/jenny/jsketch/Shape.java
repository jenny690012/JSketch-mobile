package com.example.jenny.jsketch;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

public abstract class Shape implements Cloneable{//do I make this class public?

	 private int color_line;
	 private int color_filled;
	 private int thickness_line;
     private PointF origin;
	 PointF end;
	 protected boolean selected=false;


	 public void setEnd(PointF e){end = e;}

	 public void setFillColor(int color){color_filled= color;}

	 protected void setLineColor(int color){color_line=color;}
	 protected void setThicknessLine(int thickness){thickness_line=thickness;}
	 protected void setOrigin(PointF origin ){ this.origin=origin;}
	 protected PointF getOrigin(){return origin;}
	 protected PointF getEnd(){return end;}
	 protected int getFilledColor(){return color_filled;}

	 protected float findLength(PointF p1, PointF p2){
		float rise= Math.abs(p1.x-p2.x);
		float run = Math.abs(p1.y-p2.y);
		return (float) Math.sqrt(rise*rise+run*run);
	}


	 protected int getColor(int color_line){

		 if(color_line==0)     return Color.WHITE;
		 else if(color_line==1)     return Color.RED;
		 else if(color_line==2)return Color.GREEN;
		 else if(color_line==3)return Color.YELLOW;
		 else                  return Color.BLUE;
	 }

	protected float getThickness(){
		if(thickness_line==1)     return 3;
		else if(thickness_line==2)return 10;
		else if(thickness_line==3)return 15;
		else                      return 23;
	}

	protected abstract void drawShape(Paint p,Canvas c);
    protected abstract boolean isSelected(PointF p);
	public void resetSelected(){selected=false;}
	public void setSelected(){selected=true;}
	public boolean ifSelected(PointF p){
		return isSelected(p);
	}

	protected Paint seletedPaint(){
		Paint drawSeleted = new Paint();
		drawSeleted.setColor(Color.GRAY);
		drawSeleted.setStyle(Paint.Style.STROKE);
		if(getThickness()<5) drawSeleted.setStrokeWidth(getThickness());
		else drawSeleted.setStrokeWidth(6);
		return drawSeleted;
	}

	public void draw(Canvas c){

		Paint p= new Paint();

		p.setColor(getColor(color_line));
		p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(getThickness());


			drawShape(p, c);
	}

	public void changeOrientation(){
		float y = getOrigin().x;
		float x=getOrigin().y;
		setOrigin(new PointF(x,y));

		y=getEnd().x;
		x=getEnd().y;
		setEnd(new PointF(x,y));

	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
