package com.git.QuadTreeImp;

public class Point {
	private int x;
	private int y;
	
	public Point(int xS, int yS) {
		x = xS;
		y = yS;
	}
	
	public Point() {
		this(0,0);
	}
	
	// Accessor Method
	public int[] getPoint() {
		int[] arr = {x,y};
		return arr;
	}
	
	// Setter Method
	public void setPoint(int xS, int yS) {
		x = xS;
		y = yS;
	}
}
