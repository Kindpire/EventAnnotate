package com.example.eventannotate;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.GestureDetector;

public class MainActivity extends Activity implements GestureDetector.OnGestureListener{
	ImageButton button;
	boolean pressed;
	UIEventLog logwrite;
	GestureDetector detector;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	    button= (ImageButton)findViewById(R.id.imageButton1);
	    detector = new GestureDetector(this, this);
	    //button.setOnClickListener(imgButtonHandler);
	    pressed = false;
//	    detector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() { 
//	           //短快的点击算一次单击 
//	            @Override 
//	            public boolean onSingleTapConfirmed(MotionEvent e) { 
//	                System.out.println("onSingleTapConfirmed"); 
//	                return false; 
//	            } 
//	            //双击时产生一次 
//	            @Override 
//	            public boolean onDoubleTap(MotionEvent e) { 
//	                System.out.println("onDoubleTap"); 
//	                return false; 
//	            } 
//	          //双击时产生两次 
//	            @Override 
//	            public boolean onDoubleTapEvent(MotionEvent e) { 
//	                System.out.println("onDoubleTapEvent"); 
//	                return false; 
//	            } 
//	        }); 
	    button.setOnTouchListener
	    (
	    		new OnTouchListener()
	    		{
	    			@Override
	    			public boolean onTouch(View arg0, MotionEvent arg1)
	    			{
	    				if (detector.onTouchEvent(arg1))
	    				{
	    					Log.v("touch", "singletouch");
	    					return true;
	    				}
	    				else
	    				{
	    					Log.v("touch", "singletouch2");
	    				}
	    				return false;
	    			}
	    		}
	    );
	}

    @Override 
    public boolean onTouchEvent(MotionEvent event) { 
        return detector.onTouchEvent(event); 
    } 
    // 用户轻触触摸屏，由1个MotionEvent ACTION_DOWN触发 
    @Override 
    public boolean onDown(MotionEvent e) { 
        System.out.println("onDown"); 
        return false; 
    } 
  // 用户轻触触摸屏，尚未松开或拖动，由一个1个MotionEvent ACTION_DOWN触发    
   // 注意和onDown()的区别，强调的是没有松开或者拖动的状态 
    @Override 
    public void onShowPress(MotionEvent e) { 
 
    } 
    //用户（轻触触摸屏后）松开，由一个1个MotionEvent ACTION_UP触发 
    @Override 
    public boolean onSingleTapUp(MotionEvent e) { 
        System.out.println("onSingleTapUp"); 
    	pressed = !pressed;
    	if (pressed)
    	{
    		button.setBackgroundResource(R.drawable.red);
    		
    		TextView textview = (TextView)findViewById(R.id.textView1);
    		textview.setText("Press to write a END!");
    		
    		logwrite.put("start");
    	}
    	else
    	{
    		button.setBackgroundResource(R.drawable.yellow_img);
    		
    		TextView textview = (TextView)findViewById(R.id.textView1);
    		textview.setText("Press to write a START!");
    		
    		logwrite.put("end");
    	}
        return false; 
    } 
   // 用户按下触摸屏，并拖动，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE触发 
    @Override 
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) { 
        System.out.println("onScroll"); 
        return false; 
    } 
   // 用户长按触摸屏，由多个MotionEvent ACTION_DOWN触发 
    @Override 
    public void onLongPress(MotionEvent e) { 
        System.out.println("onLongPress" + e.getEventTime()); 
    } 
   //用户按下触摸屏、快速移动后松开，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE, 1个ACTION_UP触发 
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override 
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) { 
        System.out.println("onFling: " + (e1.getX() - e2.getX()) + " " + (e1.getY() - e2.getY()) + " " + velocityX + " " + velocityY); 
        System.out.println(e1.getY());
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        DialogFragment newFragment = new deleteEventDialogFragment();
        newFragment.show(ft, "dialog");
        return false; 
    } 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
//	@Override
    public boolean onSingleTapConfirmed(MotionEvent e) { 
        System.out.println("onSingleTapConfirmed"); 
        return false; 
    } 
    //双击时产生一次 
 //   @Override 
    public boolean onDoubleTap(MotionEvent e) { 
        System.out.println("onDoubleTap"); 
        return false; 
    } 
  //双击时产生两次 
//    @Override 
    public boolean onDoubleTapEvent(MotionEvent e) { 
        System.out.println("onDoubleTapEvent"); 
        return false; 
    }

}


