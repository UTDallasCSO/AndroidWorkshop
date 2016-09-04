package com.UTD.fingerpaint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;


public class TouchDetector implements OnTouchListener
	{
		
	@Override
	public boolean onTouch(View arg0, MotionEvent e)
		{
		PaintView pv = (PaintView) arg0;
		float x = e.getX();
		float y = e.getY();
		switch (e.getAction()) {
		case MotionEvent.ACTION_DOWN:
		    pv.drawPath.moveTo(x, y);
		    break;
		case MotionEvent.ACTION_MOVE:
		    pv.drawPath.lineTo(x, y);
		    break;
		case MotionEvent.ACTION_UP:
//???		    drawCanvas.drawPath(drawPath, drawPaint);
		    pv.drawPath.reset();
		    break;
		default:
		    return false;
		}
		
		//???		pv.setPoint(arg1.getX(), arg1.getY());
		// TODO Auto-generated method stub
		return false;
		}

	}
