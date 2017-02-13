package com.xocogames.xocorpg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;


public class GameView extends View {

	boolean widthAndHeightInitialized = false;
	static float screenWidthInCentimeters = 0.0f;
	static float screenHeightInCentimeters = 0.0f;
	static int screenWidthInPixels = 0;
	static int screenHeightInPixels = 0;
	int canvasWidth;
	int canvasHeight;
	int canvasWidthCenter;
	int canvasHeightCenter;
	Point size = new Point();
	DisplayMetrics metrics = new DisplayMetrics();
	Paint linePaint;
	GameMap gameMap;

	public GameView( Context context ){
		super( context );
		init( null, 0 );
	}

	public GameView( Context context, AttributeSet attrs ){
		super( context, attrs );
		init( attrs, 0 );
	}

	public GameView( Context context, AttributeSet attrs, int defStyle ){
		super( context, attrs, defStyle );
		init( attrs, defStyle );
	}

	@SuppressWarnings( "UnusedParameters" )
	private void init( AttributeSet attrs, int defStyle ){
		linePaint = new Paint( Paint.ANTI_ALIAS_FLAG );
		linePaint.setColor( Color.BLACK );
		gameMap = new GameMap( this.getContext(), this );
	}

	@Override
	protected void onDraw( Canvas canvas ){
		super.onDraw( canvas );

		gameMap.onDraw( canvas );

		canvas.save();
		//canvas.translate( 60, 60 );
		getDisplay().getSize( size );
		getDisplay().getMetrics( metrics );

		int heightPixels = metrics.heightPixels;
		int widthPixels = metrics.widthPixels;
		int densityDpi = metrics.densityDpi;
		//float xdpi = metrics.xdpi;
		//float ydpi = metrics.ydpi;

		Log.i( "onDraw", "width: " + Integer.toString( widthPixels ) + ", height: " + Integer.toString( heightPixels ) + ", density: " + Integer.toString( densityDpi ) );
		int densityDpc = ( int ) ( densityDpi / 2.54 * 0.75 );

		for( int i = 0; densityDpc * i < heightPixels; ++i ){
			canvas.drawLine( 0, densityDpc * i, widthPixels, densityDpc * i, linePaint );
		}
		for( int i = 0; densityDpc * i < widthPixels; ++i ){
			canvas.drawLine( densityDpc * i, 0, densityDpc * i, heightPixels, linePaint );
		}

		canvas.restore();

		if( !widthAndHeightInitialized ){
			canvasWidth = canvas.getWidth();
			canvasHeight = canvas.getHeight();
			canvasWidthCenter = canvasWidth / 2;
			canvasHeightCenter = canvasHeight / 2;
			widthAndHeightInitialized = true;
		}
	}

	/*@Override
	public boolean onTouchEvent( MotionEvent event ){

		float adjustedX = ( event.getX( 0 ) - canvasWidthCenter ) / canvasWidth;
		float adjustedY = ( event.getY( 0 ) - canvasHeightCenter ) / canvasHeight;

		switch( event.getActionMasked() ){
			case MotionEvent.ACTION_DOWN:
				//Log.i( "ACTION_UP", "Running custom action down" );
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
	}*/

}
