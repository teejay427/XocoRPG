package com.xocogames.xocorpg;

import android.graphics.Canvas;

abstract class GameElement {

	int x,y;

	abstract void onDraw( int pixelsPerUnit, int columnOffset, int rowOffset, Canvas canvas );

}
