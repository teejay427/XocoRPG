package com.xocogames.xocorpg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public class CharacterView extends View {

	Paint characterPaint;
	Bitmap characterPic;

	boolean widthAndHeightInitialized = false;
	int canvasWidth;
	int canvasHeight;
	int canvasWidthCenter;
	int canvasHeightCenter;

	public CharacterView( Context context ){
		super( context );
		init( null, 0 );
	}

	public CharacterView( Context context, AttributeSet attrs ){
		super( context, attrs );
		init( attrs, 0 );
	}

	public CharacterView( Context context, AttributeSet attrs, int defStyle ){
		super( context, attrs, defStyle );
		init( attrs, defStyle );
	}

	@SuppressWarnings( "UnusedParameters" )
	private void init( AttributeSet attrs, int defStyle ){
		characterPaint = new Paint( Paint.ANTI_ALIAS_FLAG );
		initializeCharacter();
	}

	void initializeCharacter(){
		characterPic = BitmapFactory.decodeResource( this.getResources(), R.drawable.dwalk1 );
	}

	@Override
	protected void onDraw( Canvas canvas ){
		super.onDraw( canvas );

		if( !widthAndHeightInitialized ){
			canvasWidth = canvas.getWidth();
			canvasHeight = canvas.getHeight();
			canvasWidthCenter = canvasWidth / 2;
			canvasHeightCenter = canvasHeight / 2;
			widthAndHeightInitialized = true;
		}

		canvas.save();
		canvas.translate( canvasWidth / 2, canvasHeight / 2 );
		canvas.drawBitmap( characterPic, -( characterPic.getWidth() / 2 ), -( characterPic.getHeight() / 2 ), characterPaint );
		canvas.restore();
	}

	@Override
	public boolean onTouchEvent( MotionEvent event ){
		Log.v( "OnTouchEvent", "In the overridden on touch event " + Integer.toString( event.getActionMasked() ) );
		Log.v( "OnTouchEvent", "x: " + Float.toString( event.getX() ) + ", y: " + Float.toString( event.getY() ) );
		Log.v( "OnTouchEvent", "x0: " + Float.toString( event.getX( 0 ) ) + ", y0: " + Float.toString( event.getY( 0 ) ) );
		Log.v( "OnTouchEvent", "Width: " + Integer.toString( canvasWidth ) + ", Height: " + Integer.toString( canvasHeight ) );
		Log.v( "OnTouchEvent", "x: " + Float.toString( event.getX() - canvasWidthCenter ) + ", y: " + Float.toString( event.getY() - canvasHeightCenter ) );

		float adjustedX = ( event.getX( 0 ) - canvasWidthCenter ) / canvasWidth;
		float adjustedY = ( event.getY( 0 ) - canvasHeightCenter ) / canvasHeight;

		Log.d( "OnTouchEvent", "adjusted x: " + Float.toString( adjustedX ) + ", adjusted Y: " + Float.toString( adjustedY ) );

		switch( event.getActionMasked() ){
			case MotionEvent.ACTION_DOWN:
				Log.i( "ACTION_UP", "Running custom action down" );
				if( adjustedY <= 0 && Math.abs( adjustedY ) > Math.abs( adjustedX ) ){
					// Turn character up
					pointCharacter( 0 );
				}
				else if( adjustedY > 0 && Math.abs( adjustedY ) > Math.abs( adjustedX ) ){
					// Turn character down
					pointCharacter( 2 );
				}
				else if( adjustedX > 0 ){
					// Turn character right
					pointCharacter( 1 );
				}
				else{
					// Turn character left
					pointCharacter( 3 );
				}
				break;
			default:
				return false;
		}

		return super.onTouchEvent( event );
	}

	void pointCharacter( int direction ){

		switch( direction ){
			case 0:
				Log.i( "TurningCharacter", "Turning character up" );
				characterPic = BitmapFactory.decodeResource( this.getResources(), R.drawable.uwalk1 );
				break;
			case 1:
				Log.i( "TurningCharacter", "Turning character right" );
				characterPic = BitmapFactory.decodeResource( this.getResources(), R.drawable.rwalk1 );
				break;
			case 2:
				Log.i( "TurningCharacter", "Turning character down" );
				characterPic = BitmapFactory.decodeResource( this.getResources(), R.drawable.dwalk1 );
				break;
			case 3:
				Log.i( "TurningCharacter", "Turning character left" );
				characterPic = BitmapFactory.decodeResource( this.getResources(), R.drawable.lwalk1 );
				break;
		}

		this.invalidate();
	}

}
