package com.example.boya;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ListView;
import android.widget.ViewFlipper;
public class MainActivity extends Activity implements OnGestureListener,OnTouchListener{
	String TAG = "boya";
	LayoutInflater factory;
	View newView;
	View hotView;
	View messageView;
	ViewFlipper myViewFlipper;
	GestureDetector myGestureDetector;
	ListView content_new;
	ListView content_hot;
	ListView content_message;
	Handler handler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		setContentView(R.layout.main); 
		myViewFlipper = (ViewFlipper) findViewById(R.id.myViewFlipper); 
		
		myGestureDetector = new GestureDetector(this,this);   
		myViewFlipper.setLongClickable(true);
		myViewFlipper.setOnTouchListener(this);
		content_new = (ListView) findViewById(R.id.content_new);
		content_hot = (ListView) findViewById(R.id.content_hot);
		content_message = (ListView) findViewById(R.id.content_message);
		content_new.setOnTouchListener(this);
		content_hot.setOnTouchListener(this);
		content_message.setOnTouchListener(this);
		Getdown.getInstance().refresh("topten"); 
	}
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		if((e1.getX()-e2.getX())>50)
		{
			myViewFlipper.showNext();
			return true;
		}
		else if((e1.getX()-e2.getX())<-50)
		{
			myViewFlipper.showPrevious();
			return true;
		}
		return false;
	}
	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		
		return myGestureDetector.onTouchEvent(event); 
	}
	
}
