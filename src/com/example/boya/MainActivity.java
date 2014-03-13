package com.example.boya;

import android.app.Activity;
import android.app.TabActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity{
	String TAG = "boya";
	TabHost tabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main); 
		tabHost = getTabHost();
		Log.i(TAG,"ok1");
		TabSpec tab1 = tabHost.newTabSpec("tab1")
				.setIndicator("最新")
				.setContent(R.id.tab01);
		Log.i(TAG,"ok2");
		tabHost.addTab(tab1);
		Log.i(TAG,"ok3");
		TabSpec tab2 = tabHost.newTabSpec("tab2")
				.setIndicator("热门")
				.setContent(R.id.tab02);
		tabHost.addTab(tab2);
		TabSpec tab3 = tabHost.newTabSpec("tab3")
				.setIndicator("消息")
				.setContent(R.id.tab03);
		tabHost.addTab(tab3);
		
	}
	
}
