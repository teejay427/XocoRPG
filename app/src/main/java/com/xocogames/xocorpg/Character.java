package com.xocogames.xocorpg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

class Character extends GameElement {

	private String name;
	private ArrayList<ArrayList<Bitmap>> characterSprites;
	private Context context;
	private GameView gameView;
	private Bitmap currentSprite;
	private Paint characterPaint;

	Character( int x, int y, String name, Context context, GameView gameView ){

		this.x = x;
		this.y = y;
		this.name = name;
		this.context = context;
		this.gameView = gameView;
		characterPaint = new Paint( Paint.ANTI_ALIAS_FLAG );

		initializeCharacterSprite();
	}

	private void initializeCharacterSprite(){

		ArrayList<Bitmap> upSprites = new ArrayList<>();
		upSprites.add( BitmapFactory.decodeResource( context.getResources(), context.getResources().getIdentifier( "uwalk1", "drawable", context.getPackageName() ) ) );
		upSprites.add( BitmapFactory.decodeResource( context.getResources(), context.getResources().getIdentifier( "uwalk2", "drawable", context.getPackageName() ) ) );
		upSprites.add( BitmapFactory.decodeResource( context.getResources(), context.getResources().getIdentifier( "uwalk3", "drawable", context.getPackageName() ) ) );

		ArrayList<Bitmap> rightSprites = new ArrayList<>();
		rightSprites.add( BitmapFactory.decodeResource( context.getResources(), context.getResources().getIdentifier( "rwalk1", "drawable", context.getPackageName() ) ) );
		rightSprites.add( BitmapFactory.decodeResource( context.getResources(), context.getResources().getIdentifier( "rwalk2", "drawable", context.getPackageName() ) ) );
		rightSprites.add( BitmapFactory.decodeResource( context.getResources(), context.getResources().getIdentifier( "rwalk3", "drawable", context.getPackageName() ) ) );

		ArrayList<Bitmap> downSprites = new ArrayList<>();
		downSprites.add( BitmapFactory.decodeResource( context.getResources(), context.getResources().getIdentifier( "dwalk1", "drawable", context.getPackageName() ) ) );
		downSprites.add( BitmapFactory.decodeResource( context.getResources(), context.getResources().getIdentifier( "dwalk2", "drawable", context.getPackageName() ) ) );
		downSprites.add( BitmapFactory.decodeResource( context.getResources(), context.getResources().getIdentifier( "dwalk3", "drawable", context.getPackageName() ) ) );

		ArrayList<Bitmap> leftSprites = new ArrayList<>();
		leftSprites.add( BitmapFactory.decodeResource( context.getResources(), context.getResources().getIdentifier( "lwalk1", "drawable", context.getPackageName() ) ) );
		leftSprites.add( BitmapFactory.decodeResource( context.getResources(), context.getResources().getIdentifier( "lwalk2", "drawable", context.getPackageName() ) ) );
		leftSprites.add( BitmapFactory.decodeResource( context.getResources(), context.getResources().getIdentifier( "lwalk3", "drawable", context.getPackageName() ) ) );

		characterSprites = new ArrayList<>();
		characterSprites.add( upSprites );
		characterSprites.add( rightSprites );
		characterSprites.add( downSprites );
		characterSprites.add( leftSprites );

		this.pointCharacter( Direction.SOUTH );

	}

	@Override
	void onDraw( int pixelsPerUnit, int columnOffset, int rowOffset, Canvas canvas ){
		canvas.drawBitmap( currentSprite, x * pixelsPerUnit + columnOffset + ( ( pixelsPerUnit - currentSprite.getWidth() ) / 2 ), y * pixelsPerUnit + rowOffset + ( ( pixelsPerUnit - currentSprite.getHeight() ) / 2 ), characterPaint );
	}


	private void pointCharacter( int direction ){

		switch( direction ){
			case 0:
				//Log.i( "TurningCharacter", "Turning character up" );
				currentSprite = characterSprites.get( 0 ).get( 0 );
				break;
			case 1:
				//Log.i( "TurningCharacter", "Turning character right" );
				currentSprite = characterSprites.get( 1 ).get( 0 );
				break;
			case 2:
				//Log.i( "TurningCharacter", "Turning character down" );
				currentSprite = characterSprites.get( 2 ).get( 0 );
				break;
			case 3:
				//Log.i( "TurningCharacter", "Turning character left" );
				currentSprite = characterSprites.get( 3 ).get( 0 );
				break;
		}

		gameView.invalidate();

	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	Character setX( int x ){
		this.x = x;
		return this;
	}

	Character setY( int y ){
		this.y = y;
		return this;
	}

}
