package com.example.vector;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog; 
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint; 
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainPage extends Activity implements OnTouchListener, OnLongClickListener {

	KhogenView khogenview;
	Thread OurThread;
	boolean isRunning = false;
	Bitmap[] BitmapArray ;
	ArrayList<Bitmap> BitmapArrayWalk = new ArrayList<Bitmap>();
	ArrayList<Bitmap> BitmapArrayRun = new ArrayList<Bitmap>();
	ArrayList<Bitmap> BitmapArrayJump = new ArrayList<Bitmap>();
	ArrayList<Bitmap> BackgroundList = new ArrayList<Bitmap>();
	Bitmap[] BackgroundArray; 
	
	Bitmap walk,walk1,walk2,walk3,walk4,walk5,walk6;
	Bitmap run1,run2,run3,run4,run5,run6,run7,run8;
	Bitmap jump1,jump2,jump3,jump4,jump5,jump6,jump7,jump8,jump9;
	Bitmap sword,Girl,Couple;
	Bitmap grass,horror,jungle,mario,open,village;
	Bitmap pause,play,restart;
	 
	Canvas c;
	int IncreasingNo=0;
	int TotalFotos = 6;
	int FotoNo;
	int xpos;
	int xspeed;
	int yspeed;
	int ypos;
	int Points=0;
	int SwordPosition=900;
	int jumpheight = 84;
	int AddOne=0;
	int AddOneForPause=0;
	int GirlXPosition,GirlYPosition;
	int CoupleXPos,CoupleYpos;
	int GX=0,GY=0;
	float getX,getY,MovingX,MovingY;
	boolean Movingx=true;
	int OddEven=2;
	int PauseTimer=2;
	int JumpVariable= 0;
	int SwordSpeed=10;
	boolean SwordVariable = true;
	boolean ResumeBoolean= true;
	boolean Resumexspeed = true;
	boolean Coupleprint=false;
	final static int Alert_Dialog = 1; //why the prefixes important for alert dialog???
	int BackgroundPositionLeft =0;
	
	Rect Source1,Destination1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		khogenview = new KhogenView(this);
		setContentView(khogenview);
		walk = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.man);
		walk1 = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.man1);
		walk2 = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.man2);
		walk3 = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.man3);
		walk4 = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.man4);
		walk5 = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.man5);
		walk6 = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.man6);
		
		run1 = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.run1);
		run2 = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.run2);
		run3 = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.run3);
		run4 = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.run4);
		run5 = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.run5);
		run6 = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.run6);
		run7 = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.run7);
		run8 = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.run8);
		
		
		jump1 = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.jump1);
		jump2 = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.jump2);
		jump3 = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.jump3);
		jump4 = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.jump4);
		jump5 = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.jump5);
		jump6 = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.jump6);
		jump7 = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.jump7);
		jump8 = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.jump8);
		jump9 = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.jump9);
		
		grass = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.grass);
		horror = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.horror);
	    jungle = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.jungle);
		mario = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.mario);
		open = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.open);
		village = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.village);
		
		sword = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.sword);
		Girl = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.girl);
		Couple = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.couples);
		
		pause = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.pause);
		play = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.play);
		restart = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.restart);
		
		BitmapArrayWalk.add(walk);
		BitmapArrayWalk.add(walk1); 
		BitmapArrayWalk.add(walk2);  
		BitmapArrayWalk.add(walk3);
		BitmapArrayWalk.add(walk4);
		BitmapArrayWalk.add(walk5);
		BitmapArrayWalk.add(walk6);
		
		BitmapArrayRun.add(run1);
		BitmapArrayRun.add(run2);
		BitmapArrayRun.add(run3);
		BitmapArrayRun.add(run4);
		BitmapArrayRun.add(run5);
		BitmapArrayRun.add(run6);
		BitmapArrayRun.add(run7);
		BitmapArrayRun.add(run8);
		
		BitmapArrayJump.add(jump1);
		BitmapArrayJump.add(jump2);
		BitmapArrayJump.add(jump3);
		BitmapArrayJump.add(jump4);
		BitmapArrayJump.add(jump5); 
		BitmapArrayJump.add(jump6);
		BitmapArrayJump.add(jump7);
		BitmapArrayJump.add(jump8);
		BitmapArrayJump.add(jump9);
		
		BackgroundList.add(open);
		BackgroundList.add(grass);
		BackgroundList.add(village);  
		BackgroundList.add(jungle);
		BackgroundList.add(horror);
		BackgroundList.add(mario);
		
		BackgroundArray = BackgroundList.toArray(new Bitmap[BackgroundList.size()]);
		BitmapArray = BitmapArrayWalk.toArray(new Bitmap[BitmapArrayWalk.size()]);
		
		xpos = 300;
		//ypos=(c.getHeight()-90)/2;
		ypos=300;
		yspeed=0;
		xspeed=25;
		
		khogenview.setOnTouchListener(this);
		khogenview.setOnLongClickListener(this);
	    
	}
	
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		Dialog dialog=null;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		switch(id){
		
		case  Alert_Dialog :
			builder.setMessage("Choose an option ! ").setPositiveButton("Exit", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Toast.makeText(MainPage.this, "exiting...", Toast.LENGTH_LONG).show();
					khogenview.pause();
				}
			}).setNegativeButton("Resume",new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Toast.makeText(MainPage.this, "Game Resumed", Toast.LENGTH_LONG).show();
					khogenview.resume();
					dialog.cancel();
				}
			} ).setCancelable(false);
			dialog = builder.create();
			break;
			default:
			
		}
		
		return dialog;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		khogenview.pause();
		
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		khogenview.resume();
		ResumeBoolean = true;
		xspeed = 5;
		
	}
	//class change
	public class KhogenView extends SurfaceView implements Runnable {
		
	
		public KhogenView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			
		}
		 
		SurfaceHolder OurHolder = getHolder();

		@Override   
		public void run() {
			// TODO Auto-generated method stub
			
			while(isRunning){
				if(!OurHolder.getSurface().isValid())//isRunning ??
				continue;     
				
			c = OurHolder.lockCanvas(); 
			
			
			
			if(PauseTimer==1){
				xspeed=0;
				
				
				BackgroundChosen();
				IncreasingNo= IncreasingNo-1;
				SwordMotion();
				ScoreChange();
				
				update();
				
				c.drawBitmap(play, 50, 50, null);
				c.drawBitmap(restart, 150, 50, null);
			}
			else {
				
				if(SwordPosition<xpos+30 && ypos>=c.getHeight()-90 && SwordPosition>xpos )
				{
					Movingx=false;
					SwordVariable = false;
					xspeed=0;
					SwordSpeed=0;
					FotoNo = 0;
					TotalFotos = BitmapArrayWalk.size();
					   BitmapArray = BitmapArrayWalk.toArray(new Bitmap[BitmapArrayWalk.size()]);
					   Resumexspeed = false;
						
				}
				if(Resumexspeed==true){
					xspeed = 5;
					Movingx=true;
					if(Points==0)
						SwordSpeed=10;
					
					if(Points==25)
		    			SwordSpeed=20;
				}
				
			BackgroundChosen();  
			SwordMotion(); 
			ScoreChange(); 
			update();
			c.drawBitmap(pause, 50, 50, null);
			c.drawBitmap(restart, 150, 50, null);
			}
			
			OurHolder.unlockCanvasAndPost(c);
			
			
			}
		}
		public void update() {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
        
			//xpos = xpos + xspeed;  
			//ypos = ypos + yspeed;

		}
		public void pause(){
			
			ResumeBoolean = false;
			xspeed = 0;
			isRunning = false;
			while(true)
			{		try {
				OurThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			OurThread= null;
			}
			
			
		}
		public void resume(){
			isRunning=true;
			OurThread = new Thread(this);
			OurThread.start();
		}
		
	}
	@Override
	public boolean onTouch(View v, MotionEvent e) {
		
		MovingX=e.getX();
		MovingY=e.getY();
		switch(e.getAction()) {
		
		
		case MotionEvent.ACTION_DOWN :
			
			/* Dialog d = new Dialog(MainPage.this);
	    		d.setTitle("Level 2 ");
	    		TextView tv = new TextView(MainPage.this);
	    		tv.setText("Gain 25 more points");
	    		d.setContentView(tv);
	    		d.show(); */
			
			
			
			
			AddOneForPause++;
			getX = e.getX();
			getY = e.getY();
			
			
			if(getX>=50 && getX<=110 && getY>=50 && getY<=110)
			{
				if(AddOneForPause%2==0)
					PauseTimer=2;
				else PauseTimer =1;
			}else if (getX>=150 && getX<=210 && getY>=50 && getY<=110){  //restart
				AddOne = 0;
				Movingx=true;
				xspeed=5;
				SwordPosition = 900;
				SwordVariable = true;
				Resumexspeed = true;
				Coupleprint=false;
			}
			{
			if(JumpVariable==0)
			{
				JumpVariable=1;
			jumpheight = 120;
			if(SwordVariable==false)
				jumpheight=84;
			IncreasingNo=0;
			 TotalFotos = BitmapArrayJump.size() ;
			 BitmapArray = BitmapArrayJump.toArray(new Bitmap[BitmapArrayJump.size()]); 
			 
			}
			try {
				Thread.sleep(1500);
				JumpVariable=0;
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			}
			
			
			break;    //when always kept clicking keep the vector on top  
			
		case MotionEvent.ACTION_MOVE :
			if(Movingx==true){
			xpos=(int) MovingX;
			GirlXPosition=xpos;
			}
			
			jumpheight=84;
			TotalFotos = BitmapArrayWalk.size();
			   BitmapArray = BitmapArrayWalk.toArray(new Bitmap[BitmapArrayWalk.size()]);
			//   MainPage.this.showDialog(Alert_Dialog);               change the positon;
			
			break;
			
		/*case MotionEvent.ACTION_UP :
			TotalFotos = BitmapArrayWalk.size();
			BitmapArray = BitmapArrayWalk.toArray(new Bitmap[BitmapArrayWalk.size()]);
			break;  */
		}
		
		return true;
		// TODO Auto-generated method stub 
		
	}

	
	@Override
	public boolean onLongClick(View v) {           //not working!!!
		// TODO Auto-generated method stub
		//Toast.makeText(this, "hello", Toast.LENGTH_LONG).show();
		{
		TotalFotos = BitmapArrayRun.size();
		BitmapArray = BitmapArrayRun.toArray(new Bitmap[BitmapArrayRun.size()]);}
		
		
		
		return true; 
	}

    public void BackgroundChosen(){
    	
    	switch(Points){
    	
    	case 0:
    		Source1 = new Rect(BackgroundPositionLeft, 0,BackgroundPositionLeft+ BackgroundArray[0].getWidth()/4, BackgroundArray[0].getHeight());
    	        Destination1 = new Rect(0,0,c.getWidth(),c.getHeight());
    	         
    	        c.drawBitmap(BackgroundArray[0], Source1, Destination1, null);
    	        BackgroundPositionLeft += xspeed;
    	        if(BackgroundPositionLeft>=BackgroundArray[0].getWidth()/2)
    	        	BackgroundPositionLeft=0;
    	        
    	        //man running
    	        FotoNo = IncreasingNo%TotalFotos ;
    			
    			
				ypos = c.getHeight()-jumpheight;
    			c.drawBitmap(BitmapArray[FotoNo], xpos, ypos,null);
    			
    			
    			   if(IncreasingNo==BitmapArrayJump.size()-1)
    			   {
    				   jumpheight = 84;
    				   TotalFotos = BitmapArrayWalk.size();
    				   BitmapArray = BitmapArrayRun.toArray(new Bitmap[BitmapArrayRun.size()]);
    					
    			   } 
    			   
    			   IncreasingNo++;  // make it zero to avoid overflow
    			  /*if(IncreasingNo==6)   //changes here for change in array
    					IncreasingNo = 0;*/
    	        
    	        break;
    	        
    	        
    	case 25:
    		
    		
    		
    		Source1 = new Rect(BackgroundPositionLeft, 0,BackgroundPositionLeft+ BackgroundArray[1].getWidth()/4, BackgroundArray[1].getHeight());
    	        Destination1 = new Rect(0,0,c.getWidth(),c.getHeight());
    	         
    	        c.drawBitmap(BackgroundArray[1], Source1, Destination1, null);
    	        BackgroundPositionLeft += xspeed;
    	        if(BackgroundPositionLeft>=BackgroundArray[1].getWidth()/2)
    	        	BackgroundPositionLeft=0;
    	        
    	        //man running
    	        FotoNo = IncreasingNo%TotalFotos ;
    			
    			
				ypos = c.getHeight()-jumpheight;
    			c.drawBitmap(BitmapArray[FotoNo], xpos, ypos,null);
    			
    			
    			   if(IncreasingNo==BitmapArrayJump.size()-1)
    			   {
    				   jumpheight = 84;
    				   TotalFotos = BitmapArrayRun.size();
    				   BitmapArray = BitmapArrayRun.toArray(new Bitmap[BitmapArrayRun.size()]);
    					
    			   } 
    			   
    			   IncreasingNo++;  // make it zero to avoid overflow
    			   
    			   
    			   
    			  
    			 
    	        
    	        break;
    	        
    	        
    	case 50:
    		Source1 = new Rect(BackgroundPositionLeft, 0,BackgroundPositionLeft+ BackgroundArray[2].getWidth()/4, BackgroundArray[2].getHeight());
    	        Destination1 = new Rect(0,0,c.getWidth(),c.getHeight());
    	         
    	        c.drawBitmap(BackgroundArray[2], Source1, Destination1, null);
    	        BackgroundPositionLeft += xspeed;
    	        if(BackgroundPositionLeft>=BackgroundArray[2].getWidth()/2)
    	        	BackgroundPositionLeft=0;
    	        
    	        //man running
    	        FotoNo = IncreasingNo%TotalFotos ;
    			
    			
				ypos = c.getHeight()-jumpheight;
				if(Coupleprint==false){
    			c.drawBitmap(BitmapArray[FotoNo], xpos, ypos,null);
    			CoupleXPos=xpos;
				}
    			
    			   if(IncreasingNo==BitmapArrayJump.size()-1)
    			   { 
    				   jumpheight = 84;
    				   TotalFotos = BitmapArrayRun.size();
    				   BitmapArray = BitmapArrayRun.toArray(new Bitmap[BitmapArrayRun.size()]); 
    					
    			   }  
    			   
    			   IncreasingNo++;  // make it zero to avoid overflow
    			   
    			   if(Coupleprint==false){
    			   if(GY==0)
    			   GX=xpos;
    			 
    			  c.drawBitmap(Girl, GX-75, GY, null);
    			  GY+=20;
    			  
    			  if(GY>c.getHeight())
    				  GY=0;
    			 }
    			  
    			  if(GX-75-xpos<30 || xpos-GX-75<30 && ypos-GY<30 || GY-ypos<30)
    			  {
    				  c.drawBitmap(Couple, CoupleXPos, ypos, null);
    				  Coupleprint=true;
    				  GX=GY=0;
    				  
    			  }
    			    
    	        
    	        break;
    	        
    	        
    	case 75:
    		Source1 = new Rect(BackgroundPositionLeft, 0,BackgroundPositionLeft+ BackgroundArray[3].getWidth()/4, BackgroundArray[3].getHeight());
    	        Destination1 = new Rect(0,0,c.getWidth(),c.getHeight());
    	         
    	        c.drawBitmap(BackgroundArray[3], Source1, Destination1, null);
    	        BackgroundPositionLeft += xspeed;
    	        if(BackgroundPositionLeft>=BackgroundArray[3].getWidth()/2)
    	        	BackgroundPositionLeft=0;
    	        
    	        //man running
    	        FotoNo = IncreasingNo%TotalFotos ;
    			
    			
				ypos = c.getHeight()-jumpheight;
    			c.drawBitmap(BitmapArray[FotoNo], xpos, ypos,null);
    			
    			
    			   if(IncreasingNo==BitmapArrayJump.size()-1)
    			   {
    				   jumpheight = 84;
    				   TotalFotos = BitmapArrayRun.size();
    				   BitmapArray = BitmapArrayRun.toArray(new Bitmap[BitmapArrayRun.size()]);
    					
    			   } 
    			   
    			   IncreasingNo++;  // make it zero to avoid overflow
    			  
    	        break;
    	        
    	        
    	case 100:
    		Source1 = new Rect(BackgroundPositionLeft, 0,BackgroundPositionLeft+ BackgroundArray[4].getWidth()/4, BackgroundArray[4].getHeight());
    	        Destination1 = new Rect(0,0,c.getWidth(),c.getHeight());
    	         
    	        c.drawBitmap(BackgroundArray[4], Source1, Destination1, null);
    	        BackgroundPositionLeft += xspeed;
    	        if(BackgroundPositionLeft>=BackgroundArray[4].getWidth()/2)
    	        	BackgroundPositionLeft=0;
    	        
    	        //man running
    	        FotoNo = IncreasingNo%TotalFotos ;
    			
    			
				ypos = c.getHeight()-jumpheight;
    			c.drawBitmap(BitmapArray[FotoNo], xpos, ypos,null);
    			
    			
    			   if(IncreasingNo==BitmapArrayJump.size()-1)
    			   {
    				   jumpheight = 84;
    				   TotalFotos = BitmapArrayRun.size();
    				   BitmapArray = BitmapArrayRun.toArray(new Bitmap[BitmapArrayRun.size()]);
    					
    			   } 
    			   
    			   IncreasingNo++;  // make it zero to avoid overflow
    			  
    	        break; 
    	        
 
    	        
    	
    	}
	
		
		
			}
    public void SwordMotion(){
    	if(SwordPosition <= 0) {
    		SwordPosition = c.getWidth();
    	AddOne ++;  
    	if(AddOne==25 || AddOne==50 ||AddOne==75 ||AddOne==100)
    		Points=AddOne;
    	}
    		if(Points==25) 
    			SwordSpeed=20;
    	SwordPosition = SwordPosition-(SwordSpeed);
    	if(Coupleprint==false)
    	c.drawBitmap(sword, SwordPosition, c.getHeight()-70, null);
    } 
    public void ScoreChange(){
    	Paint score  = new Paint();
    	score.setColor(Color.RED);
    	score.setAntiAlias(true);
    	score.setFakeBoldText(true);
    	score.setTextSize(30);
    	score.setTextAlign(Align.LEFT);
    	c.drawText("Score: "+ AddOne, c.getWidth()-(8*score.getTextSize()), 50, score);
    }
}
