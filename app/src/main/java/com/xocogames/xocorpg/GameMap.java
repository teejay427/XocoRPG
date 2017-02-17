package com.xocogames.xocorpg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;


class GameMap {

	private ArrayList<GameElement> gameElements = new ArrayList<>();
	private Character mainCharacter;
	private GameView gameView;
	Context context;
	Paint paint;


	GameMap( Context context, GameView gameView ){
		this.context = context;
		mainCharacter = new Character( 3, 5, "Link", context, gameView );
		this.gameElements.add( mainCharacter );
		this.gameView = gameView;
		paint = new Paint( Paint.ANTI_ALIAS_FLAG );
		paint.setColor( Color.BLACK );
	}


	void onDraw( Canvas canvas ){

		for( int i = 0; i < gameView.numberOfColumns; ++i ){
			canvas.drawLine( i * gameView.pixelsPerUnit + gameView.columnOffset, 0, i * gameView.pixelsPerUnit + gameView.columnOffset, gameView.getMeasuredHeight(), paint );
		}

		Log.d( "rows", Float.toString( gameView.numberOfRows ) );
		for( int i = 0; i < gameView.numberOfRows; ++i ){
			canvas.drawLine( 0, i * gameView.pixelsPerUnit + gameView.rowOffset, gameView.getMeasuredWidth(), i * gameView.pixelsPerUnit + gameView.rowOffset, paint );
		}

		for( GameElement gameElement : gameElements ){
			gameElement.onDraw( gameView.pixelsPerUnit, gameView.columnOffset, gameView.rowOffset, canvas );
		}
	}


	boolean onTouch( MotionEvent event ){
		Log.i( "here", "here" );

		if( event.getActionMasked() == MotionEvent.ACTION_UP ){
			int columnTouched = ( int ) ( ( event.getX() - gameView.columnOffset ) / gameView.pixelsPerUnit );
			int rowTouched = ( int ) ( ( event.getY() - gameView.rowOffset ) / gameView.pixelsPerUnit );

			Log.d( "pix", Integer.toString( gameView.pixelsPerUnit ) );
			Log.d( "xy", Float.toString( event.getX() ) + ", " + Float.toString( event.getY() ) );
			Log.d( "xy", Integer.toString( columnTouched ) + ", " + Integer.toString( rowTouched ) );

			if( gameView.columnRemainder == 0 ){
				columnTouched -= 1;
			}
			if( gameView.rowRemainder == 0 ){
				rowTouched -= 1;
			}

			mainCharacter.setX( columnTouched )
					.setY( rowTouched );

			gameView.invalidate();

			return true;
		}

		return false;
	}
}
