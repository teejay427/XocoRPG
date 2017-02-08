package com.xocogames.xocorpg;


class MyPoint<Num> {

	private Num x;
	private Num y;

	public MyPoint( Num x, Num y ){
		this.x = x;
		this.y = y;
	}

	public Num getX(){
		return x;
	}

	public void setX( Num x ){
		this.x = x;
	}

	public Num getY(){
		return y;
	}

	public void setY( Num y ){
		this.y = y;
	}
}
