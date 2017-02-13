package com.xocogames.xocorpg;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v4.view.GestureDetectorCompat;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends Activity implements GestureDetector.OnGestureListener {

	//Random random = new Random();
	//NameGenerator nameGenerator = new NameGenerator();
	ArrayList<MyTriangle> myTriangles;
	int myOrientation;

	private GestureDetectorCompat mDetector;


	@Override
	protected void onCreate( Bundle savedInstanceState ){

		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );

		int currentUIOptions = this.getWindow().getDecorView().getSystemUiVisibility();
		int newUIOptions = currentUIOptions
				| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_IMMERSIVE
				| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
				| View.SYSTEM_UI_FLAG_FULLSCREEN;
		if( currentUIOptions != newUIOptions ){
			this.getWindow().getDecorView().setSystemUiVisibility( newUIOptions );
		}

		mDetector = new GestureDetectorCompat( this, this );

	}


	public int getWidth(){
		return this.getWindow().getDecorView().getWidth();
	}


	public int getHeight(){
		return this.getWindow().getDecorView().getHeight();
	}


	private GameView getGameView(){
		return ( GameView ) findViewById( R.id.view );
	}


	public void updateMyTriangles(){
		// Fills the MyTriangle array with updated triangles for the

		float width = getWidth();
		float height = getHeight();

		MyPoint<Float> center = new MyPoint<>( width / 2, height / 2 );
		MyPoint<Float> topLeft = new MyPoint<>( 0.0f, 0.0f );
		MyPoint<Float> topRight = new MyPoint<>( width, 0.0f );
		MyPoint<Float> bottomLeft = new MyPoint<>( 0.0f, height );
		MyPoint<Float> bottomRight = new MyPoint<>( width, height );

		if( myTriangles == null ){
			myTriangles = new ArrayList<>();
		}
		else{
			myTriangles.clear();
		}

		myTriangles.add( new MyTriangle( center, topLeft, topRight ) );
		myTriangles.add( new MyTriangle( center, topRight, bottomRight ) );
		myTriangles.add( new MyTriangle( center, bottomRight, bottomLeft ) );
		myTriangles.add( new MyTriangle( center, bottomLeft, topLeft ) );

	}


	@Override
	public void onConfigurationChanged( Configuration newConfig ){

		if( newConfig.orientation != myOrientation ){
			updateMyTriangles();
			myOrientation = newConfig.orientation;
		}

		super.onConfigurationChanged( newConfig );

	}


	@Override
	public boolean onTouchEvent( MotionEvent event ){

		//Log.d( "OnTouchEvent", Float.toString( event.getX( 0 ) ) );

		if( myTriangles == null ){
			updateMyTriangles();
		}

		/*// These .contains() functions take approximately 15ms to run ( each ) without caching
		//  With caching ( running them over and over again ), they take about 6ms

		if( myTriangles.get( 0 ).contains( event.getX( 0 ), event.getY( 0 ) ) ){
			getCharacterView().pointCharacter( 0 );
		}
		else if( myTriangles.get( 1 ).contains( event.getX( 0 ), event.getY( 0 ) ) ){
			getCharacterView().pointCharacter( 1 );
		}
		else if( myTriangles.get( 2 ).contains( event.getX( 0 ), event.getY( 0 ) ) ){
			getCharacterView().pointCharacter( 2 );
		}
		else{
			getCharacterView().pointCharacter( 3 );
		}*/

		this.mDetector.onTouchEvent( event );

		// Be sure to call the superclass implementation
		return super.onTouchEvent( event );
	}


	@Override
	public boolean onDown( MotionEvent e ){
		return false;
	}


	@Override
	public void onShowPress( MotionEvent e ){

	}


	@Override
	public boolean onSingleTapUp( MotionEvent event ){
		return false;
	}


	@Override
	public boolean onScroll( MotionEvent e1, MotionEvent e2, float distanceX, float distanceY ){
		return false;
	}


	@Override
	public void onLongPress( MotionEvent e ){

	}


	@Override
	public boolean onFling( MotionEvent e1, MotionEvent e2, float velocityX, float velocityY ){
		return false;
	}

}
