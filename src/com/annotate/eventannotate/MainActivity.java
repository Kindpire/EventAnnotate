package com.annotate.eventannotate;

import com.annotate.eventannotate.R;

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
//	           //?????????????????????????????? 
//	            @Override 
//	            public boolean onSingleTapConfirmed(MotionEvent e) { 
//	                System.out.println("onSingleTapConfirmed"); 
//	                return false; 
//	            } 
//	            //????????????????????? 
//	            @Override 
//	            public boolean onDoubleTap(MotionEvent e) { 
//	                System.out.println("onDoubleTap"); 
//	                return false; 
//	            } 
//	          //????????????????????? 
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
    // ???????????????????????????1???MotionEvent ACTION_DOWN?????? 
    @Override 
    public boolean onDown(MotionEvent e) { 
        System.out.println("onDown"); 
        return false; 
    } 
  // ?????????????????????????????????????????????????????????1???MotionEvent ACTION_DOWN??????    
   // ?????????onDown()????????????????????????????????????????????????????????? 
    @Override 
    public void onShowPress(MotionEvent e) { 
 
    } 
    //????????????????????????????????????????????????1???MotionEvent ACTION_UP?????? 
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
   // ???????????????????????????????????????1???MotionEvent ACTION_DOWN, ??????ACTION_MOVE?????? 
    @Override 
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) { 
        System.out.println("onScroll"); 
        return false; 
    } 
   // ?????????????????????????????????MotionEvent ACTION_DOWN?????? 
    @Override 
    public void onLongPress(MotionEvent e) { 
        System.out.println("onLongPress" + e.getEventTime()); 
    } 
   //???????????????????????????????????????????????????1???MotionEvent ACTION_DOWN, ??????ACTION_MOVE, 1???ACTION_UP?????? 
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
    //????????????????????? 
 //   @Override 
    public boolean onDoubleTap(MotionEvent e) { 
        System.out.println("onDoubleTap"); 
        return false; 
    } 
  //????????????????????? 
//    @Override 
    public boolean onDoubleTapEvent(MotionEvent e) { 
        System.out.println("onDoubleTapEvent"); 
        return false; 
    }

}


