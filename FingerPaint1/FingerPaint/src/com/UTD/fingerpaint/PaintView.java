package com.UTD.fingerpaint;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.*;
import android.view.View.OnTouchListener;

public class PaintView extends View implements OnTouchListener
	{
	Paint pBlue;
	Paint pColor;
	float x, y;
	ArrayList<Point> pts;
	public Path drawPath;
	private Canvas drawCanvas;
	//canvas bitmap
	private Bitmap canvasBitmap;
	private Paint canvasPaint;
	
	PaintView(Context context)
	{
	super(context);
  setupDrawing();
	}
	
	public PaintView(Context context, AttributeSet attrs){
  super(context, attrs);
  setupDrawing();
}
	
	public void setupDrawing()
		{
		pBlue = new Paint(Color.BLUE);
		pColor = new Paint(Color.BLACK);
		pColor.setStyle(Paint.Style.STROKE);
		pColor.setStrokeWidth(3.0f);
    pts = new ArrayList<Point>();
    canvasPaint = new Paint(Paint.DITHER_FLAG);
    }
	
	public void setPoint(float x, float y)
		{
		Point pt = new Point(x, y);
		pt.setColor(pColor.getColor());
		pts.add(pt);
		this.y = y;
//???		pColor.setColor(Color.BLUE);
		invalidate();
		}
	
	public void setColor(int c)
		{
		pColor.setColor(c);
		}
	
	public void setWidth(float w)
		{
		pColor.setStrokeWidth(w);
		}
	
	@Override
	public boolean onTouch(View arg0, MotionEvent e)
		{
		PaintView pv = (PaintView) arg0;
		float x = e.getX();
		float y = e.getY();
		switch (e.getAction()) {
		case MotionEvent.ACTION_DOWN:
		    drawPath.moveTo(x, y);
		    break;
		case MotionEvent.ACTION_MOVE:
		    drawPath.lineTo(x, y);
		    break;
		case MotionEvent.ACTION_UP:
		    drawCanvas.drawPath(drawPath, pColor);
		    drawPath.reset();
		    break;
		default:
		    return false;
		}
		invalidate();
		return true;
		}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
	//view given size
	super.onSizeChanged(w, h, oldw, oldh);
  canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
  drawCanvas = new Canvas(canvasBitmap);
	}
	
	
	@Override
	public void onDraw(Canvas canvas)
		{
		
//???		canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
//???		canvas.drawPath(drawPath, pColor);
		if (pts.size() > 1)
			{
		Point ptStart = pts.get(0);
//???		canvas.drawLine(0,  0, 60, 60, pBlue);
		for (int ix=1; ix<pts.size(); ix++)
			{
			Point pt = pts.get(ix);
			canvas.drawLine(ptStart.ptx, ptStart.pty, pt.ptx, pt.pty, pt.ptColor);
			ptStart = pt;
			}
			}
		}
	
	private class Point
	{
	public float ptx;
	public float pty;
	public Paint ptColor;
	public Point(float x, float y)
		{
		ptx = x;
		pty = y;
		}
	public void setColor(int c)
		{
		ptColor = new Paint();
		ptColor.setColor(c);
		ptColor.setStyle(Paint.Style.STROKE);
		ptColor.setStrokeWidth(pColor.getStrokeWidth());
		}
	}
	}
