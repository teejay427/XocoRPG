package com.xocogames.xocorpg;

import android.content.Context;
import android.graphics.Canvas;

import java.util.ArrayList;


class GameMap {

	ArrayList<Character> characters = new ArrayList<>();
	Context context;


	GameMap( Context context, GameView gameView ){
		this.characters.add( new Character( 3, 5, "Link", context, gameView ) );
	}


	void onDraw( Canvas canvas ){


		for( Character character : characters ){
			character.onDraw( 100, 100, canvas );
		}
	}
}
