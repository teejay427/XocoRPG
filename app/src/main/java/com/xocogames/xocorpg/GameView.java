package com.xocogames.xocorpg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public class GameView extends View {

	static float screenWidthInCentimeters = 0.0f;
	static float screenHeightInCentimeters = 0.0f;
	static int screenWidthInPixels = 0;
	static int screenHeightInPixels = 0;
	static int columnInt;
	static int rowInt;
	DisplayMetrics metrics = new DisplayMetrics();
	Paint linePaint;
	GameMap gameMap;
	float gridSize = 0.75f;
	float numberOfColumns;
	float numberOfRows;
	int pixelsPerUnit;
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
			screenHeightInCentimeters = screenHeightInPixels / densityDpc;

			numberOfColumns = screenWidthInCentimeters / gridSize;
			numberOfRows = screenHeightInCentimeters / gridSize;
			Log.d( "test", Float.toString( screenWidthInPixels / numberOfColumns ) );
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

	boolean onTouch( MotionEvent event ){
		return gameMap.onTouch( event );
	}

}
