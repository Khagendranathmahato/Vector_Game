package com.example.vector;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class KhogenView extends SurfaceView implements Runnable {
	boolean isRunning = true;
	Thread OurThread;
	SurfaceHolder OurHolder;

	public KhogenView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		OurHolder=getHolder();
		OurThread = new Thread(this);
		OurThread.start(); 
	}
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub  
		
		while(OurHolder.getSurface().isValid()!=true)  //isRunning ??
			continue;
		
		Canvas c = OurHolder.lockCanvas();
		c.drawRGB(255, 0, 0);
		OurHolder.unlockCanvasAndPost(c);
		
		
	}
	

}
