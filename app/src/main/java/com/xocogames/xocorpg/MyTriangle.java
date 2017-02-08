package com.xocogames.xocorpg;

class MyTriangle {

	private MyPoint<Float> firstPoint;
	private MyPoint<Float> secondPoint;
	private MyPoint<Float> thirdPoint;

	MyTriangle( MyPoint<Float> firstPoint, MyPoint<Float> secondPoint, MyPoint<Float> thirdPoint ){
		this.firstPoint = firstPoint;
		this.secondPoint = secondPoint;
		this.thirdPoint = thirdPoint;
	}

	boolean contains( Float x, Float y ){
		// This code was derived from: http://totologic.blogspot.com/2014/01/accurate-point-in-triangle-test.html
		// This StackOverflow article was also consulted: http://stackoverflow.com/questions/2049582/how-to-determine-if-a-point-is-in-a-2d-triangle

		double a =
			( ( secondPoint.getY() - thirdPoint.getY() ) * ( x - thirdPoint.getX() ) +
				( thirdPoint.getX() - secondPoint.getX() ) * ( y - thirdPoint.getY() ) ) /
			( ( secondPoint.getY() - thirdPoint.getY() ) * ( firstPoint.getX() - thirdPoint.getX() ) +
				( thirdPoint.getX() - secondPoint.getX() ) * ( firstPoint.getY() - thirdPoint.getY() ) );

		double b =
			( ( thirdPoint.getY() - firstPoint.getY() ) * ( x - thirdPoint.getX() ) +
				( firstPoint.getX() - thirdPoint.getX() ) * ( y - thirdPoint.getY() ) ) /
			( ( secondPoint.getY() - thirdPoint.getY() ) * ( firstPoint.getX() - thirdPoint.getX() ) +
				( thirdPoint.getX() - secondPoint.getX() ) * ( firstPoint.getY() - thirdPoint.getY() ) );

		double c = 1 - a - b;

		return 0 <= a && a <= 1 && 0 <= b && b <= 1 && 0 <= c && c <= 1;
	}

}
