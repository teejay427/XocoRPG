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
	float gridSize = 0.75f;
	float numberOfColumns;
	float numberOfRows;
	int pixelsPerUnit;
	int columnInt;
	int rowInt;
	float columnRemainder;
	float rowRemainder;
	int columnOffset;
	int rowOffset;

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
		getDisplay().getMetrics( metrics );
		int densityDpc = ( int ) ( metrics.densityDpi / 2.54 * 0.75 );

		if( screenWidthInCentimeters == 0.0f ){
			screenWidthInPixels = metrics.widthPixels;
			screenHeightInPixels = metrics.heightPixels;
			screenWidthInCentimeters = screenWidthInPixels / densityDpc;
			screenHeightInCentimeters = screenHeightInCentimeters / densityDpc;

			numberOfColumns = screenWidthInCentimeters / gridSize;
			numberOfRows = screenHeightInCentimeters / gridSize;
			pixelsPerUnit = ( int ) ( screenWidthInPixels / numberOfColumns );

			columnRemainder = numberOfColumns % 1;
			rowRemainder = numberOfRows % 1;

			columnInt = ( int ) numberOfColumns;
			rowInt = ( int ) numberOfRows;

			if( columnInt % 2 == 0 ){
				columnInt -= 1;
				columnRemainder += 1;
			}
			if( rowInt % 2 == 0 ){
				rowInt -= 1;
				rowRemainder += 1;
			}
			columnOffset = ( int ) ( ( columnRemainder * pixelsPerUnit ) / 2 );
			rowOffset = ( int ) ( ( rowRemainder * pixelsPerUnit ) / 2 );
		}

		gameMap.onDraw( canvas );

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
