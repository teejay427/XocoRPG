package com.xocogames.xocorpg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.xocogames.xocorpg.R;

public class CharacterView extends View {

	Paint characterPaint;
	Bitmap characterPic;
	int canvasWidth;
	int canvasHeight;

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

	private void init( AttributeSet attrs, int defStyle ){
		characterPaint = new Paint( Paint.ANTI_ALIAS_FLAG );
		initializeCharacter();
	}

	@Override
	protected void onDraw( Canvas canvas ){
		super.onDraw( canvas );

		canvasWidth = canvas.getWidth();
		canvasHeight = canvas.getHeight();

		canvas.drawBitmap( characterPic, canvasWidth / 2 - characterPic.getWidth() / 2, canvasHeight / 2 - characterPic.getHeight() / 2, characterPaint );
	}

	void initializeCharacter(){
		characterPic = BitmapFactory.decodeResource( this.getResources(), R.drawable.dwalk1 );
	}

	void pointCharacter( int direction ){
		switch( direction ){
			case 0:
				characterPic = BitmapFactory.decodeResource( this.getResources(), R.drawable.uwalk1 );
				break;
			case 1:
				characterPic = BitmapFactory.decodeResource( this.getResources(), R.drawable.rwalk1 );
				break;
			case 2:
				characterPic = BitmapFactory.decodeResource( this.getResources(), R.drawable.dwalk1 );
				break;
			case 3:
				characterPic = BitmapFactory.decodeResource( this.getResources(), R.drawable.lwalk1 );
				break;
		}
	}

}
