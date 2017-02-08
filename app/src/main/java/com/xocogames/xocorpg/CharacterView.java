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

import java.util.ArrayList;


public class CharacterView extends View {

	Paint characterPaint;
	Bitmap characterPic;

	boolean widthAndHeightInitialized = false;
	int canvasWidth;
	int canvasHeight;
	int canvasWidthCenter;
	int canvasHeightCenter;
	ArrayList<ArrayList<Bitmap>> characterSprites;

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

		ArrayList<Bitmap> upSprites = new ArrayList<>();
		upSprites.add( BitmapFactory.decodeResource( this.getResources(), R.drawable.uwalk1 ) );
		upSprites.add( BitmapFactory.decodeResource( this.getResources(), R.drawable.uwalk2 ) );
		upSprites.add( BitmapFactory.decodeResource( this.getResources(), R.drawable.uwalk3 ) );

		ArrayList<Bitmap> rightSprites = new ArrayList<>();
		rightSprites.add( BitmapFactory.decodeResource( this.getResources(), R.drawable.rwalk1 ) );
		rightSprites.add( BitmapFactory.decodeResource( this.getResources(), R.drawable.rwalk2 ) );
		rightSprites.add( BitmapFactory.decodeResource( this.getResources(), R.drawable.rwalk3 ) );

		ArrayList<Bitmap> downSprites = new ArrayList<>();
		downSprites.add( BitmapFactory.decodeResource( this.getResources(), R.drawable.dwalk1 ) );
		downSprites.add( BitmapFactory.decodeResource( this.getResources(), R.drawable.dwalk2 ) );
		downSprites.add( BitmapFactory.decodeResource( this.getResources(), R.drawable.dwalk3 ) );

		ArrayList<Bitmap> leftSprites = new ArrayList<>();
		leftSprites.add( BitmapFactory.decodeResource( this.getResources(), R.drawable.lwalk1 ) );
		leftSprites.add( BitmapFactory.decodeResource( this.getResources(), R.drawable.lwalk2 ) );
		leftSprites.add( BitmapFactory.decodeResource( this.getResources(), R.drawable.lwalk3 ) );

		characterSprites = new ArrayList<>();
		characterSprites.add( upSprites );
		characterSprites.add( rightSprites );
		characterSprites.add( downSprites );
		characterSprites.add( leftSprites );

		characterPic = downSprites.get( 0 );
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

	void pointCharacter( int direction ){

		switch( direction ){
			case 0:
				//Log.i( "TurningCharacter", "Turning character up" );
				characterPic = BitmapFactory.decodeResource( this.getResources(), R.drawable.uwalk1 );
				break;
			case 1:
				//Log.i( "TurningCharacter", "Turning character right" );
				characterPic = BitmapFactory.decodeResource( this.getResources(), R.drawable.rwalk1 );
				break;
			case 2:
				//Log.i( "TurningCharacter", "Turning character down" );
				characterPic = BitmapFactory.decodeResource( this.getResources(), R.drawable.dwalk1 );
				break;
			case 3:
				//Log.i( "TurningCharacter", "Turning character left" );
				characterPic = BitmapFactory.decodeResource( this.getResources(), R.drawable.lwalk1 );
				break;
		}

		this.invalidate();
	}

}
