package com.UTD.fingerpaint;

import android.os.Bundle;
import android.widget.*;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.app.Activity;
import android.app.Fragment;
import android.graphics.drawable.ColorDrawable;
import android.view.*;

public class PaintActivity extends Activity implements OnSeekBarChangeListener
	{
	PaintView pv;

	@Override
	protected void onCreate(Bundle savedInstanceState)
		{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_paint);
		SeekBar sb = (SeekBar)this.findViewById(R.id.ctlWidth);
		sb.setProgress(1);
		sb.setOnSeekBarChangeListener(this);
//???		sb.setMax(11);
		pv = (PaintView)findViewById(R.id.paintview);
//???		pv.setOnTouchListener(new TouchDetector());
		pv.invalidate();
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
		{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.paint, menu);
		return true;
		}
	
	@Override
	public boolean onTouchEvent(MotionEvent evt)
		{
		pv.setPoint(evt.getX(), evt.getY() - pv.getTop()-10);
		return true;
		}
	
	public void colorClick(View v)
		{
		Button btn = (Button)v;
		ColorDrawable cd = (ColorDrawable)v.getBackground();
		pv.setColor(cd.getColor());
		}
	
	public void widthChanged(View v)
		{
		}

	@Override
  public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2)
		{
	  pv.setWidth(arg1+1);
	  }

	@Override
  public void onStartTrackingTouch(SeekBar arg0)
	  {
	  // TODO Auto-generated method stub
	  
	  }

	@Override
  public void onStopTrackingTouch(SeekBar arg0)
	  {
	  // TODO Auto-generated method stub
	  
	  }
	
	}
